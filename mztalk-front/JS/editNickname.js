window.resizeTo(200,200); // 지정한 크기로 변한다.(가로,세로)


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
	 checkNicknameDiv.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
	 
}
