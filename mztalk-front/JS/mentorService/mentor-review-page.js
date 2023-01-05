window.onload = function(){
    getMyReview();
}

// 토큰 만료 시 토큰 얻기
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

const getMyReview = () =>{
    const userId = localStorage.getItem('userNo');
    fetch(`${LOCALHOST_URL}/mentors/score/mentor/${userId}`,{
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
            for(let score of res.data){
                document.getElementById('myReviewList').innerHTML += 
                `
                <tr>
                    <td>${score.id}</td>
                    <td>${score.count}</td>
                    <td>
                        <button type="button"  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                        class="btn btn-outline-success" onclick="myReview(${score.id});" data-bs-toggle="modal" data-bs-target="#myReview">보기</button>
                    </td>
                </tr> 
                
                <div class="modal fade" id="myReview" aria-hidden="true" aria-labelledby="myReview" tabindex="-1">
                    <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="myReview">리뷰 보기 페이지</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="scoreId" class="form-label">리뷰 고유 번호</label>
                                <input type="text" class="form-control form-control-sm" id="scoreId" value="${score.id}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="count" class="form-label">점수</label>
                                <input type="text" class="form-control form-control-sm" id="count" value="${score.count}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="content" class="form-label">리뷰 내용</label>
                                <textarea class="form-control" id="content" style="height: 300px; padding-top: 10px;">${score.content}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>`;
            }        
        }
    });
}

// 내가 작성한 리뷰 얻기
const myReview = (scoreId) =>{
    fetch(`${LOCALHOST_URL}/mentors/score/${scoreId}`,{
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
            document.getElementById('scoreId').value = scoreId;
            document.getElementById('content').innerHTML = res.data.content;
        } else {
            alert('리뷰가 존재하지 않습니다');
        }
    })
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

//멘토서비스보내기
document.getElementById('move-mentor-service').addEventListener('click',function(){
    location.href="mentor-main.html";   
});