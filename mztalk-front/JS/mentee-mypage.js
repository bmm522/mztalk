window.onload = function(){
    getBoardList();
    addFile();
}

// 멘토 신청시 첨부파일 추가 버튼 생성
const addFile = () =>{
    document.getElementById("addFile").addEventListener('click', () => { 
        if(document.getElementsByName("file").length < 3) {
            const newDiv = document.createElement('div');
            newDiv.classList.add('col-10');
            newDiv.innerHTML = '<input type = "file" class = "form-control" name = "file">';
            document.getElementById('file-form').append(newDiv);
        } else {
            alert("파일은 3개까지 첨부 가능합니다.");
        }
    });
}

// 토큰 만료 시 토큰 재발급
const getAccessToken = () =>{
    localStorage.removeItem('authorization');
    let refreshToken = localStorage.getItem('refreshToken');
    fetch("http://localhost:8000/login/access-token?refreshToken="+refreshToken,{
        method:"GET",            
    })
    .then((res)=>res.json())
    .then(res =>{
        localStorage.removeItem('refreshtoken');
        localStorage.setItem('authorization',res.jwtToken);
        localStorage.setItem('refreshToken', res.refreshToken);
     })
}

// 멘토 등록 신청서 작성(제출), 실명인증이 안되어있는경우 제출 불가.
let isAccount = false;
document.getElementById('sendResume').addEventListener('click', function(){
    if(isAccount){
        const userId = localStorage.getItem('userNo');
        fetch("http://localhost:8000/mentors/application?userId="+userId,{
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
                window.alert('이미 신청한 지원서가 존재합니다.');
                location.href="mentee-mypage.html";
                return false;
            } else {
                document.getElementById('id-hidden').value = userId;
                document.getElementById('file-form').submit();
                fetch("http://localhost:8000/mentors/application",{
                method:"POST",
                headers:{
                    "Content-Type":"application/json;",
                    Authorization:localStorage.getItem('authorization'),
                    RefreshToken:localStorage.getItem('refreshToken')
                },
                body:JSON.stringify({
                    name :document.getElementById("mentor-name").value,
                    phone : document.getElementById("mentor-phone").value,
                    email : document.getElementById("mentor-email").value,
                    job : document.getElementById("job").value,
                    bank : document.getElementById("realBankCode").value,
                    account : document.getElementById("realBankAccount").value,
                    userId : localStorage.getItem('userNo')
                })
            })    
            .then((res)=>res.json())
            .then(res =>{
                if(res > 0){
                    window.alert('멘토 신청 완료');
                    location.href="mentor-main.html";
                } else {
                    window.alert('멘토 신청 실패');
                    return false;
                }    
            })
            }
        })
        document.getElementById('id-hidden').value='';
    } else{
        alert('실명인증이 완료되지 않았습니다.');
        return false;
    }
});

//토큰 + 계좌번호, 생년월일, 은행 토큰+정보로 보내기(실명인증)
document.getElementById('accountButton').addEventListener('click', function(){
    fetch("http://localhost:8000/mentors/openapi/realname",{
        method:"POST",
        headers:{
            "Content-Type":"application/json; charset=UTF-8",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),     
        },
        body:JSON.stringify({
            bankCode : document.getElementById("realBankCode").value,
            bankAccount : document.getElementById("realBankAccount").value,
            birthday : document.getElementById("realBirthday").value
        })
    })
    .then((res)=>res.json())
    .then(res =>{
        console.log(res);
        if(res.rsp_code =='A0321'){
            alert('생년월일이 형식에 부적합 합니다');
            isAccount = false;
        }

        if(res.rsp_code =='A0004'){
            alert('계좌번호가 비어있습니다');
            isAccount = false;
        }

        if(res.rsp_code =='A0002'){
            alert('해당정보가 금융기관에 존재하지 않습니다');
            isAccount = false;
        }

        if(res.rsp_code =='A0000'){
            alert('실명인증에 성공하셨습니다');
            isAccount = true;
        }

    })
});

