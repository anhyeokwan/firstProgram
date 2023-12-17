$(function (){

});

function loadQnaList(){

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
            fileTag = "<span><img src='" + fileUrl + "'><button onclick=\"removeFile(this, '" + data[i].filepath + "')\">X</button></span>";
        }else{
            fileUrl = data[i].filepath;
            fileTag = "<span>" + fileUrl + "<button onclick='removeFile(" + fileUrl + ")'>X</button></span>";
        }

        target.append(
            fileTag
        );
    }
}

function removeFile(obj, filepath){
    console.log("확인");
    console.log(filepath);

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