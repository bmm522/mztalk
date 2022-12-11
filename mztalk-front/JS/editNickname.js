window.resizeTo(200,200); // 지정한 크기로 변한다.(가로,세로)


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
  
	fetch("http://localhost:8000/login/register/nickname/"+nickname, {
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

  })