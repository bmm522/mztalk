let page = 0;

function writeboard() {
    
    const open = document.querySelector(".write_board");
    const modal = document.querySelector(".textmodal");
    const close = document.querySelector(".btn-close");

    //console.log(open);

    open.addEventListener("click", function(){
        //console.log(open);
        
        modal.classList.remove("hidden");
        open.classList.add("hidden");

    });
    close.addEventListener("click", function(){
        modal.classList.add("hidden");
        open.classList.remove("hidden");
    });
}
writeboard();

// console.log(localStorage.getItem('userNickname'));
// console.log(localStorage.getItem('userNo')); // 세션값
// console.log(localStorage.getItem('own')); //파라미터 

window.onload=function(){
    console.log("개인 : " + localStorage.getItem('authorization'));
    console.log("개인 : " + localStorage.getItem('refreshToken'));
    console.log("개인 : " + localStorage.getItem('userNo'));
    console.log("개인 : " + localStorage.getItem('userNickname'));
    console.log("페이지주인: " + localStorage.getItem('own'));

    const own = document.getElementById('#own');
    
    //fetch쓰기


}

//개인페이지
document.getElementById('profile-edit-btn').addEventListener('click',function(){
    
    location.href="editpage.html";

    // fetch("http://localhost:8000/story/"+own,{
    //     method:"GET",
    //     headers:{
    //         "Content-Type":"application/json",
    //         Authorization:localStorage.getItem('authorization'),
    //         RefreshToken:localStorage.getItem('refreshToken'),
    //     },
    // })
    // .then((res)=> res.json())
    // .then(res=>{
    //     console.log("자료있니?" +res.data);
    //     console.log("!!!!!!!!!!");
    //     console.log(own);
    //     location.href="individualpage.html";
    // })


});





const write_board = document.getElementById('write-board');
const privacyBounds = document.getElementById('privacyBounds');
//글쓰기
write_board.addEventListener('click', function(){
    console.log("클릭됨??");
    
    if(privacyBounds.options[privacyBounds.selectedIndex].value === 'no'){
        alert("공개범위를 설정하세요");
    }else{

        fetch("http://localhost:8000/story/saveForm",{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            },
            body:JSON.stringify({
                nickname: localStorage.getItem('userNickname'),
                title: document.getElementById('title-input-text').value,
                content: document.getElementById("content-input-text").value,
                own: localStorage.getItem('own'),
                privacy: privacyBounds.options[privacyBounds.selectedIndex].value
            })
        })
            .then((res)=>res.json())
            .then(res =>{
                console.log("res : " + res);
                if(res > 0){
                    console.log('통신성공');
                   
                } else {
                    console.log('실패');
                }
            })
        
    }        
});

//구독
document.querySelector("#subscribeBtn").onclick = (e) => {
    e.preventDefault();
    document.querySelector(".modal-follow").style.display = "flex";
  };

  function closeFollow() {
    document.querySelector(".modal-follow").style.display = "none";
  }
  document.querySelector(".modal-follow").addEventListener("click", (e) => {
    if (e.target.tagName !== "BUTTON") {
      document.querySelector(".modal-follow").style.display = "none";
    }
  });

  //팝업
  function popup(obj) {
    console.log(obj);
    document.querySelector(obj).style.display = "flex";
  }
  function closePopup(obj) {
    console.log(2);
    document.querySelector(obj).style.display = "none";
  }

  //회원정보창
  document.querySelector(".modal-info").addEventListener("click", (e) => {
    if (e.target.tagName === "DIV") {
      document.querySelector(".modal-info").style.display = "none";
    }
  });

  //이미지
  document.querySelector(".modal-image").addEventListener("click", (e) => {
    if (e.target.tagName === "DIV") {
      document.querySelector(".modal-image").style.display = "none";
    }
  });


  function clickFollow(e) {
    console.log(e);
    let _btn = e;
    console.log(_btn.textContent);
    if (_btn.textContent === "팔로잉") {
      _btn.textContent = "팔로우";
      _btn.style.backgroundColor = "#0095f6";
      _btn.style.color = "#fff";
      _btn.style.border = "1px solid #ddd";
    } else {
      _btn.textContent = "팔로잉";
      _btn.style.backgroundColor = "rgba(128, 128, 128, 0.973)";
      _btn.style.color = "#fff";
      _btn.style.border = "0";
    }
  }
  


















//결국엔 페이지 주인은 own
//접속자도 userNo

//접속자의 own으로 가는거니까




// function replyList(){
//    console.log('실행됨');
//     for(let i = 0 ; i < 10 ; i++){
//         document.getElementById('reply-div').innerHTML += "<div> <div id='reply-nickname'>{작성자 닉네임}</div> <div id='reply-content'>{댓글 내용}</div><div id='reply-date'>{date}</div> <div id='reply-edit-btn'><button style='cursor:pointer;' type='button'>X</button></div></div>";



//         // const reply = document.createElement("div");
//         // reply.id='reply-nickname';
//         // reply.innerHTML = "작성자";
//         // const reply2 = document.createElement("div");
//         // reply.id='reply-content';
//         // reply.innerHTML = "댓글내용";
//         // const reply3 = document.createElement("div");
//         // reply3.id='reply-date';
//         // reply.innerHTML = "date";
//         // // const reply4 = document.createElement("div").attributes('reply-edit-btn');
//         // // reply.innerHTML = "X";
        


//         // document.getElementById('reply-div').appendChild(reply);
//         // document.getElementById('reply-div').appendChild(reply2);
//         // document.getElementById('reply-div').appendChild(reply3);
//         // document.getElementById('reply-div').appendChild(reply4);
//     }
// }


// (1) 유저 프로파일 페이지 구독하기, 구독취소
// function toggleSubscribe(obj) {
//     if ($(obj).text() === "구독취소") {
//         $(obj).text("구독하기");
//         $(obj).toggleClass("blue");
//     } else {
//         $(obj).text("구독취소");
//         $(obj).toggleClass("blue");
//     }
// }

// // (2) 구독자 정보  모달 보기
// function subscribeInfoModalOpen() {
//     document.getElementById("modal-subscribe").css("display", "flex");
// }

// function getSubscribeModalItem() {

// }


// (3) 구독자 정보 모달에서 구독하기, 구독취소
// function toggleSubscribeModal(obj) {
//     if ($(obj).text() === "구독취소") {
//         $(obj).text("구독하기");
//         $(obj).toggleClass("blue");
//     } else {
//         $(obj).text("구독취소");
//         $(obj).toggleClass("blue");
//     }
// }

// (4) 유저 프로파일 사진 변경 (완)
// function profileImageUpload() {
//     $("#userProfileImageInput").click();

//     $("#userProfileImageInput").on("change", (e) => {
//         let f = e.target.files[0];

//         if (!f.type.match("image.*")) {
//             alert("이미지를 등록해야 합니다.");
//             return;
//         }

//         // 사진 전송 성공시 이미지 변경
//         let reader = new FileReader();
//         reader.onload = (e) => {
//             $("#userProfileImage").attr("src", e.target.result);
//         }
//         reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
//     });
// }

// (5) 사용자 정보 메뉴 열기 닫기
// function popup(obj) {
//     $(obj).css("display", "flex");
// }

// function closePopup(obj) {
//     $(obj).css("display", "none");
// }

// (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
// function modalInfo() {
//     $(".modal-info").css("display", "none");
// }


// (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
// function modalImage() {
//     $(".modal-image").css("display", "none");
// }

// (8) 구독자 정보 모달 닫기
// function modalClose() {
//     $(".modal-subscribe").css("display", "none");
//     location.reload();
// }




