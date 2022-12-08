// Elements
const el = {
    signUpHome: document.getElementById('sign-up'),
    signInHome: document.getElementById('sign-in'),
    btnHome: document.querySelector('.btn-back'),
    pageMain: document.querySelector('.main'),
    pageHome: document.querySelector('.home'),
    pageSignUp: document.querySelector('.sign-up'),
    formArea: document.querySelector('.form-area'),
    sideSignLeft: document.querySelector('.signup-left'),
    sideSignRight: document.querySelector('.signup-right'),
    formSignUp: document.querySelector('.form-area-signup'),
    formSignIn: document.querySelector('.form-area-signin'),
    linkUp: document.querySelector('.link-up'),
    linkIn: document.querySelector('.link-in'),
    btnSignUp: document.querySelector('.btn-up'),
    btnSignIn: document.querySelector('.btn-in'),
    labels: document.getElementsByTagName('label'),
    inputs: document.getElementsByTagName('input'),
  };
  
  
  // ADD Events
  // Show the page Sign Up
  el.signUpHome.addEventListener('click', function(e) {
    showSign(e, 'signup');
  });
  el.linkUp.addEventListener('click', function(e) {
    showSign(e, 'signup');
  });
  
  // Show the page sign in
  el.signInHome.addEventListener('click', function(e) {
    showSign(e, 'signin');
  });
  el.linkIn.addEventListener('click', function(e) {
    showSign(e, 'signin');
  });
  el.btnSignUp.addEventListener('click', function(e) {
    showSign(e, 'signin');
  });
  
  // Show the page Home
  el.btnHome.addEventListener('click', showHome);
  
  
  // Functions Events
  // function to show screen Home
  function showHome(event) {
    
    
    setTimeout(function() {
      el.sideSignLeft.style.padding = '0';
      el.sideSignLeft.style.opacity = '0';
      el.sideSignRight.style.opacity = '0';
      el.sideSignRight.style.backgroundPositionX = '235%';
  
      el.formArea.style.opacity = '0';
      setTimeout(function() {
        el.pageSignUp.style.opacity = '0';
        el.pageSignUp.style.display = 'none';
        for (input of el.inputs)  {
          input.value = '';
        }
      }, 900);
  
    }, 100);
  
    setTimeout(function() {
      el.pageHome.style.display = 'flex';
    },1100);
    
    setTimeout(function() {
      el.pageHome.style.opacity = '1';
    }, 1200);
  
  }
  // function to show screen Sign up/Sign in
  function showSign(event, sign) {
  
    if (sign === 'signup') {
      el.formSignUp.style.display = 'flex';
      el.formSignIn.style.opacity = '0';
      setTimeout(function() {
        el.formSignUp.style.opacity = '1';
      }, 100);
      el.formSignIn.style.display = 'none';
  
    } else {
      el.formSignIn.style.display = 'flex';
      el.formSignUp.style.opacity = '0';
      setTimeout(function() {
        el.formSignIn.style.opacity = '1';
      }, 100);
      el.formSignUp.style.display = 'none';
    }
  
    el.pageHome.style.opacity = '0';
    setTimeout(function() {
      el.pageHome.style.display = 'none';
    }, 700);
    
    setTimeout(function() {
      el.pageSignUp.style.display = 'flex';
      el.pageSignUp.style.opacity = '1';
      
      setTimeout(function() {
        el.sideSignLeft.style.padding = '20px';
        el.sideSignLeft.style.opacity = '1';
        el.sideSignRight.style.opacity = '1';
        el.sideSignRight.style.backgroundPositionX = '230%';
  
        el.formArea.style.opacity = '1';
      }, 10);
  
    }, 900);
  }
  
  // Behavior of the inputs and labels
  for (input of el.inputs) {
    console.log(input)
    input.addEventListener('keydown', function() {
      this.labels[0].style.top = '10px';
    });
    input.addEventListener('blur', function() {
      if (this.value === '') {
        this.labels[0].style.top = '25px';
      }
    })
  }
  


const nameBtn = document.querySelector('.userName');
const passwordBtn = document.querySelector('.userPassword');
const loginBtn = document.querySelector('.loginBtn');

function goToMain(){
    location.href = "main.html"
}

// nameBtn.addEventListener('keyup', function(){
//     let nameLength = nameBtn.value.length;
//     let passwordLength = passwordBtn.value.length;

//     if(nameLength >= 1 && passwordLength >= 5 && nameBtn.value.indexOf('@') !== -1 ){
//         loginBtn.style.backgroundColor = "#0095f6"; 
//         loginBtn.addEventListener('click', goToMain)       
//     } else {
//         loginBtn.style.backgroundColor = "#b2dffc";
//     }
// });

// passwordBtn.addEventListener('keyup', function(){
//     let nameLength = nameBtn.value.length;
//     let passwordLength = passwordBtn.value.length;

//     if(nameLength >= 1 && passwordLength >= 5 && nameBtn.value.indexOf('@') !== -1){
//         loginBtn.style.backgroundColor = "#0095f6"; 
//         loginBtn.addEventListener('click', goToMain)       
//     } else {
//         loginBtn.style.backgroundColor = "#b2dffc";
//     }
// });


const checkEmail = () =>{
  
}

const test=()=>{
  console.log("실행되긴함");
}


function findId(){
  window.open('findId.html', '아이디 찾기', 'width=600px,height=480px,scrollbars=no').opener.close();
 
}

