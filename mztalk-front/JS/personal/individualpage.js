let page = 0;
let loginUser = localStorage.getItem('userNo');
let own = localStorage.getItem("own");

window.onload = function(){
  writeOwn();
  storyLoad();
  profileBox();
  profileName();
  BoardCount();
  FollowCount();
  FollowingCount();
  followButtonStatus();

}


function writeOwn(){

  let own = localStorage.getItem("own");
  let userNo = localStorage.getItem('userNo');

  if(own==userNo){
    
    document.getElementById('ownWrite').innerHTML='<button type="button" style="cursor:pointer;" onclick="writeboard();" id="write_board" class="write_board">글쓰기</button>';


  }else{
    document.getElementById('ownWrite').innerHTML = '';
  }


}



//글쓰기버튼
function writeboard() {

    const open = document.querySelector("#ownWrite button"); //글쓰기버튼
    const modal = document.querySelector(".textmodal");  //글쓸수 있는곳
    const close = document.querySelector(".btn-closee");  //닫기버튼

    //console.log(open);
   console.log("첫번쨰클릭");
  
    open.addEventListener("click", function(){
        console.log("두번쨰클릭");
        
        modal.classList.remove("hidden");
        open.classList.add("hidden");

    });
    close.addEventListener("click", function(){
      modal.classList.add("hidden");
      open.classList.remove("hidden");
  });
    
}


//정보 수정
document.getElementById('profile-edit-btn').addEventListener('click',function(){
    
  location.href="editpage.html";
});

//사진박스
function profileBox(){

    let own = localStorage.getItem("own");

      fetch("http://localhost:8000/story/profile/"+own,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{
      
      //console.log("통신 성공");
      
      let profileImage = res.data;
      //console.log(profileImage);
      if(!res.data){
        document.querySelector('.profile-img-wrap').innerHTML +=
        `
        <img class="profile-image" src='https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png' onerror="this.src='duck.jpg'" id="userProfileImage">
        <input type="hidden" class="imageName" value="profileName"/>
        <input type="hidden" name="bNo" id="bNo" value="own"/>
        `  
     }else{
      let profileUrl = profileImage.postImageUrl;
      let profileName = profileImage.profileImageName;
      let own = profileImage.own

      document.querySelector('.profile-img-wrap').innerHTML +=
      `
      <img class="profile-image" src='${profileUrl}' onerror="this.src='duck.jpg'" id="userProfileImage">
      <input type="hidden" class="imageName" value="${profileName}"/>
      <input type="hidden" name="bNo" id="bNo" value="${own}"/>
      `
     }
      })
    }
  
    


//팔로우 버튼테스트
function followButtonStatus(){
  
  let toUserId = localStorage.getItem("own");
  let fromUserId = localStorage.getItem('userNo');

  fetch("http://localhost:8000/story/followStatus/"+fromUserId+"/"+toUserId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{
  
      let follow = res.data;

      //console.log(follow);

       if(follow>=1){
        
        document.getElementById('followStatus').innerHTML ='';
        //document.getElementById('followStatus').innerHTML ='<button class="cta" onclick="profilecFollow(this);">팔로워</button>';
        document.getElementById('followStatus').innerHTML ='<button class="profile_follow_btn" onclick="profilecFollow(this);" style="background-color: rgba(128, 128, 128, 0.973); color: rgb(255, 255, 255); border: 1px solid rgb(221, 221, 221);">팔로잉</button>';

      } else if(loginUser==own){
        document.getElementById('followStatus').innerHTML = '';
      }else{
        document.getElementById('followStatus').innerHTML =
        `
        <button class="profile_follow_btn" onclick="profilecFollow(this);">
          팔로우
        </button>
        `
      }

      //document.getElementById('followStatus').innerHTML='';
    })
    
  }

//페이지주인은 팔로우 버튼 비활성화
function FollowingButton(){

  if(loginUser!=own){
    document.querySelector('#followStatus').innerHTML =
    `
    <button class="profile_follow_btn" onclick="profilecFollow(this)">
    팔로우
    </button>
    `
   }
 }




    