// 리뷰 제출하기
const writeReview = () => {
    const userId = localStorage.getItem('userNo');
    const boardId = document.getElementById("boardId").value;
    fetch("http://localhost:8000/mentors/score/mentee?userId="+userId+"&boardId="+boardId,{
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
                window.alert('해당 글에 대한 작성한 리뷰가 존재합니다.');
                return false;
            } else{
            fetch("http://localhost:8000/mentors/score",{
                method:"POST",
                headers:{
                    "Content-Type":"application/json;",
                    Authorization:localStorage.getItem('authorization'),
                    RefreshToken:localStorage.getItem('refreshToken')
                },
                body:JSON.stringify({
                    boardId :document.getElementById("boardId").value,
                    count : document.getElementById("count").value,
                    content : document.getElementById("content").value,
                    userId : localStorage.getItem('userNo')
                })
            })    
            .then((res)=>res.json())
            .then(res =>{
                if(res > 0){
                    window.alert('리뷰 작성 완료');
                    location.href="mentee-mypage.html";
                }
            })
        }
    });
}

// 메인페이지 글 뿌려주기
const getBoardList = () =>{
    fetch("http://localhost:8000/mentors/boards",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },  
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res.status==401){
            getAccessToken();
            location.href = "mentor-main.html";
        } else {
            let cnt = 1;
            document.getElementById('board-list-div').innerHTML += '<div class="row" style="padding:20px;" id="row-div">';
            for(let board of res.data){
                let boardId = board.id;
                let category = board.category;
                let nickname = board.nickname;
                let career = board.career;
                let title = board.title;
            if(cnt%4 !== 0 ){
                document.getElementById('row-div').innerHTML +=  `<div class="col-3">
                <div class="card" style="width: 13rem; height:14rem;">
                <div class="card-body" onclick="getBoardDetail(${boardId});"  
                data-bs-toggle="modal" href="#exampleModalToggle">
                <h5 class="card-title">${category}</h5><h6 class="card-subtitle mb-2 text-muted">
                ${nickname}</h6><h6 class="card-subtitle mb-2 text-muted">
                ${career}</h6><p class="card-text">제목:${title}</p>
                </div><input class="hidden-board-id" id=${boardId} type="hidden" value=board.id><button class="btn btn-outline-success" onclick="watchReview('${nickname}');" 
                type="button" data-bs-toggle="modal" data-bs-target="#showReview">평점보기</button></div></div>`;
                cnt += 1;
                document.getElementById('boardId-modal').value = boardId;
            } else {
                document.getElementById('row-div').innerHTML += 
                `<div class="col-3">
                <div class="card" style="width: 13rem; height:14rem;">
                <div class="card-body" onclick="getBoardDetail(${boardId});"
                data-bs-toggle="modal" href="#exampleModalToggle">
                <h5 class="card-title">${category}</h5><h6 class="card-subtitle mb-2 text-muted">
                ${nickname}</h6><h6 class="card-subtitle mb-2 text-muted">
                ${career}</h6><p class="card-text">제목:${title}</p>
                </div><input class="hidden-board-id" id=${boardId} type="hidden" value='+board.id+'><button class="btn btn-outline-success" onclick="watchReview('${nickname}');"
                 type="button" data-bs-toggle="modal" data-bs-target="#showReview">평점보기</button></div></div>
                 </div><div class="row" style="padding:20px;" id="row-div">`;
                cnt += 1;
                document.getElementById('boardId-modal').value = boardId;
            }
      }
        }        
    })
}

// 글 신고하기
const reportBoard = () =>{
    const id = document.getElementById('boardId-modal').value;
    fetch("http://localhost:8000/mentors/board/"+id,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        const userId = res.mentor.userId;
        const bId = res.id;
        fetch("http://localhost:8000/login/report",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                reportTitle : document.getElementById('reportTitle').value,
                reportContent : document.getElementById('reportContent').value,
                boardId : bId,
                serviceName : "mentor",
                userNo : userId
            })
        })
    })
    .then(res =>{
        alert('신고가 접수되었습니다.');
        location.href="mentor-main.html";
    })
}

