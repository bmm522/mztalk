const LOCALHOST_URL = "http://localhost:8000";


const moveMain = ()=>{
    alert('비밀번호가 변경되었습니다. 다시 로그인해주세요.');
    window.open('loginpage.html', 'MZTALK').opener.close();
}

   const checkCertificationEmailCode = () =>{
	if(emailAuthCode == document.getElementById('certificationEmailCode').value){
		dm();
	} else {
		alert('코드가 일치하지 않습니다.');
	}
   }
   
   
   
	
   const em=()=>{
      var e = document.getElementById('email-form');
       var c = document.getElementById('email-check');
       e.style.transform="translateY(-1000px)";
       e.style.transition="2s";
       c.style.transform="translateY(-400px)";
       c.style.transition="2s";
       
   }

   const dm=()=>{
   	var e = document.getElementById('email-check');
       var c = document.getElementById('changeByEmail');
       e.style.transform="translateY(-1000px)";
       e.style.transition="2s";
       c.style.transform="translateY(-700px)";
       c.style.transition="2s";

   }
   

   const isValidPassword = () =>{
   	let inputPassword = document.getElementById('password-box').value;
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
   		 console.log(2);
   		 checkPasswordResult.innerHTML= '공백은 허용되지 않습니다.';
   		 checkPasswordResult.style.color='red';
   		 document.getElementById('checkPasswordResult').value = "fail";
   	 }
   	 else if(num < 0 || eng < 0 || spe < 0 ){
   		 console.log(3);
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



   const isValidCheckPassword = () =>{
   	let checkRePassword = document.getElementById('check-password-box').value;
   	let checkPassword = document.getElementById('password-box').value;
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

  
//    $("#passwordForm").submit(function(){
// 	   document.getElementById('username').value=document.getElementById('userId').value;
// 		if($("#checkPasswordResult").val()=="fail"){
// 			alert("비밀번호를 형식에 맞게 작성해주세요.");
// 			return false;
// 		}
		
// 		if($("#checkRePasswordResult").val()=="fail"){
// 			alert("비밀번호가 일치하지 않습니다.")
// 			return false;
// 		}
// 		alert('변경되었습니다. 다시 로그인 해주세요.');
		
// 		 window.open('/','Y2K');
  
// 		return true;
// 	});

let emailAuthCode = '';
let userId = '';
document.getElementById('checkEmailButton').addEventListener('click', function(){
	userId = document.getElementById('userId').value;
	fetch(`${LOCALHOST_URL}/login/auth-code/?email=`+document.getElementById('userEmail').value+"&username="+document.getElementById('userId').value, {
    method:"GET",
     })
    .then((res)=> res.json())
    .then(res=>{
		if(res.authCode == 'It`s not an appropriate email format'){
			alert('올바른 이메일 형식을 입력해주세요');
		} else if(res.authCode == 'Not Exists Username'){
			alert('계정정보를 다시 확인해주세요.');
		} else {
			alert('해당 이메일로 코드를 전송하였습니다.');
			emailAuthCode = res.authCode;
			em();
		}
    })
});

const updatePwd = () =>{

	if(document.getElementById('checkPasswordResult').value == 'fail'){
		alert('비밀번호 형식이 올바르지 않습니다.');
	} else if(document.getElementById('checkRePasswordResult').value == 'fail'){
		alert('비밀번호가 일치하지 않습니다.');
	} else {
		fetch(`${LOCALHOST_URL}/login/password`, {
			method:"PATCH",
			headers:{
				"Content-Type":"application/json"
			},
			body:JSON.stringify({
				username : userId,
				password : document.getElementById('password-box').value
			}),
		})
		.then(res =>{
			moveMain();
		})
	}

}














// document.getElementById('password').addEventListener('keyup',function(){
// 	isValidPassword();
// });

// document.getElementById('password').addEventListener('blur', function(){
// 	passwordBlurText();
// });

// document.getElementById('check-password-box').addEventListener('keyup',function(){
// 	isValidCheckPassword();
// });

// document.getElementById('check-password-box').addEventListener('blur',function(){
// 	rePasswordBlurText();
// })



// document.getElementById('checkCode').addEventListener('click',function(){
// 	console.log('너 실행되니?');

// 	if(emailAuthCode == document.getElementById('certificationEmailCode').value){
// 		dm();
// 	} else {
// 		alert('코드가 일치하지 않습니다.');
// 	}
// });


