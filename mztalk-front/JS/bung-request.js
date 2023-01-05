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
        if(res.message == "수락되었습니다."){
            alert(res.message);
            location.href = 'bung-service-main.html';
        } else if(res.message == "모집인원이 초과하였습니다.") {
            alert(res.message);
            location.href = 'bung-service-main.html';
        }
    })
});

document.getElementById('refuse-btnbtn').addEventListener('click',function(){

    fetch('http://localhost:8000/bung/addBungRefuse/'+localStorage.getItem('addId'), {
        method:"DELETE",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then(res=>res.json())
    .then(res=>{
        if(res.message == "거절 요청이 완료되었습니다."){
            alert(res.message);
            location.href="bung-service-main.html";
        } else if(res.message == "거절 요청이 실패하였습니다."){
            alert(res.message);
            location.href = 'bung-service-main.html';
        }
    });
});

document.getElementById('request-btn').addEventListener('click', function(){
    location.href = 'bung-service-request.html';
});

//멘토서비스보내기
document.getElementById('move-mentor-service').addEventListener('click',function(){
    
    fetch("http://localhost:8000/mentors",{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            nickname : localStorage.getItem('userNickname'),
            id : localStorage.getItem('userNo')
        })
    })
    .then(res =>{
        location.href="mentor-main.html";
    })             
});


//벙서비스보내기
document.getElementById('move-bung-service').addEventListener('click',function(){

    //이 구조는 그냥 버튼에 자기페이지 가는 로직
    //해당유저 게시판엔 userNo이 input hidden 걸려있어야 가게끔
    //개인페이지에서 다른 페이지 넘어가면 own을 초기화 삭제 해줘야한다
    //친구페이지 갈때는 set하면 안될듯
    
    fetch("http://localhost:8000/bung/mainBoards",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
            
        },
    })
    .then((res)=> res.json())
    .then(res=>{

        location.href="bung-service-main.html";
    })

});



//경매서비스 보내기
document.getElementById('move-auction-service').addEventListener('click',function(){

    //이 구조는 그냥 버튼에 자기페이지 가는 로직
    //해당유저 게시판엔 userNo이 input hidden 걸려있어야 가게끔
    //개인페이지에서 다른 페이지 넘어가면 own을 초기화 삭제 해줘야한다
    //친구페이지 갈때는 set하면 안될듯
    
    fetch("http://localhost:8000/auction/",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
            
        },
    })
    .then((res)=> res.json())
    .then(res=>{

        location.href="auction.html";

    })

});


        

