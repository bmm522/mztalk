window.onload = () =>{
    myMentees();
    endMyBoard();
    getMyBoard();
}

// 멘토 글 작성하기 // 중복 검사 후 중복이 존재하면 글 작성 실패
document.getElementById('mentor-write-btn').addEventListener('click',function(){
    const mentorId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/board/mentor/"+mentorId,{
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
            window.alert('이미 작성하신 게시글이 존재합니다.');
            location.href="mentor-mypage.html";
            return false;
        } else {
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
                salary : document.getElementById('salary').value
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
        }
    })
});

// 내가 작성한 글상세 보기 후 수정, 삭제 구현하기 // 작성한 글 없으면 없다 띄워주기
const getMyBoard = () =>{
    const mentorId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/board/mentor/"+mentorId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })  
    .then((res)=>res.json())
    .then(res =>{
        if(!res){
            document.getElementById('board-list-div').innerHTML =
            `
            <div style="text-align:center; padding:100px;">작성하신 글이 존재하지 않습니다.</div>`    
            return false;
        } else{
            const mentorId = localStorage.getItem('userNo');
            fetch("http://localhost:8000/mentors/board/mentor?mentorId="+mentorId,{
                method:"GET",
                headers:{
                "Content-Type":"application/json",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
                },
            })
            .then((res)=>res.json())
            .then(res =>{
                document.getElementById('board-list-div').innerHTML =
                `<div style="width:500px; text-align:center; margin-left:25%; padding:50px; border:1px solid black;">
                    <div> <label>내가 작성한 멘토링 수정페이지</label>
                        <div class="modal-body" style="padding-top:30px;">
                            <label class="form-label">카테고리</label>
                            <select name="category" class="form-select form-select-sm" readonly>
                                <option>${res.category}</option>
                            </select>
                            <div class="mb-3">
                                <label for="modify-title" class="form-label">제목</label>
                                <input type="text" class="form-control form-control-sm" id="modify-title" value="${res.title}">
                            </div>
                            <div class="mb-3">
                                <label for="modify-introduction" class="form-label">자기소개</label>
                                <input type="text" class="form-control form-control-sm" id="modify-introduction" value="${res.introduction}">
                            </div>
                            <div class="mb-3">
                                <label for="modify-career" class="form-label">경력</label>
                                <input type="text" class="form-control form-control-sm" id="modify-career" value="${res.career}">
                            </div>
                            <div class="mb-3">
                                <label for="modify-salary" class="form-label">시급</label>
                                <input type="text" class="form-control form-control-sm" id="modify-salary" value="${res.salary}">
                            </div>
                            <div class="mb-3">
                                <label for="modify-content">내용</label>
                                <textarea class="form-control" id="modify-content" style="height: 300px">${res.content}</textarea>
                            </div>
                            <button type="button" class="btn btn-outline-success" onclick="modify();">수정 하기</button>
                            <button type="button" class="btn btn-outline-danger" onclick="deleteBoard();">삭제하기</button>
                        </div>
                    </div>      
                </div>`
            })  
        }   
    });
};

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
const endMyBoard = () =>{
    document.getElementById('endMyMentoring').addEventListener('click',function(){
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

