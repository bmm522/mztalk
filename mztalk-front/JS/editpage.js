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

  const isIdVaildText = () =>{
    let checkIdDiv = document.getElementById('checkId');
    checkIdDiv.innerHTML = '아이디는 최소 4글자 이상이어야 합니다.';
    checkIdDiv.style.color = 'red';
    document.getElementById('checkIdResult').value = "fail";
  }

  const idBlurText = () => {
    let checkIdDiv = document.getElementById('checkId');
    checkIdDiv.innerHTML = '';  
  }

  document.getElementById('nickname').addEventListener('blur',function(){
    idBlurText(); 
  });















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
        <img class="profile-image" src='profileUrl' onerror="this.src='duck.jpg'" id="userProfileImage">
        <input type="hidden" class="imageName" value="profileName"/>
        <input type="hidden" name="bNo" id="bNo" value="own"/>
        `  
     }
      let profileUrl = profileImage.postImageUrl;
      let profileName = profileImage.profileImageName;
      let own = profileImage.own

      document.querySelector('.profile-img-wrap').innerHTML +=
      `
      <img class="profile-image" src='${profileUrl}' onerror="this.src='duck.jpg'" id="userProfileImage">
      <input type="hidden" class="imageName" value="${profileName}"/>
      <input type="hidden" name="bNo" id="bNo" value="${own}"/>
      `
      })
    }
