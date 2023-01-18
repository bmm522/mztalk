window.onload =() =>{
    console.log("수정페이지 : " + localStorage.getItem('authorization'));
    console.log("수정페이지 : " + localStorage.getItem('refreshToken'));
    console.log("수정페이지 : " + localStorage.getItem('userNo'));
    console.log("수정페이지 : " + localStorage.getItem('userNickname'));
    console.log("수정페이지 : " + localStorage.getItem('role'));
    profileBox();
    noSocial();
    checkVipCheck();
    console.log(role);
}

const checkVipCheck = ()=>{

    let userNo = localStorage.getItem('userNo');
    
    fetch(`${LOCALHOST_URL}/story/vip/${userNo}`,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{

      if(res.data != null){
        let VIPCHECK = res.data;
        let createDate = VIPCHECK.createDate.substr(0,10);
        let vipDate = VIPCHECK.vipDate.substr(0,10);
        // console.log(createDate);
         console.log(vipDate);
        let userNickname = localStorage.getItem('userNickname');
        document.querySelector('#vipDate').innerHTML =
        `
        <div id="viptime">
        <a id="vipnickname">${userNickname}</a> 
          구독 기간은<br>
        <a>${createDate} 부터
        <br>${vipDate}입니다.</a>
        </div>
        `
      }else{

        
      }
  })
}

const image = document.querySelector(".modal-image");

function modalImage() {
	image.style.display = "none";
}
function popup(image) {
    image.style.display = "flex";
}
  //프로필 사진
