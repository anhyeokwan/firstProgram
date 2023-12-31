$(function (){

});

function loadQnaList(){

}

function registQna() {
    const filenameArr = [];
    const filepathArr = [];
    const url = "/qna/insertQna";
    var dataString = $("#contactForm").serialize();

    for (let i = 0; i < $(".fileSpan").length; i++) {
        var filename = $(".fileSpan").eq(i).data("filename");
        var filepath = $(".fileSpan").eq(i).data("filepath");

        filenameArr.push(filename);
        filepathArr.push(filepath);
    }

    const fileObj = {
        filenameArr : filenameArr,
        filepathArr : filepathArr
    };

    dataString += "&fileObj=" + JSON.stringify(fileObj);

    $.ajax({
        url : url,
        type : "post",
        data: dataString,
        dataType: "json",
        success: function (data) {
            console.log(data);
            const code = data.code;
            let message = data.message;

            if (code == "200") {
                const qnaIdx = data.qnaIdx;
                loadOneQna(qnaIdx);
            }else{
                alert(message);
            }
        },
    })
}

function loadOneQna(qnaIdx) {
    const url = "/qna/loadOneQna";
    $("[name=qnaIdx]").val(qnaIdx);
    $("#iForm").attr("method", "get");
    $("#iForm").attr("action", url);
    $("#iForm").submit();

    /*$.ajax({
        url : url,
        type : "get",
        data : {
            qnaIdx : qnaIdx
        },
        dataType : "json"
        ,
        success: function (data) {

        },
    })*/
}

function makeFileListHtml(data){
    $(".uploadResult").empty();
    const target = $(".uploadResult");

    let fileTag = ""
    for (let i = 0; i < data.length; i++) {
        console.log(data[i].filepath);
        var fileUrl;
        if(data[i].image){
            fileUrl = "/getImgUrl?filepath=" + data[i].filename + "&type=qna";
            fileTag = "<span class='fileSpan' data-filename='" + data[i].filename + "' data-filepath='" + data[i].filepath + "'><img src='" + fileUrl + "'><button onclick=\"removeFile(this, '" + data[i].filepath + "')\">X</button></span>";

        }else{
            fileUrl = data[i].filepath;
            fileTag = "<span class='fileSpan' data-filename='" + data[i].filename + "' data-filepath='" + data[i].filepath + "'>" + fileUrl + "<button onclick='removeFile(" + fileUrl + ")'>X</button></span>";
        }

        target.append(
            fileTag
        );
    }
}

function removeFile(obj, filepath){

    var url = "/removeFile";

    $.ajax({
        url : url,
        type : "post",
        data : {
            filepath : filepath
        },
        dataType: "json",
        success: function (data) {
            var code = data.code;
            var message = data.message;

            if (code != "200") {
                alert(message);
            }else{
                $(obj).parent().remove();
            }
        },
    })
}