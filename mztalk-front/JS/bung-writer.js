

window.onload = function(){
    console.log(localStorage.getItem('bId'));
document.getElementById('bId-hidden').value=localStorage.getItem('bId');
   console.log( document.getElementById('bId-hidden').value);
   console.log("이미지 value 값 : " + document.getElementById('fileUpload').value);
  
}















document.getElementById('bung-write-btn').addEventListener('click', function(){
  if(!document.getElementById('fileUpload').value){
    alert('사진을 입력해주세요');
  } else {
    let checkValue = '';
    let cnt = 0;
     
    for(let i = 0 ; i < document.getElementsByClassName('form-check-input').length ; i++){
        
        if(document.getElementsByClassName('form-check-input')[i].checked){
            checkValue += document.getElementsByClassName('form-check-input')[i].value+ " ";
            cnt += 1;       
        }
       
    }
 
    if(cnt > 1){
        alert('카테고리는 하나만 선택해주세요');
    } else if(cnt ==0){
        alert('카테고리를 선택해주세요');
    } else {
       
        const form = document.getElementById('image-form');
        const payload = new FormData(form);
    
        fetch('http://localhost:8000/resource/images',{
            method: 'POST',
            body: payload,
        })
        .then(res=>{
            postBungData(checkValue);      
        })





        
    }
}
});



const postBungData = (checkValue) =>{
  

    fetch('http://localhost:8000/bung/mainInsertBoard', {
        method:"POST",
       headers:{
       "Content-Type" : "application/json",
       Authorization:localStorage.getItem('authorization'),
       RefreshToken:localStorage.getItem('refreshToken'),
   },
   body:JSON.stringify({
       boardWriter : localStorage.getItem("userNickname"),
       boardTitle : document.getElementById('bungTitle').value,
       boardContent : document.getElementById('bungContent').value,
       deadlineDate : document.getElementById('calYear').innerHTML+"-"+document.getElementById('calMonth').innerHTML+"-"+document.getElementsByClassName('choiceDay')[0].innerHTML,
        fullGroup:document.getElementById('full-group').value,
       category : checkValue
   })
   
})
.then(res=>{
   
   location.href="bung-service-main.html";
})
}

