window.onload = () =>{
    myMentees();
    // endMyBoard();
    getMyBoard();
}

// 멘토 글 작성하기
document.getElementById('mentor-write-btn').addEventListener('click',function(){
    fetch("http://localhost:8000/mentors/board",{
        method:"POST",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
        body:JSON.stringify({
            category:document.getElementById('write-category').value,
            title : document.getElementById('title').value,
            nickname : localStorage.getItem('userNickname'),
            userId : localStorage.getItem('userNo'),
            content : document.getElementById('content').value,
            introduction : document.getElementById('introduction').value,
            career : document.getElementById('career').value,
            salary : document.getElementById('salary').value,
            mentoringDate : document.getElementById('mentoringDate').value
        })
    })    
    .then((res)=>res.json())
    .then(res =>{
        if(res > 0){
            window.alert('게시글이 작성 되었습니다.');
            location.href="mentor-main.html";
        } else {
            window.alert('멘토 신청 실패');
            return false;
        }
    })
});


// 내가 작성한 글상세 보기 후 수정, 삭제 구현하기 // 작성한 글 없으면 없다 띄워주기
const getMyBoard = () =>{
   const mentorId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/board/mentor/"+mentorId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res == null){
            document.getElementById("board-list-div").innerHTML = 
            `<div>작성하신 글이 존재하지 않습니다.</div>`
        } else{
            for(const board of res.data){
                document.getElementById("board-list-div").innerHTML += 
                `<div>${board.title}</div>
                <div>==========</div>`
            }
        }
    })  
};


//글 작성 수정하기
const modify = () =>{
    const mentorId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/board/edit/"+mentorId,{
        method:"PATCH",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            title : document.getElementById('modify-title').value,
            introduction : document.getElementById('modify-introduction').value,
            career : document.getElementById('modify-career').value,
            salary : document.getElementById('modify-salary').value,
            mentoringDate : document.getElementById('mentoringDate').value,
            content : document.getElementById('modify-content').value
        })    
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res>0){
            window.alert('글이 수정되었습니다.');
        } else{
            console.log('글 수정 실패');
        }
    })  
}

// 글 작성시 최소 날짜 설정.
const dateControl = document.getElementById('mentoringDate');
const date = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().slice(0, -5);
dateControl.value = date;
dateControl.setAttribute("min",date);

function setMinValue() {
    if(dateControl.value < date) {
        alert('현재 시간보다 이전의 날짜는 설정할 수 없습니다.');
        dateControl.value = date;
    }
}

// 멘토글 삭제
const deleteBoard = () =>{
    const mentorId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/board/"+mentorId,{
        method:"DELETE",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res>0){
            window.alert('글이 삭제되었습니다.');
            location.href="mentor-main.html";
        } else{
            window.alert('글  삭제 실패');
            return false;
        }
    })  
}


// 작성한 글에 대한 멘토 신청 현황
const myMentees = () =>{
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
}

// 멘토링이 종료된 멘티들
// const endMyBoard = () =>{
//     document.getElementById('endMyMentoring').addEventListener('click',function(){
//     const mentorId = localStorage.getItem('userNo');
//     fetch("http://localhost:8000/mentors/participant?mentorId="+mentorId,{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         },
//     })
//     .then((res)=>res.json())
//     .then(res =>{
//         if(res!=null){
//             for(const data of res.data){
//             document.getElementById('myMentees').innerHTML =
//                 `<td>${data.board.id}</td>
//                 <td>${data.mentee.nickname}</td>`
//              }
//         } else{
//             console.log('값없음');
//         }
//     })  
//     });
// }


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
        if(res){
            location.href="mentor-mypage.html";
        } else {
            location.href="mentee-mypage.html";
        }
    })
});

// 멘토 메인페이지로 이동
document.getElementById('move-mentor-service').addEventListener('click',function(){
    location.href="mentor-main.html";   
});

// 멘토 리뷰 관리 페이지 이동 
document.getElementById('myReview').addEventListener('click',function(){
    location.href="mentor-review-page.html";   
});