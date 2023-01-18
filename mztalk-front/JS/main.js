let page = 1;
//로그인js도 LOCALHOST_URL 바꿔주기
const LOCALHOST_URL = "http://localhost:8000";
const CHAT_URL = "http://localhost:3000";


const AUCTION_URL = `${LOCALHOST_URL}/auction/board-front/`;
const FOLLOW_URL = `${LOCALHOST_URL}/story/main/`;
const BUNG_URL = `${LOCALHOST_URL}/bung/mainBoards-main/`;
const MENTOR_URL = `${LOCALHOST_URL}/mentors/board/latest/`;


document.addEventListener('DOMContentLoaded', function() {
  ROLEVIP();
  checkVip();
});


//로그아웃
function logouts(){
  localStorage.clear();
  deleteCookie('Authorization');
  deleteCookie('RefreshToken');
  location.href="loginpage.html";
}

//쿠키삭제
function deleteCookie(name) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}



//개인페이지
document.getElementById('move-story-service').addEventListener('click', () => {
    const own = localStorage.getItem("userNo");
    localStorage.setItem("own", own);
  
    fetch(`${LOCALHOST_URL}/story/${own}/${page}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
    })
    .then((res) => res.json())
    .then((res) => {
      location.href = "individualpage.html";
    });
  });

//멘토서비스보내기
document.getElementById('move-mentor-service').addEventListener('click', () => {
    fetch(`${LOCALHOST_URL}/mentors`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
      body: JSON.stringify({
        nickname: localStorage.getItem('userNickname'),
        id: localStorage.getItem('userNo'),
      }),
    })
    .then((res) => res.json())
    .then(() => {
      location.href = "mentor-main.html";
    });
  });

//벙서비스보내기
document.getElementById('move-bung-service').addEventListener('click', () => {
    fetch(`${LOCALHOST_URL}/bung/mainBoards`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
    })
    .then((res) => res.json())
    .then(() => {
      location.href = "bung-service-main.html";
    });
  });

//경매서비스 보내기
document.getElementById('move-auction-service').addEventListener('click', () => {
    fetch(`${LOCALHOST_URL}/auction/`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
    })
    .then((res) => res.json())
    .then(() => {
      location.href = "auction.html";
    });
  });

//채팅창 띄우기
document.getElementById('move-chat-service').addEventListener('click', function(){
    window.open(`${CHAT_URL}/chat-friends/?userId=${localStorage.getItem('userNo')}&userNickname=${localStorage.getItem('userNickname')}`, 'width=150', 'height=150');
})


//페이지이동
const moveMainPage = () =>{
    console.log("main : " + localStorage.getItem('authorization'));
    console.log("main : " + localStorage.getItem('refreshToken'));
    console.log("main : " + localStorage.getItem('userNo'));
    console.log("main : " + localStorage.getItem('userNickname'));

    location.href="main.html";
  }



  
// ROLE_VIP
function ROLEVIP(){
  console.log("main ??: " + localStorage.getItem('role'));
  let ROLE_VIP = localStorage.getItem('role');

  if(ROLE_VIP.includes("ROLE_USER")){
      document.querySelector("#advertis");
  }
  if(ROLE_VIP.includes('ROLE_VIP')){
      document.querySelector("#advertis").style.display = "none";
  }
}


const checkVip = () => {
const userNo = localStorage.getItem('userNo');
fetch(`${LOCALHOST_URL}/story/checkVip/${userNo}`, {
  method: "PUT",
  headers: {
    "Content-Type": "application/json",
    Authorization: localStorage.getItem('authorization'),
    RefreshToken: localStorage.getItem('refreshToken')
  },
  body: JSON.stringify({
    userNo: localStorage.getItem('userNo')
  })
})
  .then(res => res.json())
  .then(res => {
    if (res.data === 1) {
      fetch(`${LOCALHOST_URL}/login/role/user/${userNo}`, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json;",
          Authorization: localStorage.getItem('authorization'),
          RefreshToken: localStorage.getItem('refreshToken')
        },
        body: JSON.stringify({
          userNo: localStorage.getItem('userNo')
        })
      });
      alert('VIP이용이 종료되었습니다. 감사합니다');
      localStorage.removeItem('role');
      localStorage.setItem('role', 'ROLE_USER');

      location.href = "main.html";
    }
  });
};


//사진 로테이션
let slideIndex = 0;
showSlides();
function showSlides() {
    let i;
    let slides = document.getElementsByClassName("advertisement");
    //console.log(slides);
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) { slideIndex = 1 }
    slides[slideIndex - 1].style.display = "block";
    setTimeout(showSlides, 10000);
} 
