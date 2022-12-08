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

document.getElementById('sign-up-btn').addEventListener('click',function(){

  fetch("http://localhost:8000/login/register/user",{
        method: "POST",
        headers:{
            "Content-Type":"application/json",            
        },
        body:JSON.stringify({
            userId : document.getElementById('username').value,
            password : document.getElementById('password').value,
            nickname : document.getElementById('nickname').value,
            email : document.getElementById('email').value
        }),
    })
   
    .then((res) => res.json())
    .then(res => {  
    });

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


