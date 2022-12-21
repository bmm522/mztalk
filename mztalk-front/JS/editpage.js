window.onload=function(){
    console.log("수정페이지 : " + localStorage.getItem('authorization'));
    console.log("수정페이지 : " + localStorage.getItem('refreshToken'));
    console.log("수정페이지 : " + localStorage.getItem('userNo'));
    console.log("수정페이지 : " + localStorage.getItem('userNickname'));
    
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
    let userId = document.getElementById('nickname').value;
  
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