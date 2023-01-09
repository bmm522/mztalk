window.onload= function(){

    document.getElementById('bungRequestUserName').value = localStorage.getItem('userNickname');

    
}

document.getElementById('addbtn').addEventListener('click', function(){
    if(document.getElementById('bungRequestuserReason').value === '') {
        alert('사유를 작성해주세요!');
    } else if(document.getElementById('bungRequestUserPhone').value === '') {
        alert('핸드폰 번호를 작성해주세요!');
    } else {
        fetch('http://localhost:8000/bung/bungAddBoard', {
        method:"POST",
        headers:{
            "Content-Type" : "application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            addNickName : localStorage.getItem("userNickname"),
            addContent : document.getElementById('bungRequestuserReason').value,
            addPhone : document.getElementById('bungRequestUserPhone').value,
            boardId : localStorage.getItem("bId")
        }),
    })
        .then((res) => res.json())
        .then(res => {
            console.log("신청 시 RESPONSE:" + JSON.stringify(res));

            if(res.message == "신청이 완료되었습니다.") {
                alert(res.message);
                location.href="bung-service-main.html";
            } else if(res.message == "마감시간이 종료되었습니다.") {
                alert(res.message);
                location.href="bung-service-main.html";
            } else if(res.message == "이미 신청한 게시글입니다.") {
                alert(res.message);
                location.href="bung-service-main.html";
            }
        });
    }
});

document.getElementById('request-btn').addEventListener('click', function(){
    location.href = 'bung-service-detail.html';
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