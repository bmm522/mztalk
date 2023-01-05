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

// 내가 작성한 리뷰 얻기
const getMyReview = () =>{
    const userId = localStorage.getItem('userNo');
    fetch(`${LOCALHOST_URL}/mentors/score/mentee/${userId}`,{
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
                    <td>${score.content}</td>
                    <td>
                        <button type="button"  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                        class="btn btn-outline-success" onclick="myReview(${score.id});" data-bs-toggle="modal" data-bs-target="#myReview">보기</button>
                    </td>
                    <td>
                        <button type="button"  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                        class="btn btn-outline-danger" onclick="deleteReview(${score.id});">삭제</button>
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
                                <input type="text" class="form-control form-control-sm" id="scoreId" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="count" class="form-label">별점</label>
                                <select id="count" class="form-select">
                                    <option value="5.0">★★★★★</option>
                                    <option value="4.0">★★★★</option>
                                    <option value="3.0">★★★</option>
                                    <option value="2.0">★★</option>
                                    <option value="1.0">★</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="content" class="form-label">리뷰 내용</label>
                                <textarea class="form-control" id="content" style="height: 300px; padding-top: 10px;"></textarea>
                            </div>
                        </div>
                    <div class="modal-footer">
                        <button class="btn btn-outline-success" type="button" onclick="modifyReview();" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">수정하기</button>
                    </div>
                </div>
            </div>
        </div>`;
            }        
        }
    });
}

// 리뷰 디테일
const myReview = (scoreId) =>{
    document.getElementById('scoreModifyId').value = scoreId;
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

// 리뷰 수정하기
const modifyReview = () => {
    const modifyId= document.getElementById('scoreModifyId').value;
    fetch(`${LOCALHOST_URL}/mentors/score/${modifyId}`,{
         method:"PATCH",
         headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
         },
         body:JSON.stringify({
            count : document.getElementById("count").value,
            content : document.getElementById("content").value,
        })
     })    
     .then((res)=>res.json())
     .then(res =>{
         if(res != null){
            window.alert('리뷰 수정 완료');
            location.href="mentee-review-page.html";
        } else{
            window.alert('리뷰 수정에 실패하셨습니다.');
            location.href="mentee-review-page.html";
        }
     })
 }

// 리뷰 삭제 메소드
const deleteReview = (deleteId) => {
    fetch(`${LOCALHOST_URL}/mentors/score/${deleteId}`,{
        method:"DELETE",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res != null){
            window.alert('리뷰가 삭제 되었습니다.');
            location.href="mentee-review-page.html";
        } else {
            window.alert('리뷰가 삭제에 실패했습니다.');
            location.href="mentee-review-page.html";
        }
    })
}

// 리뷰 작성 페이지 글번호 보여주기
const showBoardId = (boardId)=>{
    document.getElementById('boardId').value = boardId;
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