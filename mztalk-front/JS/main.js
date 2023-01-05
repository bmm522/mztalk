let page = 1;

const LOCALHOST_URL = "http://localhost:8000";
const AUCTION_URL = `${LOCALHOST_URL}/auction/board-front/`;
const FOLLOW_URL = `${LOCALHOST_URL}/story/main/`;
const BUNG_URL = `${LOCALHOST_URL}/bung/mainBoards-main/`;
const MENTOR_URL = `${LOCALHOST_URL}/mentors/board/latest/`;



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
    window.open(`http://localhost:3000/chat-friends/?userId=${localStorage.getItem('userNo')}&userNickname=${localStorage.getItem('userNickname')}`, 'width=150', 'height=150');
})


//페이지이동
const moveMainPage = () =>{
    console.log("main : " + localStorage.getItem('authorization'));
    console.log("main : " + localStorage.getItem('refreshToken'));
    console.log("main : " + localStorage.getItem('userNo'));
    console.log("main : " + localStorage.getItem('userNickname'));

    location.href="main.html";
  }