// 글번호에 대해 글 상세 보기
const getBoardDetail = (bId) =>{
    fetch("http://localhost:8000/mentors/board/"+bId,{
        method:"GET",
        headers:{
        "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        console.log("res : " + res);
        if(res != null){
            document.getElementById('modal-body').innerHTML = "자기소개 : " + res.introduction + "<br/>";
            document.getElementById('modal-body').innerHTML += "글 내용 : " + res.content;
            document.getElementById('modal-salary').innerHTML = "1회 멘토링 : 1시간 /" +  res.salary + "원";
            document.getElementById('modal-mentoringDate').innerHTML = "멘토링 날짜 : " + res.mentoringDate.substr(0,10) +"&nbsp&nbsp"+ res.mentoringDate.substr(11,5);
            document.getElementById('board-price').value = res.salary;
        } else {
            console.log('실패');
        }
    })
}


// 멘토 닉네임을 이용해서 멘토에 대한 모든 리뷰 가져오기.
const watchReview = (nickname) =>{
    fetch("http://localhost:8000/mentors/score?nickname="+nickname,{
        method:"GET",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res != null){
            let star ='';
            for(const score of res.data){
                switch(score.count){
                    case 5 : star ='★★★★★'; break;1
                    case 4 : star ='★★★★'; break; 
                    case 3 : star ='★★★'; break; 
                    case 2 : star ='★★'; break; 
                    case 1 : star ='★'; break;
                }
                document.getElementById('reviewBody').innerHTML +=  '<br/>' + star + '<br/>' + '<br/>' + score.content + '<br/>';
            }
        } else {
            console.log('실패');
        }
    })
    document.getElementById('reviewBody').innerHTML ='';
}

// 완료된 멘토링 목록
const endMentoring = ()=>{
        const userId = localStorage.getItem('userNo');
        console.log(userId);
        fetch("http://localhost:8000/mentors/board?userId="+userId,{
            method:"GET",
            headers:{
                "Content-Type":"application/json;",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
            },
        })
        .then((res)=>res.json())
        .then(res =>{
        if(res!=null){
            for(const endBoard of res.data){
                document.getElementById('endBoardList').innerHTML +=
                `<td>${endBoard.id}</td>
                <td>${endBoard.nickname}</td>
                <td>${endBoard.title}</td>
                <td style="text-align: center;">
                    <button class="btn btn-outline-success" type="button" data-bs-toggle="modal" href="#writeReview"
                    onclick="showBoardId(${endBoard.id});"
                    style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">리뷰쓰기</button>
                </td>`
            }
        } 
     })
    document.getElementById('endBoardList').innerHTML ='';
}

// 리뷰 작성 페이지 글번호 보여주기
const showBoardId = (boardId)=>{
    document.getElementById('boardId').value = boardId;
}

// 신청한 멘토링 목록
const allMentoring =()=>{
    const userId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/board?userId="+userId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res!=null){
            for(const allBoard of res.data){
                document.getElementById('mentoringRow').innerHTML +=
                `<th scope="row">${allBoard.id}</th>
                <td>${allBoard.nickname}</td>
                <td>${allBoard.title}</td>
                <td style="text-align: center;">${allBoard.status}</td>`
            }
        } else {
            for(const endBoard of res.data){
                document.getElementById('mentoringRow').innerHTML +=
                `<th scope="row">${allBoard.id}</th>
                <td>${allBoard.nickname}</td>
                <td>${allBoard.title}</td>
                <td style="text-align: center;">${allBoard.status}</td>`
            }
        }
    })
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


// 리뷰 관리 페이지로 이동하기
document.getElementById('moveReviewPage').addEventListener('click',function(){
    location.href="mentee-review-page.html";
});

// 멘토서비스보내기
document.getElementById('move-mentor-service').addEventListener('click',function(){
    location.href="mentor-main.html";   
});
