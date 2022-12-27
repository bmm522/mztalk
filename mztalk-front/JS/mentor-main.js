window.onload = function(){
    getBoardList();
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
    document.getElementById('boardId-modal').value = bId;
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
        if(res != null){
            document.getElementById('modal-body').innerHTML = "자기소개 : " + res.introduction + "<br/>";
            document.getElementById('modal-body').innerHTML += "글 내용 : " + res.content;
            document.getElementById('modal-mentoringDate').innerHTML = "멘토링 날짜 : " + res.mentoringDate.substr(0,10) +"&nbsp&nbsp"+ res.mentoringDate.substr(11,5);
            document.getElementById('modal-salary').innerHTML = "1회 멘토링 : 1시간 /" +  res.salary + "원";
            // 결제 하기 위한 금액 설정
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
                    case 5 : star ='★★★★★'; break;
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

// 검색 조건
document.getElementById('sendSearch').addEventListener('click', function(){
    const categoryValue = document.getElementById('category').value;
    const salaryValue = document.getElementById('salary').value;
    const selected = document.getElementById('type').value;
    const searchValue = document.getElementById('searchValue').value;

    fetch("http://localhost:8000/mentors/board/search?category=" + categoryValue + "&salary=" + salaryValue + "&" + selected + "=" + searchValue,{
        method:"GET",
        headers:{
            "Content-Type":"application/json; charset=UTF-8",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res != ''){
            let cnt = 1;
            document.getElementById('board-list-div').innerHTML += '<div class="row" style="padding:20px;" id="row-div">';
            for(let board of res.data){
                let boardId = board.id;
                let category = board.category;
                let nickname = board.nickname;
                let career = board.career;
                let title = board.title;
                if(cnt%4 !== 0 ){
                    document.getElementById('row-div').innerHTML =  `<div class="col-3">
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
        } else if(res ==''){
            location.href="mentor-main.html";
        }
    });
});

// 정렬 조건
const sort = document.getElementById('sort');
sort.addEventListener('change', function(){
    const sortValue = this.value;

    console.log("http://localhost:8000/mentors/board/search?sort="+sortValue);
    fetch("http://localhost:8000/mentors/board/search?sort="+sortValue,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res > 0){
            console.log('통신성공');
        } else {
            console.log('실패');
        }
    })
});

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

// boardId이용해서 리뷰 >> 점수 평균 가져오기.
// document.getElementById('watchScore').addEventListener('click', function(){
//     const boardId = document.getElementById('boardId').value
//     fetch("http://localhost:8000/mentors/score/"+boardId,{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json;",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken')
//         },
//     })
//     .then((res)=>res.json())
//     .then(res =>{
//         console.log("res : " + res);
//         if(res > 0){
//             console.log('통신성공');
//         } else {
//             console.log('실패');
//         }
//     })
// });