//이름박스
function profileName(){

    let own = localStorage.getItem("own");

      fetch("http://localhost:8000/story/profile/name/"+own,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{
      
      //console.log("통신 성공");
      
      let profileName = res.data;
      //console.log(profileName);
      let nickname = profileName.nickname;
      document.querySelector('.own_name').innerHTML +=
      `
      ${nickname}
      `
      })
    }



//게시물 갯수
function BoardCount(){

  let own = localStorage.getItem("own");

    fetch("http://localhost:8000/story/profile/boardCount/"+own,{
      method:"GET",
      headers:{
          "Content-Type":"application/json",
          Authorization:localStorage.getItem('authorization'),
          RefreshToken:localStorage.getItem('refreshToken'),
      },
    })
  .then((res)=>res.json())
  .then(res =>{
    
    //console.log("통신 성공");
    
    let board = res.data;
    //console.log(board+"ㅎㅇ");
    document.querySelector('.board_count').innerHTML +=
    `
    ${board}
    `
    })
  }


//팔로잉 명수
function FollowingCount(){

  let own = localStorage.getItem("own");

    fetch("http://localhost:8000/story/profile/followingCount/"+own,{
      method:"GET",
      headers:{
          "Content-Type":"application/json",
          Authorization:localStorage.getItem('authorization'),
          RefreshToken:localStorage.getItem('refreshToken'),
      },
    })
  .then((res)=>res.json())
  .then(res =>{
    
    //console.log("통신 성공");
    
    let following = res.data;
    

    if(document.querySelector('.following_count') != null){
      document.querySelector('.following_count').innerHTML +=
    `
    ${following}
    ` 
   }

    })
  }




//팔로워 명수
function FollowCount(){

  let own = localStorage.getItem("own");

    fetch("http://localhost:8000/story/profile/followerCount/"+own,{
      method:"GET",
      headers:{
          "Content-Type":"application/json",
          Authorization:localStorage.getItem('authorization'),
          RefreshToken:localStorage.getItem('refreshToken'),
      },
    })
  .then((res)=>res.json())
  .then(res =>{
    
    //console.log("통신 성공");
    
    let follower = res.data;
   // console.log(follower);
    document.querySelector('.follower_count').innerHTML +=
    `
    ${follower}
    `
    })
  }



// 퍼블릭인 글 목록들 DIV
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
        //console.log(res.data);

        for(let board of res.data){

          let boardId = board.id;
          let writer = board.writer;
          let privacy = board.privacy;
          let title = board.title;
          let content = board.content;
          let date = board.lastModifiedDate.substr(0,10);
          
          if(privacy.includes("PUBLIC")){
          document.querySelector("#contentList").innerHTML += 
                `<div id="post-div-${boardId}" class="post-div">
                    <table id="post-table">
                        <tr>
                            <td>
                                <div id="category-div">${privacy}
                                </div>
                            </td>
                            <td>
                                <div id="post-title-div">${title}</div>
                            </td>
                            <td>
                                <div id="post-date-div"><br>${date}</div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><br><br>
                                <div id="edit-delete-div">
                                       
                                    <button style="cursor:pointer;" onclick="getBoardDetail(${boardId});" data-bs-target="#exampleModalToggle"
                                    data-bs-toggle="modal" type="button">수정</button>
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
                                    <div id="post-content-input">${content}</div>
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
                           <div id="reply-nickname" onclick="movePage(${reply.replyUserNo});">${reply.replyNickname}</div>
                           <div id="reply-content">${reply.replyContent}</div>
                           <div id="reply-date">${reply.lastModifiedDate.substr(5,5)}</div>
                           <div id="reply-edit-btn"><button onClick="deleteReply(${reply.id})" style="cursor:pointer;" type="button">X</button>
                           <input type="hidden" class='replyDelete' value="${reply.replyUserNo}">
                           </div>
                         `;
                      }
                     )
                   }else if(privacy.includes('SECRET')) {
                      //비밀글 
                      //console.log("되니?");
                      document.querySelector("#contentList").innerHTML += 
                      `<div id="post-div-${boardId}" class="post-div">
                          <table id="post-table">
                              <tr>
                                  <td>
                                      <div id="category-div">${privacy}
                                      </div>
                                  </td>
                                  <td>
                                      <div id="post-title-div">${title}</div>
                                  </td>
                                  <td>
                                      <div id="post-date-div"><br>${date}</div>
                                  </td>
                              </tr>
                              <tr>
                                  <td colspan="3"><br><br>
                                      <div id="edit-delete-div">
                                             
                                          <button style="cursor:pointer;" onclick="getBoardDetail(${boardId});" data-bs-target="#exampleModalToggle"
                                          data-bs-toggle="modal" type="button">수정</button>
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
                                          <div id="post-content-input">${content}</div>
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
                                 <div id="reply-nickname" onclick="movePage(${reply.replyUserNo});">${reply.replyNickname}</div>
                                 <div id="reply-content">${reply.replyContent}</div>
                                 <div id="reply-date">${reply.lastModifiedDate.substr(5,5)}</div>
                                 <div id="reply-edit-btn"><button onClick="deleteReply(${reply.id})" style="cursor:pointer;" type="button">X</button>
                                 <input type="hidden" class='replyDelete' value="${reply.replyUserNo}">
                                 </div>
                               `;
                            })



                   }
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
                writer: localStorage.getItem('userNickname'),
                title: document.getElementById('title-input-text').value,
                content: document.getElementById("content-input-text").value,
                own: localStorage.getItem('own'),
                privacy: privacyBounds.options[privacyBounds.selectedIndex].value,
            })
        })
            .then((res)=>res.json())
            .then(res =>{
                //console.log("res : " + res);
                    // console.log("res.data :" +res.data);
                    // console.log('통신성공');
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
                                          <div id="post-title-div">${board.title}</div>
                                      </td>
                                      <td>
                                          <div id="post-date-div"><br>${board.date}</div>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td colspan="3"><br><br>
                                          <div id="edit-delete-div">
                                              
                                          <button style="cursor:pointer;" onclick="getBoardDetail(${boardId});" data-bs-target="#exampleModalToggle"
                                          data-bs-toggle="modal" type="button">수정</button>
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
                  
          }   
          // location.href="individualpage.html";   
      });
     



