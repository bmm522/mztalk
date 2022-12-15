window.onload = function(){
     getBoardList();
}

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
            if(cnt%4 !== 0 ){
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" id="modal" style="width: 13rem; height:14rem;"><div class="card-body" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.career+'</h6><p class="card-text">제목:'+board.title+'</p></div><input id="boardId" type="hidden" value='+board.id+'><button class="btn btn-outline-success" id="watchScore" type="button">평점보기</button></div></div>';
                cnt += 1;  
            } else {
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" id="modal" style="width: 13rem; height:14rem;"><div class="card-body" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.career+'</h6><p class="card-text">제목:'+board.title+'</p></div><input id="boardId" type="hidden" value='+board.id+'><button class="btn btn-outline-success" id="watchScore" type="button">평점보기</button></div></div></div><div class="row" style="padding:20px;" id="row-div">';
                cnt += 1;  
            }
      }
        }        
    })

}

// 글 상세 조회
// const modal = document.getElementById("modal");
// modal.addEventListener("click", function(){
//     const bId = document.getElementById('boardId').value;
//     console.log("http://localhost:8000/mentors/board/"+bId);
//     fetch("http://localhost:8000/mentors/board/"+bId,{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json",
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


// 검색 조건
document.getElementById('sendSearch').addEventListener('click', function(){
    const categoryValue = document.getElementById('category').value;
    const salaryValue = document.getElementById('salary').value;
    const selected = document.getElementById('type').value;
    const searchValue = document.getElementById('searchValue').value;

    console.log("http://localhost:8000/mentors/board/search?category=" + categoryValue + "&salary=" + salaryValue + "&" + selected + "=" + searchValue);
    fetch("http://localhost:8000/mentors/board/search?category=" + categoryValue + "&salary=" + salaryValue + "&" + selected + "=" + searchValue,{
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
        if(res > 0){
            console.log('통신성공');
        } else {
            console.log('실패');
        }
    })
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
        console.log("res : " + res);
        if(res > 0){
            console.log('통신성공');
        } else {
            console.log('실패');
        }
    })
});

//참가 신청
document.getElementById('participant-btn').addEventListener('click', function(){

    fetch("http://localhost:8000/mentors/participant",{
        method:"POST",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
        body:JSON.stringify({
            userId : localStorage.getItem('userNo'),
            boardId : document.getElementById('boardId').value,
            name :document.getElementById("name").value,
            phone : document.getElementById("phone").value,
            email : document.getElementById("email").value,
            message : document.getElementById("message").value
        })
    })
    .then((res)=>res.json())
    .then(res =>{
        console.log("res : " + res);
        if(res > 0){
            console.log('통신성공');
            location.href="mentor-main.html";
        } else {
            console.log('실패');
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

//닉네임을 이용해서 멘토에 대한 모든 리뷰 가져오기.
// document.getElementById('watchScore').addEventListener('click', function(){
//     const nickname = document.getElementById('nickname').value;
//     fetch("http://localhost:8000/mentors/score?nickname="+nickname,{
//         method:"POST",
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
//             for(const score of res.data){
//                 console.log(score.count);
//                 console.log(score.content);
//             }
//         } else {
//             console.log('실패');
//         }
//     })
// });


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




