function profileBox(){
    let own = localStorage.getItem("own");
      fetch(`${LOCALHOST_URL}/story/profile/${own}`,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
    .then((res)=>res.json())
    .then(res =>{
      let profileImage = res.data;
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

async function ch_nickName() {
  const nickname = document.getElementById('nickname').value;
  const userNo = localStorage.getItem('userNo');
  const id = localStorage.getItem('userNo');
  const writer = document.getElementById('nickname').value;
  const own = localStorage.getItem('userNo');
  const replyNickname = document.getElementById('nickname').value;
  const replyUserNo = localStorage.getItem('userNo');
  if (!confirm('닉네임 변경시 로그아웃됩니다. 바꾸시겠습니까?')) {
    return;
  }
  try {
    await fetch(`${LOCALHOST_URL}/login/user/nickname`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
      body: JSON.stringify({
        nickname,
        userNo,
      }),
    });
    await fetch(`${LOCALHOST_URL}/story/nickname`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
      body: JSON.stringify({
        replyNickname,
        replyUserNo,
      }),
    });  
    await fetch(`${LOCALHOST_URL}/auction/nickname`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
      body: JSON.stringify({
        nickname,
        userNo,
      }),
    });
    await fetch(`${LOCALHOST_URL}/story/board/nickname`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
      body: JSON.stringify({
        writer,
        own,
      }),
    });
    await fetch(`${LOCALHOST_URL}/mentors/mentee/${id}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem('authorization'),
        RefreshToken: localStorage.getItem('refreshToken'),
      },
      body: JSON.stringify({
        nickname,
        id,
      }),
    });
    alert('닉네임변경완료');
    localStorage.clear();
    deleteCookie('Authorization');
    deleteCookie('RefreshToken');

    location.href = "loginpage.html";
  } catch (error) {
    console.error(error);
  }
}

// async function ch_nickName() {
//   const nickname = document.getElementById('nickname').value;
//   const userNo = localStorage.getItem('userNo');
//   if (!confirm('닉네임 변경시 로그아웃됩니다. 바꾸시겠습니까?')) {
//     return;
//   }

//   const headers = {
//     "Content-Type": "application/json",
//     Authorization: localStorage.getItem('authorization'),
//     RefreshToken: localStorage.getItem('refreshToken'),
//   };
//   const body = JSON.stringify({ nickname, userNo });

//   try {
//     const endpoints = [
//       `${LOCALHOST_URL}/login/user/nickname`,
//       `${LOCALHOST_URL}/story/nickname`,
//       `${LOCALHOST_URL}/story/board/nickname`,
//       `${LOCALHOST_URL}/mentee/{id}`,
//     ];
//     for (const endpoint of endpoints) {
//       await fetch(endpoint, { method: "PATCH", headers, body });
//     }

//     alert('닉네임변경완료');
//     localStorage.clear();
//     deleteCookie('Authorization');
//     deleteCookie('RefreshToken');
//     location.href = "loginpage.html";
//   } catch (error) {
//     console.error(error);
//     alert('닉네임변경실패');
//   }
// }




//비밀번호변경
document.getElementById('password').addEventListener('keyup',function(){
  isValidPassword();
});

document.getElementById('password').addEventListener('blur',function(){
  passwordBlurText();
});

const isValidPassword = () =>{
	let inputPassword = document.getElementById('password').value;
	console.log(inputPassword);
	let checkPasswordResult = document.getElementById('checkPw');
	 var pw = inputPassword;
	 //console.log("pw : " + pw);
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	 if(pw.length < 8 || pw.length > 20){
		 //console.log(1);
		 checkPasswordResult.innerHTML= '8자리~20자리 이내로 입력하세요';
		 checkPasswordResult.style.color='red';
		 document.getElementById('checkPasswordResult').value = "fail";
	 }
	 else if(pw.search(/\s/) != -1){
		 checkPasswordResult.innerHTML= '공백은 허용되지 않습니다.';
		 checkPasswordResult.style.color='red';
		 document.getElementById('checkPasswordResult').value = "fail";
	 }
	 else if(num < 0 || eng < 0 || spe < 0 ){
		 checkPasswordResult.innerHTML= '영문,숫자,특수문자를 혼합하여 입력해주세요.';
		 checkPasswordResult.style.color='red';
		 document.getElementById('checkPasswordResult').value = "fail";
	 }
	 else{
	 checkPasswordResult.innerHTML= '올바른 비밀번호 형식입니다.';
		 checkPasswordResult.style.color='green';
		 document.getElementById('checkPasswordResult').value = "success";
	 }
	
}

const passwordBlurText = () => {
	 let checkPasswordResult = document.getElementById('checkPw');
	 checkPasswordResult.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
	 
}

document.getElementById('re_password').addEventListener('keyup',function(){
  isValidCheckPassword();
});

document.getElementById('re_password').addEventListener('blur',function(){
  rePasswordBlurText();
});

const isValidCheckPassword = () =>{
	let checkRePassword = document.getElementById('re_password').value;
	let checkPassword = document.getElementById('password').value;
	let checkRePw = document.getElementById('checkRePw');

	if(checkRePassword == checkPassword){
		checkRePw.innerHTML="일치합니다.";
		checkRePw.style.color = "green";
		document.getElementById('checkRePasswordResult').value = "success";
    document.querySelector('#password_button_check').disabled = false;
    
	} else {
		checkRePw.innerHTML="일치하지 않습니다.";
		checkRePw.style.color = "red";
		document.getElementById('checkRePasswordResult').value = "fail";
    document.querySelector('#password_button_check').disabled = true;
	}
}

const rePasswordBlurText = () => {
	let checkRePw = document.getElementById('checkRePw');
	checkRePw.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
}




//비밀번호 변경
//body : prePassword, newPassword, id
function ch_Password(){
  let prePassword = document.getElementById('passwd').value;
  let newPassword= document.getElementById('re_password').value;

  let id = localStorage.getItem('userNo');
  
  if(confirm('비밀번호 변경시 로그아웃됩니다. 바꾸시겠습니까?')){
  
  fetch(`${LOCALHOST_URL}/login/new-password`,{
        method:"PATCH",
          headers:{
              "Content-Type":"application/json",
              Authorization:localStorage.getItem('authorization'),
              RefreshToken:localStorage.getItem('refreshToken'),
          },
          body:JSON.stringify({
            prePassword : document.getElementById('passwd').value,
            newPassword : document.getElementById('re_password').value,
            id : localStorage.getItem('userNo'),
        })
    })
    .then((res)=>res.json())
    .then(res =>{
      if(res==1){
      alert('비밀번호 변경완료');

      localStorage.clear();
      
      deleteCookie('Authorization');
      
      deleteCookie('RefreshToken');

      location.href="loginpage.html";
      }else{
        alert('기존 비밀번호가 맞지 않습니다.');
      }

    })
  }
};














//이메일
document.getElementById('email-box').addEventListener('keyup', function(){
  isVaildEmail();
});
document.getElementById('email-box').addEventListener('blur', function(){
  emailBlurText();
});
const isVaildEmail =  () => {
  let email = document.getElementById('email-box').value;
  const exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
  let checkEmail = document.getElementById('checkEmail');
    
  if(exptext.test(email)==false){  
      checkEmail.innerHTML= '이메일 형식이 올바르지 않습니다.';
      checkEmail.style.color='red';
      document.getElementById('checkEmailResult').value = "fail";
    } else{
      fetch(`${LOCALHOST_URL}/login/register/email/${email}`,{
        method:"GET"
      })
      .then((res)=>res.json())
      .then(res=>{
        console.log(res.checkResult);
        if(res.checkResult == 'available'){
          checkEmail.innerHTML= '사용가능한 이메일 입니다.';
          checkEmail.style.color='green';
          document.getElementById('checkEmailResult').value = "success";
        } else{
          checkEmail.innerHTML= '중복된 이메일 입니다.';
          checkEmail.style.color='red';
          document.getElementById('checkEmailResult').value = "fail";
        }
      })
    
    }
}

const emailBlurText = () =>{
  document.getElementById('checkEmail').innerHTML = '';
}

let emailAuthCode = '';

document.getElementById('email_check').addEventListener('click', function(){
  if(document.getElementById('checkEmailResult').value == "fail" || document.getElementById('checkEmailResult').value =='none'){
  
    alert('이메일을 제대로 입력 후에 눌러주세요');
    document.getElementById('email-box').focus();
  
  } else{
    fetch(`${LOCALHOST_URL}/login/register/auth-code/`+document.getElementById('email-box').value, {
    method:"GET",
     })
    .then((res)=> res.json())
    .then(res=>{
     console.log(res.authCode);
      if(!res.authCode == 'It`s not an appropriate email format'){
        
        alert('이메일 형식이 올바르지 않습니다.');

      } else {
        
        emailAuthCode = res.authCode;
        document.querySelector('#final_check').disabled = false;
        alert('작성하신 이메일로 인증코드를 전송했습니다.');
       
        
      }
    })
  }
});

