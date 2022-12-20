
//슬라이드 관련
const slides = document.querySelector('.slides');
const slideImg = document.querySelectorAll('.slides li');
let currentIdx = 0;
const slideCount = slideImg.length; //슬라이드 개수
const prev = document.querySelector('.prev');
const next = document.querySelector('.next');
const slideWidth = 300;
const slideMargin = 100;

slides.style.width = (slideWidth + slideMargin) * slideCount + 'px';

function moveSlide(num) {
    slides.style.left = -num * 400 + 'px';
    currentIdx = num;
}

prev.addEventListener('click', function() {
    if(currentIdx !== 0) moveSlide(currentIdx - 1);
});

next.addEventListener('click', function() {
    if(currentIdx !== slideCount -1) {
        moveSlide(currentIdx + 1);
    }
});

//입찰가 가격 제한
document.getElementById("priceSubmitBtn").addEventListener('click', function() {
    console.log(document.getElementById('startPrice').innerHTML);
    console.log(document.getElementById('inputPrice').value);
    if(Number(document.getElementById('startPrice').innerHTML) >= Number(document.getElementById('inputPrice').value)) {
        alert('시작 금액보다 큰 금액을 입력해 주세요');
    }
});
//입찰가 전달
document.getElementById("priceSubmitBtn").addEventListener('click', function() {
    fetch("http://localhost:8000/auction/board/", {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "currentPrice" : Number(document.getElementById('inputPrice').value)
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
});

//댓글 작성
function writeComment() {
    fetch("http://localhost:8000/auction/comment/" + document.getElementById("hidden-bId"), {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "bId" : document.getElementById("hidden-bId").value,
            "commInput" : document.getElementById("commInput").value
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
}

//댓글 수정
function updateComment() {
    //input 폼으로
    document.getElementById('commContent').innerHTML = "<input type = 'text' id = 'commUpdate' style = 'width: 450px'/>"
    fetch("http://localhost:8000/auction/comment/" + cId, {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "cId" : document.getElementById("hidden-cId").value(),
            "content" : document.getElementById("commUpdate").value()
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
}

//댓글 삭제
function deleteComment() {
    if(window.confirm("정말 삭제하시겠습니까?")) {
        fetch("http://localhost:8000/auction/deleteComment/" + cId, {
        method: "PUT",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "cId" : document.getElementById("hidden-cId").value()
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
    }
}

//bId
window.onload = () => {
    fetch("http://localhost:8000/auction/board", {
        method: "GET",
        headers: {
            "Content-Type":"application/json",
        }
    })
    .then(res => res.json())
    .then(res => {
        console.log(res);
        for(var bId in res) {
            console.log(res[bId]);
        }
    })
}

