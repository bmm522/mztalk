//const { main } = require("@popperjs/core");

let isSearchPerformed = false;
let isMainPerformed = true;
window.onload = function(){
    getBoardList(1);
    addFile();
}

window.onscroll = () =>{
    if (window.innerHeight + window.scrollY >= document.getElementById('board-list-div').offsetHeight && isMainPerformed) {
        page++;
        getBoardList(page);
    }
}


// 멘토 신청시 첨부파일 추가 버튼 생성
const addFile = () =>{
    document.getElementById("addFile").addEventListener('click', () => { 
        if(document.getElementsByName("file").length < 3) {
            const newDiv = document.createElement('div');
            newDiv.classList.add('col-10');
            newDiv.innerHTML = '<input type="file" class = "form-control" name = "file">';
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
    fetch(`${LOCALHOST_URL}/login/access-token?refreshToken=${refreshToken}`,{
        method:"GET",            
    })
    .then((res)=>res.json())
    .then(res =>{
        localStorage.removeItem('refreshtoken');
        localStorage.setItem('authorization',res.jwtToken);
        localStorage.setItem('refreshToken',res.refreshToken);
    })
}

// 멘토 등록 신청서 작성(제출), 실명인증이 안되어있는경우 제출 불가.
let isAccount = false;
document.getElementById('sendResume').addEventListener('click', function(){
    if(isAccount){
        const userId = localStorage.getItem('userNo');
        fetch(`${LOCALHOST_URL}/mentors/application?userId=${userId}`,{
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
                fetch(`${LOCALHOST_URL}/mentors/application`,{
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
                    birthday : document.getElementById('realBirthday').value,
                    userId : localStorage.getItem('userNo')
                })
                })    
            .then((res)=>res.json())
            .then(res =>{
                if(res != null){
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
    fetch(`${LOCALHOST_URL}/mentors/openapi/realname`,{
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

// 완료된 멘토링 목록 >> 리뷰 버튼 클릭시 리뷰 작성 페이지
const endMentoring = ()=>{
    const userId = localStorage.getItem('userNo');
    fetch(`${LOCALHOST_URL}/mentors/board?userId=${userId}`,{
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

// 리뷰 제출하기
const writeReview = () => {
    const userId = localStorage.getItem('userNo');
    const boardId = document.getElementById('boardId').value;
    fetch(`${LOCALHOST_URL}/mentors/score/mentee?userId=${userId}&boardId=${boardId}`,{
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
            location.href="mentee-myPage.html";
            return false;
        } else{
            fetch(`${LOCALHOST_URL}/mentors/score`,{
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
                if(res != null){
                    window.alert('리뷰 작성 완료');
                    location.href="mentee-mypage.html";
                }
                if(res.status == 500){
                    window.alert('리뷰 작성 실패');
                }
            })
        }
    });
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

// 글 신고하기
const reportBoard = () =>{
    const id = document.getElementById('boardId-modal').value;
    fetch(`${LOCALHOST_URL}/mentors/board/${id}`,{
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
          alert('상세보기 실패');
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

// 신청한 멘토링 목록
const allMentoring =()=>{
    const userId = localStorage.getItem('userNo');
    fetch(`${LOCALHOST_URL}/mentors/board/mentee/${userId}`,{
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
                `<td scope="row">${allBoard.id}</th>
                <td>${allBoard.nickname}</td>
                <td>${allBoard.title}</td>
                <td style="text-align: center;">${allBoard.status}</td>
                <td><button type="button" class="btn btn-outline-danger" onclick="cancelPay(${allBoard.payment.id},'${allBoard.payment.impUid}','${allBoard.payment.merchantUid}',${allBoard.payment.price},'${allBoard.mentoringDate}');" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">결제 취소</button></td>`
            }
        } else {
            document.getElementById('mentoringRow').innerHTML = '<td colspan="5">신청한 게시글이 존재하지 않습니다.</td>';
        }
    })
    document.getElementById('mentoringRow').innerHTML ='';
}

//아임포트 결제 취소
function cancelPay(paymentId,impUid,merchantUid,price,mentoringDate) {
    const today = new Date();
    if(today>new Date(mentoringDate)){
        alert('완료된 멘토링은 취소할 수 없습니다.');
        location.href="mentee-myPage.html";
        return false;
    } else{
        $.ajax({
            url:`${LOCALHOST_URL}/mentors/api/import/cancel`,
            headers: { 
                "Content-Type": "application/json;",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
            },
            type: "POST",
            data: JSON.stringify({
                imp_uid:impUid,
                merchant_uid: merchantUid,
                cancel_request_amount: price
            }),
            dataType: "json"
        }).done(function(result){
            if(result.code == "0"){
                $.ajax({
                    url: `${LOCALHOST_URL}/mentors/payment/cancel/${paymentId}`,
                    headers: { 
                        "Content-Type": "application/json;",
                        Authorization:localStorage.getItem('authorization'),
                        RefreshToken:localStorage.getItem('refreshToken')
                    },type: "POST"
                }).done(function(result){
                    alert('결제가 취소되었습니다.');
                }).fail(function(result){
                    alert('결제취소에 실패하셨습니다.');
                    return false;
                })
            }

            if(result.code != "0"){
                alert(result.message);
                location.href="mentee-myPage.html";
                return false;
            }    
        });
    }
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


// 리뷰 관리 페이지로 이동하기
document.getElementById('moveReviewPage').addEventListener('click',function(){
    location.href="mentee-review-page.html";
});

// 멘토서비스보내기
document.getElementById('move-mentor-service').addEventListener('click',function(){
    location.href="mentor-main.html";   
});