document.getElementById('auth-box').addEventListener('keyup', function(){
  isValidAuthCode();
});

document.getElementById('auth-box').addEventListener('blur', function(){
  authBlurText();
});



const isValidAuthCode = () => {
  let authCode = document.getElementById('auth-box').value;
  let checkAuth = document.getElementById('checkAuth');
  if(emailAuthCode != authCode){
    checkAuth.innerHTML = '코드가 일치하지 않습니다.';
    checkAuth.style.color = 'red';
    document.getElementById('checkAuthResult').value = "fail";
  } else {
    checkAuth.innerHTML = '코드가 일치 합니다.';
    checkAuth.style.color = 'green';
    document.getElementById('checkAuthResult').value = "success";
  }
}
//checkAuthResult
const authBlurText = () =>{
  document.getElementById('checkAuth').innerHTML = '';
}
//이메일 변경
function ch_email(){ 

    fetch(`${LOCALHOST_URL}/login/user/email`,{
          method:"PATCH",
            headers:{
                "Content-Type":"application/json",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            },
            body:JSON.stringify({
              email: document.querySelector('#email-box').value,
              userNo: localStorage.getItem('userNo'),
          })
      })
      .then((res)=>res.json())
      .then(res =>{
        
        alert('이메일변경완료');
        
        localStorage.clear();
      
        deleteCookie('Authorization');
        deleteCookie('RefreshToken');
        location.href="loginpage.html";
      })
    }
    function deleteCookie(name) {
      document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    }





//닉네임 변경
document.getElementById('nickname').value = localStorage.getItem('userNickname');

