let page = 0;
let loginUser = localStorage.getItem('userNo');
let own = localStorage.getItem("own");

window.onload = function(){
  storyLoad();
  
}


function writeboard() {
    
    const open = document.querySelector(".write_board"); //글쓰기버튼
    const modal = document.querySelector(".textmodal");  //글쓸수 있는곳
    const close = document.querySelector(".btn-close");  //닫기버튼

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



//개인페이지
document.getElementById('profile-edit-btn').addEventListener('click',function(){
    
    location.href="editpage.html";
});

function storyLoad() {
  
  fetch("http://localhost:8000/story/"+own,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
      .then((res)=> res.json())
      .then(res=>{       
        console.log(res.data);

        for(let board of res.data){
          let boardId = board.id;
          let nickname = board.nickname;
          let privacy = board.privacy;
          let title = board.title;
          let content = board.content;
          let date = board.lastModifiedDate;
          
        document.querySelector("#contentList").innerHTML += 
                `<div id="post-div-${boardId}" class="post-div">
                    <table id="post-table">
                        <tr>
                            <td>
                                <div id="category-div">${board.privacy}
                                </div>
                            </td>
                            <td>
                                <div id="post-title-div"><br>&nbsp&nbsp${board.title}</div>
                            </td>
                            <td>
                                <div id="post-date-div"><br>${board.date}</div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><br><br>
                                <div id="edit-delete-div">
                                    <button style="cursor:pointer;" type="button">수정</button>
                                    <button style="cursor:pointer;" onClick="deleteBoard(${boardId})" type="button">삭제</button>
                                </div>
                                <div id=post-hr>
                                    <hr>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div id="post-content">
                                    <div id="post-content-input">${board.content}</div>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <div id="reply-div" class="reply-div-${boardId}">
                      <div>

                      </div>
                      </div>
                      <div id="reply-write-div">
                          <table>
                              <tr>
                                  <td>
                                      <div id="reply-write-box"><input type="text" class="reply-write-input-${boardId}" id="reply-write-input"></div>
                      </div>
                      </td>
                      <td>  
                          <div id="reply-write-btn"><button onClick="addReply(${boardId})" id="replyButton" style="cursor:pointer;" type="button">등록</button></div>
                      </td>
                      </tr>
                      </table>
                    </div>
                   </div>
                    `;
                    
                     board.replyList.forEach((reply)=>{
                     document.querySelector(`.reply-div-${boardId}`).innerHTML +=
                         `
                           <div id="reply-nickname">${reply.replyNickname}</div>
                           <div id="reply-content">${reply.replyContent}</div>
                           <div id="reply-date">${reply.lastModifiedDate}</div>
                           <div id="reply-edit-btn"><button onClick="deleteReply(${reply.id})" style="cursor:pointer;" type="button">X</button></div>
                         `
                      }
                     )
                      
              }
             
            
           })
}
      



//글쓰기

const write_board = document.getElementById('write-board');
const privacyBounds = document.getElementById('privacyBounds');

  //console.log("개인 : " + localStorage.getItem('authorization'));
  //console.log("개인 : " + localStorage.getItem('refreshToken'));
  //console.log("개인 : " + localStorage.getItem('userNo'));
  //console.log("개인 : " + localStorage.getItem('userNickname'));
  //console.log("페이지주인: " + localStorage.getItem('own'));

write_board.addEventListener('click', function(){
    //console.log("클릭됨??");
   
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
                privacy: privacyBounds.options[privacyBounds.selectedIndex].value,
            })
        })
            .then((res)=>res.json())
            .then(res =>{
                //console.log("res : " + res);
                    console.log("res.data :" +res.data);
                    console.log('통신성공');
                    //글썻을떄
                    const open = document.querySelector(".write_board"); //글쓰기버튼
                    const modal = document.querySelector(".textmodal");
                    modal.classList.add("hidden");
                    open.classList.remove("hidden");
                    let board = res.data;
                    let boardId = board.id;
                    let title = board.title;
                    let content = board.content;
                    let date = board.createDate;
                  document.querySelector("#contentList").innerHTML += 
                          `<div id="post-div-${boardId}" class="post-div">
                              <table id="post-table">
                                  <tr>
                                      <td>
                                          <div id="category-div">${board.privacy}
                                          </div>
                                      </td>
                                      <td>
                                          <div id="post-title-div"><br>&nbsp&nbsp${board.title}</div>
                                      </td>
                                      <td>
                                          <div id="post-date-div"><br>${board.date}</div>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td colspan="3"><br><br>
                                          <div id="edit-delete-div">
                                              <button style="cursor:pointer;" type="button">수정</button>
                                              <button style="cursor:pointer;" onClick="deleteBoard(${boardId})" type="button">삭제</button>
                                          </div>
                                          <div id=post-hr>
                                              <hr>
                                          </div>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td colspan="3">
                                          <div id="post-content">
                                              <div id="post-content-input">${board.content}</div>
                                          </div>
                                      </td>
                                  </tr>
                              </table>
                              <div id="reply-div" class="reply-div-${boardId}">
                                  
                              </div>
                              <div id="reply-write-div">
                                  <table>
                                      <tr>
                                          <td>
                                              <div id="reply-write-box"><input type="text" class="reply-write-input-${boardId}" id="reply-write-input"></div>
                              </div>
                              </td>
                              <td>
                                  <div id="reply-write-btn"><button id="replyButton" onClick="addReply(${boardId})" style="cursor:pointer;" type="button">등록</button></div>
                              </td>
                              </tr>
                              </table>
                          </div>
                      </div>`;
               })
               location.href="individualpage.html";      
          }   
            
      });
     

      





      