//수정창상세보기
function getBoardDetail(boardId){


  let title = document.querySelector('#post-title-div').innerText;
  let content = document.getElementById("post-content-input").innerText;
  let privacy = document.getElementById('category-div').innerText;
  
  

const write_board = document.getElementById('write-board');
const privacyBound = document.getElementById('privacyBound');


  console.log(boardId);
  console.log(title);
  console.log(content);
  console.log(privacy);
     

    if(privacy==='PUBLIC'){
      
      document.querySelector('.modal-content').innerHTML +=
    `
    <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalToggleLabel"><div id="title-div"><input type="text" class="title-input-text" value="${title}"></div></h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div id="modal-body" class="modal-body">
      <input type="text" class="content-input-text" id="contents" value="${content}">
          <input type="hidden" value="${boardId}">
          <div class="selectBox">
              <select id="privacyBound" name="privacyBound" class="select">
              <option disabled value="no">공개범위</option>
              <option value="PUBLIC" selected>전체공개</option>
              <option value="SECRET">비공개</option>
              </select>
          </div>
      </div>
      <div class="modal-footer">
          
          <button style="cursor:pointer;" onClick="modification(${boardId})" type="button">수정</button>
          <div id="btn-div">

      </div>

    </div>
    `
    }

    if(privacy==='SECRET'){
      document.querySelector('.modal-content').innerHTML +=
    `
    <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalToggleLabel"><div id="title-div"><input type="text" class="title-input-text" value="${title}"></div></h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div id="modal-body" class="modal-body">
          
      <input type="text" class="content-input-text" id="contents" value="${content}">
          <input type="hidden" value="${boardId}">
          <div class="selectBox">
              <select id="privacyBound" name="privacyBound" class="select">
              <option disabled value="no">공개범위</option>
              <option value="PUBLIC" >전체공개</option>
              <option value="SECRET" selected>비공개</option>
              </select>
          </div>
      </div>
      <div class="modal-footer">
          
          <button style="cursor:pointer;" onClick="modification(${boardId})" type="button">수정</button>
          <div id="btn-div">

      </div>

    </div>
    `
    }
    //document.querySelector('.modal-content').innerHTML='';
    
}



