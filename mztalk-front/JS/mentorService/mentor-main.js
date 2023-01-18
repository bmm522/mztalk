let isSearchPerformed = false;
let isMainPerformed = true;
window.onload = function(){
    getBoardList(1);
}

window.onscroll = () =>{
    if (window.innerHeight + window.scrollY >= document.getElementById('board-list-div').offsetHeight && isMainPerformed) {
        document.getElementById('board-list-search-div').innerHTML = '';
        page++;
        getBoardList(page);
    }

    if (window.innerHeight + window.scrollY >= document.getElementById('board-list-search-div').offsetHeight && isSearchPerformed) {
        searchEvent();
    }
}



// 토큰 만료 시 토큰 재발급
const getAccessToken = () =>{
    localStorage.removeItem('authorization');
    let refreshToken = localStorage.getItem('refreshToken');
    fetch(`${LOCALHOST_URL}/login/access-token?refreshToken=${refreshToken}`,{
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
const getBoardList = (page) =>{
    fetch(`${LOCALHOST_URL}/mentors/boards/${page}`,{
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
            for(let i = 0 ; i <res.data.length ; i++){    
            let board = res.data[i];
            let mentorId = board.mentor.userId;
            let boardId = board.id;
            let category = board.category;
            let nickname = board.nickname;
            let career = board.career;
            let title = board.title;
                if(cnt%4 !== 0 ){
                    document.getElementById('row-div1').innerHTML +=  `<div class="col-3">
                    <div class="card" style="width: 13rem; height:14rem;">
                    <div class="card-body" onclick="getBoardDetail(${boardId});"  
                    data-bs-toggle="modal" href="#exampleModalToggle">
                    <h5 class="card-title">${category}</h5><h6 class="card-subtitle mb-2 text-muted">
                    ${nickname}</h6><h6 class="card-subtitle mb-2 text-muted">
                    ${career}</h6><p class="card-text">제목:${title}</p>
                    </div><input class="hidden-board-id" id=${boardId} type="hidden" value=board.id><button class="btn btn-outline-success" onclick="watchReview(${mentorId});" 
                    type="button" data-bs-toggle="modal" data-bs-target="#showReview">리뷰보기</button></div></div>`;
                    cnt += 1;
                } else {
                    document.getElementById('row-div1').innerHTML += 
                    `<div class="col-3">
                    <div class="card" style="width: 13rem; height:14rem;">
                    <div class="card-body" onclick="getBoardDetail(${boardId});"
                    data-bs-toggle="modal" href="#exampleModalToggle">
                    <h5 class="card-title">${category}</h5><h6 class="card-subtitle mb-2 text-muted">
                    ${nickname}</h6><h6 class="card-subtitle mb-2 text-muted">
                    ${career}</h6><p class="card-text">제목:${title}</p>
                    </div><input class="hidden-board-id" id=${boardId} type="hidden" value='+board.id+'><button class="btn btn-outline-success" onclick="watchReview(${mentorId});"
                    type="button" data-bs-toggle="modal" data-bs-target="#showReview">리뷰보기</button></div></div>
                    </div><div class="row" style="padding:20px;" id="row-div">`;
                    cnt += 1;
                }
            }         
        }    
    })
}

// 검색 조건
document.getElementById('sendSearch').addEventListener('click', function(){
    document.getElementById('board-list-div').innerHTML = '';
    document.getElementById('board-list-search-div').innerHTML = '';
    isSearchPerformed = true;
    isMainPerformed = false;
    searchEvent();

});

// 글 신고하기
const reportBoard = () =>{
    const userId = localStorage.getItem('userNo');
    const boardId = document.getElementById('boardId-modal').value;
    fetch(`${LOCALHOST_URL}/mentors/board/mentor?userId=${userId}&boardId=${boardId}`,{
        method: "GET",
        headers: { 
            "Content-Type": "application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        }
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res){
            alert('본인의 글을 신고할 수 없습니다.');
            location.href="mentor-main.html";
            return false;
        } else{
            fetch(`${LOCALHOST_URL}/mentors/board/${boardId}`,{
                method:"GET",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:localStorage.getItem('authorization'),
                    RefreshToken:localStorage.getItem('refreshToken')
                },
            })
            .then((res)=>res.json())
            .then(res =>{
                const userId = res.data.mentor.userId;
                const bId = res.data.id;
                fetch(`${LOCALHOST_URL}/login/report`,{
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
    })    
}

// 글번호에 대해 글 상세 보기
const getBoardDetail = (bId) => {
    const boardIdModal = document.getElementById('boardId-modal');
    boardIdModal.value = bId;
    const headers = {
      "Content-Type": "application/json",
      Authorization: localStorage.getItem('authorization'),
      RefreshToken: localStorage.getItem('refreshToken')
    };
    fetch(`${LOCALHOST_URL}/mentors/board/${bId}`, {
      method: "GET",
      headers
    })
      .then((res) => res.json())
      .then((res) => {
        if (res) {
          const modalBody = document.getElementById('modal-body');
          modalBody.innerHTML = `자기소개: ${res.data.introduction}<br/>`;
          modalBody.innerHTML += res.data.content;
          const modalMentoringDate = document.getElementById('modal-mentoringDate');
          modalMentoringDate.innerHTML = `멘토링 날짜: ${res.data.mentoringDate.substr(0, 10)}&nbsp;${res.data.mentoringDate.substr(11, 5)}`;
          const modalSalary = document.getElementById('modal-salary');
          modalSalary.innerHTML = `1회 멘토링: 1시간 / ${res.data.salary}원`;
          const boardPrice = document.getElementById('board-price');
          boardPrice.value = res.data.salary;
        } else {
          window.alert("실패");
        }
      });
  };

// 멘토 식별자 이용해서 멘토에 대한 모든 리뷰 가져오기.
const watchReview = (mentorId) => {
    fetch(`${LOCALHOST_URL}/mentors/score/mentor/${mentorId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json;",
        Authorization: localStorage.getItem("authorization"),
        RefreshToken: localStorage.getItem("refreshToken"),
      },
    })
      .then((res) => res.json())
      .then((res) => {
        if (res) {
          const reviewBody = document.getElementById("reviewBody");
          reviewBody.innerHTML = "";
          for (const score of res.data) {
            let star = "";
            const content = score.content;
            const count = score.count;
            for (let i = 0; i < count; i++) {
              star +=
                "<img src='https://cdn-icons-png.flaticon.com/512/7656/7656139.png' style='width:30px; height:30px;'/>";
            }
            reviewBody.innerHTML += `${star}<br/>${content}<br/><br/>`;
          }
        } else {
          window.alert("Failed to fetch review");
        }
      });
  };

//글 검색
const searchEvent = () =>{
    const categoryValue = document.getElementById('category').value;
    const salaryValue = document.getElementById('salary').value;
    const selected = document.getElementById('type').value;
    const searchValue = document.getElementById('searchValue').value;

    fetch(`${LOCALHOST_URL}/mentors/board/search?category=${categoryValue}&salary=${salaryValue}&${selected}=${searchValue}`,{
        method:"GET",
        headers:{
            "Content-Type":"application/json; charset=UTF-8",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res.data.length != 0){
            let cnt = 1;
            document.querySelector('#board-list-search-div').innerHTML = '<div class="row" style="padding:20px;" id="row-div2">';
            for(let board of res.data){
                let boardId = board.id;
                let category = board.category;
                let nickname = board.nickname;
                let career = board.career;
                let title = board.title;
                if(cnt%4 !== 0 ){
                    document.getElementById('row-div2').innerHTML +=  `<div class="col-3">
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
                    document.getElementById('row-div2').innerHTML += 
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
        } else if(res.data.length == 0) {
            alert('검색에 맞는 글이 존재하지 않습니다.');
            location.href="mentor-main.html";
        }
    });
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
