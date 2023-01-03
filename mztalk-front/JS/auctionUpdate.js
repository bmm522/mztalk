
//디테일값 그대로 받아오기
window.onload = () => {
    // fetch("http://localhost:8000/auction/board/" + localStorage.getItem("bId"), {
    //     method: "GET",
    //     headers: {
    //         "Content-Type":"application/json",
    //         Authorization:localStorage.getItem('authorization'),
    //         RefreshToken:localStorage.getItem('refreshToken'),
    //     }
    // })
    // .then((res)=>res.json())
    // .then(res=>{
    //     console.log("detail 받아온 res JSON: " + JSON.stringify(res));
        
    //         console.log("deatil 받아온 값: " + res.boardId);
    //         document.getElementById('title').value = res.title;
    //         document.getElementById('searchBook').value = res.bookTitle;
    //         document.getElementById('content').value = res.content;


        

    // })
    const imageInfo = JSON.parse(localStorage.getItem("imageInfo"));
    console.log("update imageInfo: " + imageInfo);
    document.getElementById("title").innerHTML = localStorage.getItem("title");
    for(let i = 0; i < imageInfo.length; i++) {
        console.log("hidden-objectKey 넣을 값: " + imageInfo[i].objectKey);
   //  document.getElementById("hidden-objectKey").value = imageInfo[i].objectKey;
   //  document.getElementById("hidden-objectKey").innerHTML = imageInfo[i].objectKey;
        let objectKey = imageInfo[i].objectKey;
        let imageName = imageInfo[i].imageName;
        console.log("찍히니 ? : " + objectKey);
        document.getElementById("fileList").innerHTML += `<div id="${objectKey}"><span class = "imageName">${imageInfo[i].imageName}</span><span id = "fileDelete" style="color: gray;margin-left: 5px; cursor: pointer;" onclick="deleteFile('${objectKey}');">X<br></span></div>`;

    }
    // console.log(document.getElementsByClassName('imageName')[0].innerHTML);
    // console.log(document.getElementsByClassName('imageName')[1].innerHTML);
    document.getElementById("title").value = localStorage.getItem("title");
    document.getElementById("searchBook").value = localStorage.getItem("bookTitle");
    document.getElementById("content").value = localStorage.getItem("content");
    document.getElementById("hidden-bId").value = localStorage.getItem("bId");



    document.getElementById("currentPrice").addEventListener('click', function() {
        alert("시작가는 변경할 수 없습니다.");
    });
    document.getElementById("timeLimit").addEventListener('click', function() {
        alert("남은 시간은 변경할 수 없습니다.");
    });


    //첨부 파일 개수 체크
    checkFile();
}

function checkFile() {
    if(Number(document.getElementsByClassName('imageName').length) == 3) {
        document.getElementById('count').innerHTML = '<span style="color:gray;">사진은 최대 3장까지 첨부 가능합니다.</span>';
    } else {
        //파일 추가 버튼
        document.getElementById("addFile").addEventListener('click', function () {
            const fileArea = document.getElementById("fileArea");
            console.log("수정 첨부 파일: " + document.getElementsByName("image").length);
            console.log("이미 첨부한 파일: " + Number(document.getElementsByClassName("imageName").length));
        
            
            if(Number(document.getElementsByName("image").length) + Number(document.getElementsByClassName("imageName").length) < 3) {
                const newDiv = document.createElement('div');
                newDiv.classList.add('col-10');
                newDiv.innerHTML = '<input type = "file" class = "form-control" name = "image" accept="image/*"">';
                fileArea.append(newDiv);
        
            } else {
                alert("사진은 최대 3개까지 첨부 가능합니다.");
            }
        });  
    }
}


//파일 삭제
function deleteFile(objectKey) {
    document.getElementById(objectKey).remove();
    console.log(objectKey);
   // console.log("파일삭제 시 hidden-objectKey: " + document.getElementById("hidden-objectKey").value);
    if(confirm('정말 삭제하시겠습니까?')) {
        console.log("삭제시objectKey: " + objectKey);
        fetch('http://localhost:8000/resource/image-detail?imageName=' + objectKey, {
            method: 'DELETE',
            headers: {
                "Content-Type": "text/html",
                Authorization: localStorage.getItem('authorization'),
                RefreshToken: localStorage.getItem('refreshToken'),
            }
        })
        .then(res=>{
            if(res.status == 200) {
                console.log("이미지 삭제 완료");
                checkFile();
            }

        })
    }

}

//*******수정하기
function updateBoard() {

    if(document.getElementById('inputFiles').value){
        const form = document.getElementById('update-form');
        const payload = new FormData(form);

       fetch('http://localhost:8000/resource/update-image',{
          method: 'POST',
          body: payload
         })
     .then(res=>{
         updateData();
     })
    } else {
        updateData();
    };
}

const updateData = () =>{
    console.log("수정하기 버튼 눌렀을 시 bId: " + document.getElementById("hidden-bId").value);
    fetch("http://localhost:8000/auction/board/" + document.getElementById('hidden-bId').value, {
        method: "PATCH",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            "title" : document.getElementById('title').value,
            "content": document.getElementById('content').value
        }),
    })
    .then(res => {
        if(res.status = 200) {
            console.log("수정 완료");
    
        }
        location.href="auctionDetail.html";
    });
}

//지금 마감시키기
document.getElementById('closeNow').addEventListener('click', function() {
    if(confirm('정말 지금 마감시키겠습니까?')) {
        // let nowDateTime = new Date();
        // console.log("지금 마감시키기 nowDateTime: " + nowDateTime);
        fetch("http://localhost:8000/auction/board/close", {
        method: "PATCH",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body: JSON.stringify({
            boardId: document.getElementById('hidden-bId')
        }),
    })
    .then(res => {
        if(res.status = 200) {
            alert('마감 완료!');
            document.getElementById('close-div').innerHTML = '<span style="float: right; margin-top:5px; color:gray" id = "closed">마감 완료</span>';
        }
    })
    }
    
});


//목록으로
function backToMain() {
    location.href="auction.html";
}

