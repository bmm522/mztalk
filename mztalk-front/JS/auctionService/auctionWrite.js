 //시작가 0,000 형식으로
function priceStandard(text) {
    const startPrice = document.getElementById("startPrice").value;
    const under = startPrice.slice(-3);
    const upper = startPrice.slice(0, startPrice.length-3);
    text.value = upper + "," + under;

}

//마감 시간 제한
function timeStandard(time) {
    const timeLimit = document.getElementById("timeLimit").value;
    if(timeLimit > 72) {
        alert("제한 시간은 3일을 넘길 수 없습니다.");
        time.value = 3;
        return false;
    }
}


//글쓰기 insert
function boardWrite() {
    const form = document.getElementById('image-form-form-form');
    const payload = new FormData(form);

    fetch('http://localhost:8000/resource/images',{
        method: 'POST',
        body: payload,
    })
    .then(res=>{
        postData();
    })
}

const postData = () =>{
    const startPrice = document.getElementById('startPrice').value;
    const startPriceSplit = startPrice.split(',');
    const startPriceTrans = Number(startPriceSplit[0].concat(startPriceSplit[1]));
    let date = new Date();

    if(document.getElementById('title').value == '') {
        alert('제목을 입력해 주세요.');
        return false;
    } else if(document.getElementById('searchBook').value == '') {
        alert('책 제목을 입력해 주세요.');
        return false;
    } else if(document.getElementById('content').value == '') {
        alert('내용을 입력해 주세요.');
        return false;
    } else if(document.getElementById('startPrice').value == '') {
        alert('시작가를 입력해 주세요.');
        return false;
    } else if(document.getElementById('timeLimit').value == '') {
        alert('마감 시간을 입력해 주세요.');
        return false;
    }
    
    fetch('http://localhost:8000/auction/board', {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            "title" : document.getElementById('title').value,
            "writer":localStorage.getItem('userNickname'),
            "content":document.getElementById('content').value,
            "bookTitle": document.getElementById('searchBook').value,
            "startPrice": startPriceTrans,
            "timeLimit":  date.setHours(date.getHours() +  Number(document.getElementById('timeLimit').value)),
            "currentTime" : new Date().getTime(),
            "userNo": localStorage.getItem("userNo"),
            "isbn": document.getElementById('hidden-isbn').value
        }),
    })
    .then(res => {
        // localStorage.removeItem('bId');
        location.href="auction.html";
    })
}

//파일추가
window.onload = () => {
    //목록으로
    document.getElementById('listBtn').addEventListener('click', function() {
        location.href="auction.html";
    })
    console.log(document.getElementById('image-form-form-form'));
    document.getElementById('hidden-bId').value = localStorage.getItem('bId');
    console.log('bId : ' + document.getElementById('hidden-bId').value);
    const fileArea = document.getElementById("fileArea");
    
    document.getElementById("addFile").addEventListener('click', () => { 
        if(document.getElementsByName("files").length < 2) {
            const newDiv = document.createElement('div');
            newDiv.classList.add('col-10');
            newDiv.innerHTML = '<input type = "file" class = "form-control" name = "image" accept="image/*"">';
            fileArea.append(newDiv);
        } else {
            alert("파일은 3개까지 첨부 가능합니다.");
        }
    });
}

//파일 preview
function showPreview(event) {
    console.log("들어옴?");
    if(event.target.files.length > 0) {
        var src = URL.createObjectURL(event.target.files[0]);
        var preview = document.getElementById("file-ip-1-preview");
        preview.src = src;
        preview.style.display = "block";
    }
}

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
            let isbn = book.isbn;
            let thumbnail = "";
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