//글수정      
function modification(boardId){
    //console.log(localStorage.getItem('authorization'));

    if(own != loginUser){
      alert("본인만 수정할 수 있습니다.");
      return;
    }

      
    let id = boardId;
    const privacyBound = document.getElementById('privacyBound');


    // console.log(localStorage.getItem('userNickname'));
    // console.log(document.querySelector('.title-input-text').value);
    // console.log(document.getElementById("contents").value);
    // console.log(localStorage.getItem('own'));
    // console.log(privacyBound.options[privacyBound.selectedIndex].value);
    // console.log(boardId);

      fetch("http://localhost:8000/story/update/"+id,{
        method:"PATCH",
          headers:{
              "Content-Type":"application/json",
              Authorization:localStorage.getItem('authorization'),
              RefreshToken:localStorage.getItem('refreshToken'),
          },
          body:JSON.stringify({
          nickname: localStorage.getItem('userNickname'),
          title: document.querySelector('.title-input-text').value,
          content: document.getElementById("contents").value,
          own: localStorage.getItem('own'),
          privacy: privacyBound.options[privacyBound.selectedIndex].value,
          id : boardId,
        })
    })
    .then((res)=>res.json())
    .then(res =>{

        console.log(res);

        location.href="individualpage.html";   
    }    
)}  


//글삭제
function deleteBoard(boardId){
   
    if(own != loginUser){
      alert("본인만 삭제 할 수 있습니다.");
      return;
    }

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








//프로필 이미지 바꾸기
function profileImageUpload(){
  let userProfileImage = document.getElementById('userProfileImageInput');
  document.getElementById('bNo').value = own;
  if(own != loginUser){
		alert("본인만 수정할 수 있습니다.");
		return;
	}
  userProfileImage.click();

  
  userProfileImage.addEventListener("change",(e)=>{
    let f = e.target.files[0];

    if(!f.type.match("image.*")){
      alert("이미지를 등록해야합니다.");
      return;
    }

  const form = document.getElementById('image-form');

  const payload = new FormData(form);

    fetch('http://localhost:8000/resource/main-image',{
        method: 'POST',
        body: payload,
    })
    .then(res=>{

      fetch("http://localhost:8000/story/profile/"+own,{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
          own: document.getElementById('bNo').value,
    
        }) 
      })
    .then((res)=>res.json())
    .then(res =>{
      // console.log("통신 성공");
      // console.log("res :" +res.data);
      //console.log("profileDtos" + profileDtos);
      let profile = res.data;
      let reader = new FileReader();
      let imageUrl = profile.profileUrl;
      let objectKey = profile.profileImageName;
      reader.onload=(e)=>{
        let userProfileImg = document.getElementById('userProfileImage');
        userProfileImg.src = e.target.result;        
      }
      reader.readAsDataURL(f); 

    //   let profileImage = res.data;
    //   //console.log(profileImage);
    //   if(!res.data){
    //     document.querySelector('.profile-img-wrap').innerHTML +=
    //     `
    //     <img class="profile-image" src='https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png' onerror="this.src='duck.jpg'" id="userProfileImage">
    //     <input type="hidden" class="imageName" value="profileName"/>
    //     <input type="hidden" name="bNo" id="bNo" value="own"/>
    //     `  
    //  }else{
    //   let profileUrl = profileImage.postImageUrl;
    //   let profileName = profileImage.profileImageName;
    //   let own = profileImage.own

    //   document.querySelector('.profile-img-wrap').innerHTML +=
    //   `
    //   <img class="profile-image" src='${profileUrl}' onerror="this.src='duck.jpg'" id="userProfileImage">
    //   <input type="hidden" class="imageName" value="${profileName}"/>
    //   <input type="hidden" name="bNo" id="bNo" value="${own}"/>
    //   `
      
      })
      closePopup('.modal-image');
      
    })
    
  })
};



//댓글쓰기
function addReply(boardId){
  //let replyContent = document.querySelector();
  let replyContent = document.querySelector(`.reply-write-input-${boardId}`);
  let replyButton = document.querySelector('#replyButton');

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
            
            //console.log("res.data: "+ res.data);

            let reply = res.data;
            console.log(reply.replyUserNo);
            
            // let lastModifiedDate = reply.lastModifiedDate;
            document.querySelector(`.reply-div-${boardId}`).innerHTML +=
            `<div>
              <div id="reply-nickname" onclick="movePage(${reply.replyUserNo});">${reply.replyNickname}</div>
              <div id="reply-content">${reply.replyContent}</div>
              <div id="reply-date">${reply.lastModifiedDate}</div>
              <div id="reply-edit-btn"><button onClick="deleteReply(${reply.id})" style="cursor:pointer;" type="button">X</button>
              <input type="hidden" class='replyDelete' value="${reply.replyUserNo}">
              </div>
            </div>`;
          })
          // location.href="individualpage.html";   
        }   
    }
 // )}

    