//글삭제
function deleteBoard(boardId){
   
    console.log("찍힘?123123123");

    console.log(boardId);
  
      fetch("http://localhost:8000/story/delete/"+boardId,{
          method:"PATCH",
          headers:{
              "Content-Type":"application/json",
              Authorization:localStorage.getItem('authorization'),
              RefreshToken:localStorage.getItem('refreshToken'),
          },
        })
      .then((res)=>res.json())
      .then(res =>{
        
        
         
      })
      location.href="individualpage.html";  
    }








































//댓글쓰기
function addReply(boardId){
  //let replyContent = document.querySelector();
  let replyContent = document.querySelector(`.reply-write-input-${boardId}`);
  let replyButton = document.querySelector('#replyButton');
  //let replyButton = document.getElementById('replyButton');
  // let replyList = document.getquerySelector(`reply-div-${boardId}`);
  //let replyList = document.getElementById('reply-div');
  //let id = localStorage.getItem('userNo');
  console.log(replyButton);
  //replyButton.addEventListener('click', function(){
  console.log("클림됨?");
      console.log(replyContent);
        if(replyContent.value === ''){
          alert("댓글을 작성해주세요!");
        }else{
          fetch("http://localhost:8000/story/board/"+boardId+"/reply",{
              method:"POST",
              headers:{
                  "Content-Type":"application/json",
                  Authorization:localStorage.getItem('authorization'),
                  RefreshToken:localStorage.getItem('refreshToken'),
              },
              body:JSON.stringify({
                  replyNickname: localStorage.getItem('userNickname'),
                  replyContent: replyContent.value,
                  replyUserNo: localStorage.getItem("userNo"),
                })
            })
          .then((res)=>res.json())
          .then(res =>{

            console.log("통신성공?");
            
            console.log("res.data: "+ res.data);

            let reply = res.data;
            //document.getElementsByClassName(`reply-div-${boardId}`).innerHTML +=
            document.querySelector(`.reply-div-${boardId}`).innerHTML +=
            `<div>
              <div id="reply-nickname">${reply.replyNickname}</div>
              <div id="reply-content">${reply.replyContent}</div>
              <div id="reply-date">${reply.lastModifiedDate}</div>
              <div id="reply-edit-btn"><button onClick="deleteReply(${reply.id})" style="cursor:pointer;" type="button">X</button></div>
            </div>`;
          })
          location.href="individualpage.html";   
        }   
    }
 // )}


    
//댓글삭제    
function deleteReply(Id){
  console.log('fgfg   : '+Id);
  console.log("찍힘?123123123");
 
  console.log('loginUser   :' + loginUser);

    fetch("http://localhost:8000/story/board/"+Id+"/reply",{
        method:"delete",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{

      console.log("통신성공?");
      location.href="individualpage.html";   
    })
   
  }



    











































//팔로우 기능구현














//팔로우
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
  
  function profilecFollow(e) {
    console.log(e);
    let _btn = e;
    console.log(_btn.textContent);
    if (_btn.textContent === "팔로우") {
      _btn.textContent = "팔로잉";
      _btn.style.backgroundColor = "rgba(128, 128, 128, 0.973)";
      _btn.style.color = "#fff";
      _btn.style.border = "1px solid #ddd";
    } else {
      _btn.textContent = "팔로우";
      _btn.style.backgroundColor = "#0095f6";
      _btn.style.color = "#fff";
      _btn.style.border = "0";
    }
  }


  function follow(check, userno){
    //true -> follow하기
    //false -> unfollow하기
    let url = "/follow"/+userno
    if(check){

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




