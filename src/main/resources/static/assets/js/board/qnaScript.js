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
            fileTag = "<span><img src='" + fileUrl + "'><button onclick='removeFile()'>X</button></span>";
        }else{
            fileUrl = data[i].filepath;
            fileTag = "<span>" + fileUrl + "<button onclick='removeFile()'>X</button></span>";
        }

        target.append(
            fileTag
        );
    }
}

function removeFile(){
    console.log("확인");
}