//댓글삭제    
function deleteReply(Id){

      let replyUserNo = document.querySelector('.replyDelete').value;
      let loginUser = localStorage.getItem('userNo');
     
      console.log(replyUserNo);
      console.log(loginUser);

     
      if(replyUserNo != loginUser){
      alert('댓글 쓴 유저만 지울 수 있습니다.');
      return;
     }
     


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

      location.href="individualpage.html";   
    })
   
  }



  



//팔로워리스트
// document.querySelector("#subscribeBtn1").onclick = (e) => {
//   e.preventDefault();
  
//   let toUserId = localStorage.getItem("own");
 
//   fetch("http://localhost:8000/story/followList/"+toUserId,{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         },
//       })
//     .then((res)=>res.json())
//     .then(res =>{

//       let follower = res.data;
//       document.querySelector(".modal-follow").style.display = "flex";
//       document.querySelector(".follower-list").innerHTML  = '';
//       console.log(follower);


//       for(let i = 0; i < follower.length; i++){
//         console.log("길이"+follower.length );
//         // console.log("follower" + follower);
//         //console.log("뜨니?"+ follower[0].followStatus);
//         //console.log(document.querySelectorAll('.follower__btn'));

//         let followbutton = document.querySelectorAll('.follower__btn');
//         //console.log(follower[i].userNo);

//       document.querySelector(".follower-list").innerHTML +=
//       `
//       <div class="follower__item">
//           <div class="follower__img"><img class="profile-image" src='${follower[i].imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"></div>
//           <input type="hidden" class="imageName" value="${follower[i].imageName}"/>
//           <input type="hidden" name="bNo" id="bNo" value="${follower[i].userNo}"/>
//           <div class="follower__text">
//               <h2>${follower[i].userNickname}</h2>
//               <input type="hidden" name="userNo" value="${follower[i].userNo}"/>
//           </div>
//           <div class="follower__btn">
//             <button onclick="movePage(${follower[i].userNo});">페이지이동</button>
//           </div>
//       </div> 
//       `;
      
        
//     } 
//     })