function findPwd(){
  window.open('findPwd.html', '비밀번호 변경', 'width=600px,height=480px,scrollbars=no').opener.close();
}


 const isIdVaildText = () =>{
   let checkIdDiv = document.getElementById('checkId');
   checkIdDiv.innerHTML = '아이디는 최소 4글자 이상이어야 합니다.';
   checkIdDiv.style.color = 'red';
   document.getElementById('checkIdResult').value = "fail";
 }


const idSuccessText = () => {
  let checkIdDiv = document.getElementById('checkId');
  checkIdDiv.innerHTML = '사용가능합니다.';
  checkIdDiv.style.color = 'green';
  document.getElementById('checkIdResult').value = "success";
}

const idFailText = () => {
  let checkIdDiv = document.getElementById('checkId');
  checkIdDiv.innerHTML = '중복된 아이디 입니다.';
  checkIdDiv.style.color = 'red';
  document.getElementById('checkIdResult').value = "fail";
}

const idBlurText = () => {
  let checkIdDiv = document.getElementById('checkId');
  checkIdDiv.innerHTML = '';
  
}


// const register = () =>{
//   fetch("http://localhost:8000/login/user",{
//     method: "POST",
//     headers:{
//         "Content-Type":"application/json",            
//     },
//     body:JSON.stringify({
//         userId : document.getElementById('username').value,
//         password : document.getElementById('password').value,
//         nickname : document.getElementById('nickname').value,
//         email : document.getElementById('email').value
//     }),
// })

// .then((res) => res.json())
// .then(res => {  
// });

// }

document.getElementById('sign-up-btn').addEventListener('click',function(e){

  if(document.getElementById('checkIdResult').value=="fail"){
		
    alert("아이디 확인 바랍니다.");
    showSign(e, 'signup');
    document.getElementById('username').focus();
   
	} else if(document.getElementById('checkPasswordResult').value=="fail"){
		
    alert("비밀번호를 형식에 맞게 작성해주세요.");
    showSign(e, 'signup');
    document.getElementById('password').focus();
    
	} else if(document.getElementById('checkRePasswordResult').value=="fail"){
		
    alert("비밀번호가 일치하지 않습니다.");
    showSign(e, 'signup');
    document.getElementById('re_password').focus();
   
	
  } else if(document.getElementById('checkNicknameResult').value=="fail"){
		
    alert("닉네임 확인 바랍니다.");
    showSign(e, 'signup');
    document.getElementById('nickname').focus();
	
  } else if(document.getElementById('checkEmailResult').value=="fail"){
		
    alert("이메일 확인 바랍니다.");
    showSign(e, 'signup');
    document.getElementById('email-box').focus();
	
  } else if(document.getElementById('checkAuthResult').value=="fail"){
		
    alert("인증코드 확인 바랍니다.");
    showSign(e, 'signup');
    document.getElementById('auth-box').focus();
	
  } else {

    fetch("http://localhost:8000/login/register/user",{
        method: "POST",
        headers:{
            "Content-Type":"application/json",            
        },
        body:JSON.stringify({
            userId : document.getElementById('username').value,
            password : document.getElementById('password').value,
            nickname : document.getElementById('nickname').value,
            email : document.getElementById('email-box').value
        }),
    })
   
    .then((res) => res.json())
    .then(res => {  
    });

  }






  

});


document.getElementById('username').addEventListener('keyup',function(){
  let userId = document.getElementById('username').value;

  fetch("http://localhost:8000/login/register/username/"+userId, {
    method:"GET",
  })
  .then((res)=> res.json())
  .then(res=>{

    if(userId.length >3){
     
      if(res.checkResult == 'available'){
        idSuccessText();
      } else if(res.checkResult == 'unavailable'){
        idFailText();
      }

    } else {
        isIdVaildText();
    }

  })

});
document.getElementById('username').addEventListener('blur',function(){
  idBlurText(); 
});


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
	 console.log("pw : " + pw);
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	 if(pw.length < 8 || pw.length > 20){
		 console.log(1);
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
	} else {
		checkRePw.innerHTML="일치하지 않습니다.";
		checkRePw.style.color = "red";
		document.getElementById('checkRePasswordResult').value = "fail";
	}
}

const rePasswordBlurText = () => {
	let checkRePw = document.getElementById('checkRePw');
	checkRePw.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
}


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
  nicknameBlurText();
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

const authBlurText = () =>{
	document.getElementById('checkAuth').innerHTML = '';
}



document.getElementById('sign-in-btn').addEventListener('click', function(){
  

  let userId = document.getElementById('userId').value;
  let password = document.getElementById('password-in').value;

  if(userId == "" || password == ""){
    alert('아이디 또는 비밀번호를 입력해주세요');
  } else {
    fetch("http://localhost:8000/login",{
        method: "POST",
        headers:{
            "Content-Type":"application/json",            
        },
        body:JSON.stringify({
            username : userId,
            password : password
        }),
    })
   
    .then(res => {
      let result =   res.headers.get('LoginResult');
      console.log('통신성공');
      if(result =='Not found userId or userPassword'){
        alert('아이디 또는 비밀번호가 틀렸습니다.');
        document.getElementById('userId').value = "";
        document.getElementById('password-in').value ="";
      } else if(result =='Fail Login'){
        alert('로그인을 실패하였습니다.');
        document.getElementById('userId').value = "";
        document.getElementById('password-in').value ="";
      } else {
        localStorage.setItem("Authorization", res.headers.get('Authorization'));
        localStorage.setItem("RefreshToken", res.headers.get('RefreshToken'));
        window.open('main.html', '_self');
      }

    });
  }
  
  
});
