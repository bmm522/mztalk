window.onload=function(){
    console.log("수정페이지 : " + localStorage.getItem('authorization'));
    console.log("수정페이지 : " + localStorage.getItem('refreshToken'));
    console.log("수정페이지 : " + localStorage.getItem('userNo'));
    console.log("수정페이지 : " + localStorage.getItem('userNickname'));
    profileBox();

}




const image = document.querySelector(".modal-image");

function modalImage() {
    
    console.log(modalImage);
    image.classList
	image.style.display = "none";
}

function popup(image) {
    console.log(image);

    image.style.display = "flex";
}


document.getElementById('nickname').value = localStorage.getItem('userNickname');

document.getElementById('nickname').addEventListener('keyup',function(){
    let nickname = document.getElementById('nickname').value;
  
    fetch("http://localhost:8000/login/register/nickname/"+nickname, {
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

  document.getElementById('nickname').addEventListener('blur',function(){
    idBlurText(); 
  });

  const isNicknameVaildText = () =>{
    let checkNicknameDiv = document.getElementById('checkNickname');
    checkNicknameDiv.innerHTML = '닉네임은 최소 2글자 이상이어야 합니다.';
    checkNicknameDiv.style.color = 'red';
    document.getElementById('checkNicknameResult').value = "fail";
  }
    
  const nicknameSuccessText = () => {
    let checkNicknameDiv = document.getElementById('checkNickname');
    checkNicknameDiv.innerHTML = '사용가능합니다.';
    checkNicknameDiv.style.color = 'green';
    document.getElementById('checkNicknameResult').value = "success";
  }
  
  const nicknameFailText = () => {
    let checkNicknameDiv = document.getElementById('checkNickname');
    checkNicknameDiv.innerHTML = '중복된 닉네임 입니다.';
    checkNicknameDiv.style.color = 'red';
    document.getElementById('checkNicknameResult').value = "fail";
  }
  
  const nicknameBlurText = () => {
    let checkNicknameDiv = document.getElementById('checkNickname');
    checkNicknameDiv.innerHTML = '';
    
  }




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
      
        checkEmail.innerHTML= '올바른 형식입니다.';
        checkEmail.style.color='green';
        document.getElementById('checkEmailResult').value = "success";
      
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
      fetch("http://localhost:8000/login/register/auth-code/"+document.getElementById('email-box').value, {
      method:"GET",
       })
      .then((res)=> res.json())
      .then(res=>{
       console.log(res.authCode);
        if(!res.authCode == 'It`s not an appropriate email format'){
          
          alert('이메일 형식이 올바르지 않습니다.');
  
        } else {
  
          emailAuthCode = res.authCode;
          alert('작성하신 이메일로 인증코드를 전송했습니다.');
          document.getElementById('auth_div').style.display="flex";
  
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
  








  //프로필 사진
function profileBox(){

  // const form = document.getElementById('image-form');
    let own = localStorage.getItem("own");
  // const payload = new FormData(form);

    // fetch('http://localhost:8000/resource/main-image',{
    //     method: 'POST',
    //     // body: payload,
    // })
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
