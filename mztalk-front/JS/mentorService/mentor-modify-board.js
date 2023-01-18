window.onload = () =>{
    myMentees();
    getMyBoard();
}

// 멘토 글 작성하기
document.getElementById('mentor-write-btn').addEventListener('click',function(){
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
            salary : document.getElementById('salary').value,
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


// 내가 작성한 글상세 보기 후 수정, 삭제
const getMyBoard = () =>{
   const mentorId = localStorage.getItem('userNo');
    fetch(`${LOCALHOST_URL}/mentors/board/mentor/${mentorId}`,{
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
            document.getElementById("board-list-div").innerHTML += 
            `<div>작성하신 글이 존재하지 않습니다.</div>`
        } else{
            document.getElementById("board-list-div").innerHTML +=
            `<div class="container text-center" style="padding-top:30px; font-size:14px;">
                <div class="row">
                    <div class="col-4">
                        글번호
                    </div>
                    <div class="col-4">
                        제목
                    </div>
                    <div class="col-4">
                        상세보기
                    </div>
                </div>
            </div>
            `
            for(const board of res.data){
                document.getElementById("board-list-div").innerHTML +=
                `<div class="container text-center" style="padding-top:30px; font-size:14px;">
                    <div class="row">
                        <div class="col-4">
                            ${board.id}
                        </div>
                        <div class="col-4">
                            ${board.title}
                        </div>
                        <div class="col-4">
                            <button type="button" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                            class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#modifyModal" onclick="showBoard(${board.id});">작성한 내용 보기</button>
                        </div>
                    </div>
                </div>
                `
            }
        }
    })
};

// 내가 작성한글 보기
const showBoard = (boardId) =>{
    fetch(`${LOCALHOST_URL}/mentors/board/${boardId}`,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },  
    })
    .then((res)=>res.json())
    .then(res =>{
        document.getElementById('modifyBoardId').value = res.data.id;
        document.getElementById('modifyTitle').value = res.data.title;
        document.getElementById('modifyIntroduction').value = res.data.introduction;
        document.getElementById('modifyCareer').value = res.data.career;
        document.getElementById('modifySalary').value = res.data.salary;
        document.getElementById('modifyMentoringDate').value = res.data.mentoringDate;
        document.getElementById('modifyContent').value = res.data.content;
    }) 
}

// 글 수정하기
const modifyBoard = () =>{
    const boardId = document.getElementById('modifyBoardId').value;
    let salary = document.getElementById('salary').value;
    if(salary<0){
        alert('0원 이하의 금액은 설정할 수 없습니다.');
        return false;
    }
    fetch(`${LOCALHOST_URL}/mentors/participant/board/${boardId}`,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=>res.json())
    .then(res=>{
        if(res){
            alert('신청자가 존재하여 수정할 수 없습니다.');
            location.href="mentor-modify-board.html";
            return false;
        } else{
            fetch(`${LOCALHOST_URL}/mentors/board/edit/${boardId}`,{
                method:"PATCH",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:localStorage.getItem('authorization'),
                    RefreshToken:localStorage.getItem('refreshToken'),
                },
                body:JSON.stringify({
                    title : document.getElementById('modifyTitle').value,
                    introduction : document.getElementById('modifyIntroduction').value,
                    career : document.getElementById('modifyCareer').value,
                    salary : salary,
                    mentoringDate : document.getElementById('modifyMentoringDate').value,
                    content : document.getElementById('modifyContent').value
                })    
            })
            .then((res)=>res.json())
            .then(res =>{
                if(res.status != 500){
                    window.alert('글이 수정되었습니다.');
                    location.href="mentor-modify-board.html";
                } else{
                    window.alert('글 수정 실패.');
                    location.href="mentor-modify-board.html";
                }
            }) 
        }
    })     
}

// 글 작성시 최소 날짜 설정.
const dateControl = document.getElementById('mentoringDate');
const modifyControl = document.getElementById('modifyMentoringDate');
const date = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().slice(0, -5);
dateControl.value = date;
dateControl.setAttribute("min",date);

function setMinValue() {
    if(dateControl.value < date) {
        alert('현재 시간보다 이전의 날짜는 설정할 수 없습니다.');
        dateControl.value = date;
    }
}

function setMinValue2() {
    if(modifyControl.value < date) {
        alert('현재 시간보다 이전의 날짜는 설정할 수 없습니다.');
        modifyControl.value = date;
    }
}

// 멘토글 삭제
const deleteBoard = () =>{
    const boardId = document.getElementById('modifyBoardId').value;
    fetch(`${LOCALHOST_URL}/mentors/board/${boardId}`,{
        method:"DELETE",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res.status != 500){
            window.alert('글이 삭제되었습니다.');
            location.href="mentor-modify-board.html";
        } else{
            window.alert('신청자가 존재하여 삭제할 수 없습니다.');
            location.href="mentor-modify-board.html";
            return false;
        }
    })  
}


// 작성한 글에 대한 멘토 신청 현황
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
    document.getElementById('myBoardList').innerHTML = '';
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

// 멘토 메인페이지로 이동
document.getElementById('move-mentor-service').addEventListener('click',function(){
    location.href="mentor-main.html";   
});

// 멘토 리뷰 관리 페이지 이동 
document.getElementById('myReview').addEventListener('click',function(){
    location.href="mentor-review-page.html";   
});