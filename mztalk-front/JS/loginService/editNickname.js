window.resizeTo(200,200); // 지정한 크기로 변한다.(가로,세로)

const LOCALHOST_URL = "http://localhost:8000";

const isNicknameVaildText = () =>{
	 let checkNicknameDiv = document.getElementById('checkNickname');
	 checkNicknameDiv.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp닉네임은 최소 2글자 이상이어야 합니다.';
	 checkNicknameDiv.style.color = 'red';
	 document.getElementById('checkNicknameResult').value = "fail";
}


const nicknameSuccessText = () => {
	 let checkNicknameDiv = document.getElementById('checkNickname');
	 checkNicknameDiv.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp사용가능합니다.';
	 checkNicknameDiv.style.color = 'green';
	 document.getElementById('checkNicknameResult').value = "success";
}

const nicknameFailText = () => {
	 let checkNicknameDiv = document.getElementById('checkNickname');
	 checkNicknameDiv.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp중복된 닉네임 입니다.';
	 checkNicknameDiv.style.color = 'red';
	 document.getElementById('checkNicknameResult').value = "fail";
}

const nicknameBlurText = () => {
	 let checkNicknameDiv = document.getElementById('checkNickname');
	 checkNicknameDiv.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
	 
}

document.getElementById('nickname-box').addEventListener('keyup',function(){
	let nickname = document.getElementById('nickname-box').value;
  
	fetch(`${LOCALHOST_URL}/login/register/nickname/${nickname}`, {
		method:"GET",
	})
	.then((res)=> res.json())
	.then(res=>{
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


  document.getElementById('editBtn').addEventListener('click',function(){

	fetch(`${LOCALHOST_URL}/login/social/nickname`,{
		method:"PATCH",
		headers:{
			"Content-Type":"application/json"
		},
		body:JSON.stringify({
			newNickname : document.getElementById('nickname-box').value,
			username : getCookieValue('username')
		}),
	})
	.then((res)=>res.json())
	.then(res=>{
		// localStorage.removeItem("Authorization");
		// localStorage.removeItem("RefreshToken");
		localStorage.setItem("authorization", res.jwtToken);
		localStorage.setItem("refreshToken", res.refreshToken);
		localStorage.setItem("userNo", res.userNo);
		localStorage.setItem("userNickname", res.userNickname);
		localStorage.setItem('path', 'SOCIAL');
		localStorage.setItem('role', res.role);
		window.open('main.html', '_self');
	})
  });

 

  const getCookieValue = (key) => {
	let cookieKey = key + "="; 
	let result = "";
	const cookieArr = document.cookie.split(";");
	
	for(let i = 0; i < cookieArr.length; i++) {
	  if(cookieArr[i][0] === " ") {
		cookieArr[i] = cookieArr[i].substring(1);
	  }
	  
	  if(cookieArr[i].indexOf(cookieKey) === 0) {
		result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
		return result;
	  }
	}
	return result;
  }