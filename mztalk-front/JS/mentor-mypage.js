window.onload = () =>{
    myBoard();
    endMyBoard();
}

// 멘토 신청하기
document.getElementById('mentor-write-btn').addEventListener('click',function(){

    fetch("http://localhost:8000/mentors/board",{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            category:document.getElementById('write-category').value,
            title : document.getElementById('title').value,
            nickname : localStorage.getItem('userNickname'),
            userId : localStorage.getItem('userNo'),
            content : document.getElementById('content').value,
            introduction : document.getElementById('introduction').value,
            career : document.getElementById('career').value,
            salary : document.getElementById('salary').value
        })
    })
    .then(res =>{
        window.location.replace("mentor-main.html");
    })         
});

// 작성한 글에 대한 멘토 신청 현황
const myBoard = () =>{
    document.getElementById('getMyBoard').addEventListener('click',function(){
    const mentorId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/participant?mentorId="+mentorId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res!=null){
            for(const data of res.data){
            document.getElementById('myBoardList').innerHTML =
                `<td>${data.board.id}</td>
                <td>${data.mentee.nickname}</td>
                <td>${data.phone}</td>
                <td>${data.email}</td>`
             }
        } else{
            console.log('값없음');
        }
    })  
    });
}

// 멘토링이 종료된 멘티들
const endMyBoard = () =>{
    document.getElementById('endMyMentoring').addEventListener('click',function(){
    const mentorId = localStorage.getItem('userNo');
    console.log(mentorId);
    fetch("http://localhost:8000/mentors/participant?mentorId="+mentorId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res!=null){
            for(const data of res.data){
            document.getElementById('myMentees').innerHTML =
                `<td>${data.board.id}</td>
                <td>${data.mentee.nickname}</td>`
             }
        } else{
            console.log('값없음');
        }
    })  
    });
}


//마이 페이지 이동, 권한 확인 후 true면 멘토 > 멘토페이지 false면 멘티 > 멘티페이지
document.getElementById('myPage').addEventListener('click', function(){
    const userId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/member?userId="+userId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        console.log("res : " + res);
        if(res){
            location.href="mentor-mypage.html";
        } else {
            location.href="mentee-mypage.html";
        }
    })
});

//멘토 메인페이지로 이동
document.getElementById('move-mentor-service').addEventListener('click',function(){
    location.href="mentor-main.html";   
});