document.getElementById('nickname').addEventListener('keyup',function(){
    let nickname = document.getElementById('nickname').value;
  
    fetch(`${LOCALHOST_URL}/login/register/nickname/${nickname}`, {
      method:"GET",
    })
    .then((res)=> res.json())
    .then(res=>{
      console.log(res.checkResult);
      if(nickname.length >1){
       
        if(res.checkResult == 'available'){
          nicknameSuccessText();
        } else if(res.checkResult == 'unavailable'){
          nicknameFailText();
        }
  
      } else {
         isNicknameVaildText();
      }
  
    })
  
  });
  const isNicknameVaildText = () =>{
    let checkNicknameDiv = document.getElementById('checkNickname');
    checkNicknameDiv.innerHTML = '닉네임은 최소 2글자 이상이어야 합니다.';
    checkNicknameDiv.style.color = 'red';
    document.getElementById('checkNicknameResult').value = "fail";
    document.querySelector('#nickname_check').disabled = true;
  }
    
  const nicknameSuccessText = () => {
    let checkNicknameDiv = document.getElementById('checkNickname');
    checkNicknameDiv.innerHTML = '사용가능합니다.';
    checkNicknameDiv.style.color = 'green';
    document.getElementById('checkNicknameResult').value = "success";
    document.querySelector('#nickname_check').disabled = false;
  }
  
  const nicknameFailText = () => {
    let checkNicknameDiv = document.getElementById('checkNickname');
    checkNicknameDiv.innerHTML = '중복된 닉네임 입니다.';
    checkNicknameDiv.style.color = 'red';
    document.getElementById('checkNicknameResult').value = "fail";
    document.querySelector('#nickname_check').disabled = true;
  }
  
  const nicknameBlurText = () => {
    let checkNicknameDiv = document.getElementById('checkNickname');
    checkNicknameDiv.innerHTML = '';
    
  }




// LOCAL or SOCIAL
function noSocial(){
  console.log(localStorage.getItem('path'));

  //console.log("v-pills-profile-tab");
  let SOCIAL = localStorage.getItem('path');
  let buttonss = document.querySelectorAll('#v-pills-home-tab');
  //let buttonz = `<button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false" >비밀번호 변경</button>`;
  if(SOCIAL.includes("LOCAL")){
    document.querySelector('#v-pills-profile-tab');
  }
  if(SOCIAL.includes('SOCIAL')){
    document.getElementById('v-pills-profile-tab').style.display = "none";
  }
}


//내가 신고당한 글 리스트
function myReport(){
  let userNo = localStorage.getItem('userNo')

  fetch(`${LOCALHOST_URL}/login/report/${userNo}`,{
    method:"GET",
    headers:{
      "Content-Type":"application/json",
      Authorization:localStorage.getItem('authorization'),
      RefreshToken:localStorage.getItem('refreshToken'),
      },
    })
    .then((res)=>res.json())
    .then(res =>{
      document.querySelector("#reportAllList").innerHTML  = '';
      for(let report of res.data){
        let boardId = report.boardId;
        let content = report.content;
        let title = report.title;
        let serviceName = report.serviceName;
        let UserInfo = report.user;
        let path = report.path;
        let reportStatus = report.reportStatus;
        let imageUrl = report.imageUrl;
        console.log(report+"왜 없어?");
        console.log(report.title);
        //let list = document.querySelector("#reportAllList");
        
      if(serviceName.includes('mentor')){
        document.querySelector("#reportAllList").innerHTML +=
        `
        <div class="card mb-3" style="width: 750px;" style="height: 250px;">
            <div class="row g-0" style="height: 250px;"> 
            <div class="col-md-4" style="overflow: hidden; height:250px">
            <img class="profile-image" src='${imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                <h5 class="card-title">${title}</h5>
                <span class="badge text-bg-primary" id="serviceMetors">멘토</span>
                <p class="card-text">${content}</p>
               
                </div>
            </div>
            </div>
        </div>
        `;
      }
      else if(serviceName.includes('auction')){
        document.querySelector("#reportAllList").innerHTML +=
        `
        <div class="card mb-3" style="width: 750px;" style="height: 250px;">
            <div class="row g-0" style="height: 250px;">
            <div class="col-md-4" style="overflow: hidden; height:250px">
              <img class="profile-image" src='${imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                <h5 class="card-title">${title}</h5>
                <span class="badge text-bg-success" id="serviceAuction">경매</span>
                <p class="card-text">${content}</p>
               
                </div>
            </div>
            </div>
        </div>
        `;
      }
      else{
        document.querySelector("#reportAllList").innerHTML +=
        `
        <div class="card mb-3" style="width: 750px;" style="height: 250px;">
            <div class="row g-0" style="height: 250px;">
              <div class="col-md-4" style="overflow: hidden; height:250px">
              <img class="profile-image" src='${imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
              </div>
              <div class="col-md-8">
                  <div class="card-body">
                  <h5 class="card-title">${title}</h5>
                  <span class="badge text-bg-info" id="serviceBung">벙</span>
                  <p class="card-text" width="750px">${content}</p>             
                  </div>
              </div>
            </div>
        </div>
        `;
      }
      
      }
  })
}




//VIP시 <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>

//








