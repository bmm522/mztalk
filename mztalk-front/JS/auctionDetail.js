const slides = document.querySelector('.slides');
const slideImg = document.querySelectorAll('.slides li');
let currentIdx = 0;
const slideCount = slideImg.length; //슬라이드 개수
const prev = document.querySelector('.prev');
const next = document.querySelector('.next');
const slideWidth = 300;
const slideMargin = 100;

//슬라이드 관련
//전체 슬라이드 컨테이너 넓이 설정
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
ㅔㅁㅎㄷ=1
//입찰가 전달
document.getElementById("priceSubmitBtn").addEventListener('click', function() {
    const linkArr = document.location.href.split("/");
    const bId = linkArr[linkArr.length - 1];
    console.log(Number(document.getElementById('inputPrice').value));
    // console.log(bId);

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
