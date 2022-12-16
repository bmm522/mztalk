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

//팝업 관련