// };
document.querySelector("#subscribeBtn1").addEventListener("click", (e) => {
  e.preventDefault();
  
  const toUserId = localStorage.getItem("own");
 
  fetch(`http://localhost:8000/story/followList/${toUserId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: localStorage.getItem('authorization'),
            RefreshToken: localStorage.getItem('refreshToken'),
        },
      })
    .then((res) => res.json())
    .then((res) => {
      const follower = res.data;
      document.querySelector(".modal-follow").style.display = "flex";
      document.querySelector(".follower-list").innerHTML = '';

      for (let i = 0; i < follower.length; i++) {
        const followbutton = document.querySelectorAll('.follower__btn');
        document.querySelector(".follower-list").innerHTML +=
        `
        <div class="follower__item">
            <div class="follower__img">
              <img class="profile-image" src='${follower[i].imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage">
            </div>
            <input type="hidden" class="imageName" value="${follower[i].imageName}"/>
            <input type="hidden" name="bNo" id="bNo" value="${follower[i].userNo}"/>
            <div class="follower__text">
                <h2>${follower[i].userNickname}</h2>
                <input type="hidden" name="userNo" value="${follower[i].userNo}"/>
            </div>
            <div class="follower__btn">
              <button onclick="movePage(${follower[i].userNo});">페이지이동</button>
            </div>
        </div> 
        `;
      } 
    });
});





function closeFollow() {
  document.querySelector(".modal-follow").style.display = "none";
}
document.querySelector(".modal-follow").addEventListener("click", (e) => {
  if (e.target.tagName !== "BUTTON") {
    document.querySelector(".modal-follow").style.display = "none";
  }
});







//팔로잉리스트
document.querySelector("#subscribeBtn").onclick = (e) => {
    e.preventDefault();

    let fromUserId = localStorage.getItem("own");

    fetch("http://localhost:8000/story/followingList/"+fromUserId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{
      let following = res.data;
      
      document.querySelector(".modal-following").style.display = "flex";

      document.querySelector(".following-list").innerHTML  = '';
      for(let i = 0; i < following.length; i++){

      document.querySelector(".following-list").innerHTML +=
      `
      <div class="following__item">
          <div class="following__img"><img class="profile-image" src='${following[i].imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"></div>
          <input type="hidden" class="imageName"  value="${following[i].imageName}"/>
          <input type="hidden" name="bNo" id="bNo" value="${following[i].userNo}"/>
          <div class="following__text">
              <h2>${following[i].userNickname}</h2>
              
          </div>
          <div class="following__btn">
            <button onclick="movePage(${following[i].userNo});">페이지이동</button>
          </button>
          
          </div>
      </div> 
      `;

    }
    })

  };


  function closeFollow() {
    document.querySelector(".modal-following").style.display = "none";
  }
  document.querySelector(".modal-following").addEventListener("click", (e) => {
    if (e.target.tagName !== "BUTTON") {
      document.querySelector(".modal-following").style.display = "none";
    }
  });

  //팝업
  function popup(obj) {
    //console.log(obj);
    document.querySelector(obj).style.display = "flex";
  }
  function closePopup(obj) {
   // console.log(2);
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




  //팔로우리스트에서의 버튼
  function clickFollow(e) {

    console.log(e);
    let _btn = e;
    console.log(_btn.textContent);
    let userButton = document.querySelector('.following_button');
    console.log("버튼"+ _btn.value);

    if (_btn.textContent === "팔로잉") {

      let fromUserId = localStorage.getItem('userNo');
      let toUserId = _btn.value;

      fetch("http://localhost:8000/story/follow/"+fromUserId+"/"+toUserId,{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{
             
      //console.log(res);
      
      _btn.textContent = "팔로우";
      _btn.style.backgroundColor = "#0095f6";
      _btn.style.color = "#fff";
      _btn.style.border = "1px solid #ddd";
    }) 
    
    
    } else {
      _btn.textContent = "팔로잉";
      _btn.style.backgroundColor = "rgba(128, 128, 128, 0.973)";
      _btn.style.color = "#fff";
      _btn.style.border = "0";
    }
  }
  
// console.log("로칼:(2)"+localStorage.getItem("own"));
// console.log("로칼조현재:(1)"+localStorage.getItem('userNo'));


//다른사람 피드 팔로우 기능구현
  function profilecFollow(e) {

    //console.log("배한성:(2)"+localStorage.getItem("own"));
    //console.log("조현재:(1)"+localStorage.getItem('userNo'));

    //console.log(e);
    let _btn = e;
    //console.log(_btn.textContent);

    if (_btn.textContent.includes("팔로우")) {
      
      let fromUserId = localStorage.getItem('userNo');
      let toUserId = localStorage.getItem("own");

      fetch("http://localhost:8000/story/follow/"+toUserId+"/"+fromUserId,{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{

      _btn.textContent = "팔로잉";
      _btn.style.backgroundColor = "rgba(128, 128, 128, 0.973)";
      _btn.style.color = "#fff";
      _btn.style.border = "1px solid #ddd";


    })

    } else {
      let toUserId = localStorage.getItem("own");
      let fromUserId = localStorage.getItem('userNo');
   
      fetch("http://localhost:8000/story/follow/"+toUserId+"/"+fromUserId,{
        method:"delete",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{
      // FollowCount();
      // FollowingCount();
      //console.log(res);

      _btn.textContent = "팔로우";
      _btn.style.backgroundColor = "#0095f6";
      _btn.style.color = "#fff";
      _btn.style.border = "0";

   
    })
    
    }

  }




//로그아웃
function logout(){

  localStorage.clear();
  
  deleteCookie('Authorization');
  deleteCookie('RefreshToken');

  location.href="loginpage.html";


}
//쿠키삭제
function deleteCookie(name) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}


//페이지이동 userNo
const movePage = (userNo) =>{
  localStorage.removeItem('own');
  localStorage.setItem('own', userNo);
  location.href="individualpage.html";
}










