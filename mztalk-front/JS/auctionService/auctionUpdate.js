
//디테일값 그대로 받아오기
window.onload = () => {  
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
        document.getElementById("fileList").innerHTML += `<div id="${objectKey}"><span class = "imageName">${imageInfo[i].imageName}</span><span class = "fileDelete" style="color: gray;margin-left: 5px; cursor: pointer;" onclick="deleteFile('${objectKey}');">X<br></span></div>`;

    }
    // console.log(document.getElementsByClassName('imageName')[0].innerHTML);
    // console.log(document.getElementsByClassName('imageName')[1].innerHTML);
    document.getElementById("title").value = localStorage.getItem("title");
    document.getElementById("searchBook").value = localStorage.getItem("bookTitle");
    document.getElementById("content").value = localStorage.getItem("content");
    document.getElementById("hidden-bId").value = localStorage.getItem("bId");
    document.getElementById("hidden-isbn").value = localStorage.getItem("isbn");

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
    console.log("checkFile 들어옴");
    if(Number(document.getElementsByClassName('imageName').length) == 3) {
        document.getElementById('count').innerHTML = '<input type="hidden" id = "inputFiles"/><span style="color:gray; size:smaller; margin-top: 500px;">사진은 3장까지 첨부 가능합니다.</span>';
        document.getElementById('addFileArea').innerHTML = '';
    } else {
        document.getElementById('count').innerHTML = '<input type="file" class="form-control" id="inputFiles" name = "image" accept="image/*">';
        document.getElementById('addFileArea').innerHTML = '<button type = "button" id = "addFile" style = "margin-top: 20px;margin-left: 10px;">+</button>';
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
    if(confirm('정말 삭제하시겠습니까?')) {
        console.log("삭제시objectKey: " + objectKey);
        fetch(`${LOCALHOST_URL}/resource/image-detail?imageName=${objectKey}`, {
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

       fetch(`${LOCALHOST_URL}/resource/update-image`,{
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
    fetch(`${LOCALHOST_URL}/auction/board/` + document.getElementById('hidden-bId').value, {
        method: "PATCH",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            "title" : document.getElementById('title').value,
            "content": document.getElementById('content').value,
            "bookTitle": document.getElementById('searchBook').value,
            "isbn": document.getElementById('hidden-isbn').value
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
        fetch(`${LOCALHOST_URL}/auction/board/close`, {
        method: "PATCH",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body: JSON.stringify({
            "boardId": document.getElementById('hidden-bId').value
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

//책 검색
document.getElementById('searchBtn').addEventListener('click', function() {
    fetch("https://dapi.kakao.com/v3/search/book?target=title&query="+document.getElementById('searchBook').value, {
        method: "GET",
        headers: {
            Authorization: "KakaoAK d7041cb01ccfe4c12792028ae9cb5fff"
        }
    })
    .then((res) => res.json())
    .then(res => {
        let bookId = 1;
        for(let book of res.documents) {
            let title = book.title;
            let authors = book.authors;
            let publisher = book.publisher;
            let thumbnail = "";
            let isbn = book.isbn;
            if(book.thumbnail == "") {
                thumbnail = "img/auction/noImage.png"
            } else {
                thumbnail = book.thumbnail;
            }
            document.getElementById('bookSearchArea').innerHTML += `<div class = "col-4" id = "bookImg"><img src = "${thumbnail}" class = "bookThumbnail" style = "width: 50%; height: 80%;"></div><div class = "col-8 mt-3 bookContent"><span id = "${bookId}" class = "bookTitle" style="display:block;" onclick = "selectBook(${bookId});">${title}</span><span class = "bookInform" style="color:gray;">저자 | ${authors} 출판 | ${publisher}<input type = "hidden" id = "isbn${bookId}" value = "${isbn}"/></div>`;
            bookId++;
        }
        
    })
    
});

//책 선택
function selectBook(bookId) {
    bookTitle = document.getElementById(bookId).textContent;
    isbnData = document.getElementById('isbn' + bookId).value;
    isbnArr = isbnData.split(" ");
    isbn = isbnArr[1];
    console.log("타이틀 클릭 시 책title: " + bookTitle);
    console.log("타이틀 클릭 시 isbn값: " + isbn);
    document.getElementById('searchBook').value = bookTitle;
    document.getElementById('hidden-isbn').value = isbn;

    document.querySelector('.btn-close').click();
    document.getElementById('bookSearchArea').innerHTML = "";
}


//목록으로
function backToMain() {
    location.href="auction.html";
}

