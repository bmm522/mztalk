window.onload= function(){
    

    fetch('http://localhost:8000/bung/bungAddBoardSelect/'+localStorage.getItem('addId'), {
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then(res=>res.json())
    .then(res=>{
        document.getElementById('name-box').value =res.addNickName;
        document.getElementById('phone-box').value =res.addPhone;
        document.getElementById('bungRequestuserReason').innerHTML =res.addContent;

        
    })

}

document.getElementById('request-btnbtn').addEventListener('click',function(){

    fetch('http://localhost:8000/bung/bungAddBoardAccept/'+localStorage.getItem('addId'), {
        method:"PATCH",
        headers:{
            Authorization:localStorage.getItem('authorization'),
        RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then(res=>res.json())
    .then(res=>{
        if(res.result == 0){
            alert('모집인원이 초과하였습니다.');
        } else {
            alert('승인되었습니다.');
            location.href = 'bung-service-main.html';
        }
    })
});


        

