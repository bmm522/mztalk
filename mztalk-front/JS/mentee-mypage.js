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

// 멘토 등록 신청서 작성 이미존재할 경우 return false 강제로 작성하면 서버측에서 Exception발생
document.getElementById('sendResume').addEventListener('click', function(){
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
            fetch("http://localhost:8000/mentors/application",{
            method:"POST",
            headers:{
                "Content-Type":"application/json;",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
             },
            body:JSON.stringify({
                name :document.getElementById("name").value,
                phone : document.getElementById("phone").value,
                email : document.getElementById("email").value,
                job : document.getElementById("job").value,
                bank : document.getElementById("bank").value,
                account : document.getElementById("account").value,
                userId : localStorage.getItem('userNo')
            })
        })    
        .then((res)=>res.json())
        .then(res =>{
            if(res > 0){
                window.alert('멘토 신청 완료');
                location.href="mentee-mypage.html";
            } else {
                window.alert('멘토 신청 실패');
                return false;
            }
    })
        }
    })
});


// 리뷰 제출하기
document.getElementById('writeReview').addEventListener('click', function(){

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
        console.log(res);
        if(res > 0){
            window.alert('리뷰 작성 완료');
            location.href="mentee-mypage.html";
        } else {
            window.alert('리뷰 작성 실패');
        }
    })
});


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
            if(cnt%4 !== 0 ){
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" id="modal" style="width: 13rem; height:14rem;"><div class="card-body" id="modal" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.career+'</h6><p class="card-text">제목:'+board.title+'</p></div><input type="hidden" value='+board.id+'/><button class="btn btn-outline-success" type="button">평점보기</button></div></div>';
                cnt += 1;  
            } else {
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" id="modal" style="width: 13rem; height:14rem;"><div class="card-body" id="modal" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.career+'</h6><p class="card-text">제목:'+board.title+'</p></div><input type="hidden" value='+board.id+'/><button class="btn btn-outline-success" type="button">평점보기</button></div></div></div><div class="row" style="padding:20px;" id="row-div">';
                cnt += 1;  
            }
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
        console.log("res : " + res);
        if(res){
            location.href="mentor-mypage.html";
        } else {
            location.href="mentee-mypage.html";
        }
    })
});

