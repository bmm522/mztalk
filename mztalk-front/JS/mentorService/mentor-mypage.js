window.onload = () =>{
    myMentees();
}

// 멘토 글 작성하기
document.getElementById('mentor-write-btn').addEventListener('click',function(){
    let salary = document.getElementById('salary').value;
    if(salary<0){
        alert('0원이하의 금액은 설정할 수 없습니다.');
        return false;
    }
    fetch(`${LOCALHOST_URL}/mentors/board`,{
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
            salary : salary,
            mentoringDate : document.getElementById('mentoringDate').value
        })
    })    
    .then((res)=>res.json())
    .then(res =>{
        if(res != null){
            window.alert('게시글이 작성 되었습니다.');
            location.href="mentor-main.html";
        } else {
            window.alert('멘토 신청 실패');
            return false;
        }
    })
});

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

// 글삭제 하기
const deleteBoard = () =>{
    const mentorId = localStorage.getItem('userNo');
    fetch(`${LOCALHOST_URL}/mentors/board/${mentorId}`,{
        method:"DELETE",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res != null){
            window.alert('글이 삭제되었습니다.');
            location.href="mentor-main.html";
        } else{
            window.alert('글  삭제 실패');
            return false;
        }
    })  
}


// 멘토가 작성한 글에 대한 신청 현황
const myMentees = () =>{
    const mentorId = localStorage.getItem('userNo');
    fetch(`${LOCALHOST_URL}/mentors/participant?mentorId=${mentorId}`,{
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
            document.getElementById('myBoardList').innerHTML +=
                `<td>${data.board.id}</td>
                <td>${data.mentee.nickname}</td>
                <td>${data.phone}</td>
                <td>${data.email}</td>`
             }
        } else{
            window.alert('읽어오기 실패');
        }
    })
    document.getElementById('myBoardList').innerHTML ='';
}


//마이 페이지 이동, 권한 확인 후 true면 멘토 > 멘토페이지 false면 멘티 > 멘티페이지
document.getElementById('myPage').addEventListener('click', function(){
    const userId = localStorage.getItem('userNo');
    fetch(`${LOCALHOST_URL}/mentors/member?userId=${userId}`,{
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

// 멘토 본인이 작성한글 수정페이지
document.getElementById('myBoard').addEventListener('click',function(){
    location.href="mentor-modify-board.html";   
});

// 멘토 메인페이지로 이동
document.getElementById('move-mentor-service').addEventListener('click',function(){
    location.href="mentor-main.html";   
});

// 멘토 리뷰 관리 페이지 이동 
document.getElementById('myReview').addEventListener('click',function(){
    location.href="mentor-review-page.html";   
});