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

// 멘토 등록 신청서 작성
document.getElementById('sendResume').addEventListener('click', function(){

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
        console.log(res);
        if(res > 0){
            window.alert('멘토 신청 완료');
            location.href="mentee-mypage.html";
        } else {
            console.log('실패');
        }
    })
});


// 리뷰 제출하기
document.getElementById('sendResume').addEventListener('click', function(){

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
            console.log('실패');
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
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" id="modal" style="width: 13rem; height:14rem;"><div class="card-body" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.job+'</h6><p class="card-text">제목:'+board.title+'</p></div><input type="hidden" value='+board.id+'/><button class="btn btn-outline-success" type="button">평점보기</button></div></div>';
                cnt += 1;  
            } else {
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" id="modal" style="width: 13rem; height:14rem;"><div class="card-body" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.job+'</h6><p class="card-text">제목:'+board.title+'</p></div><input type="hidden" value='+board.id+'/><button class="btn btn-outline-success" type="button">평점보기</button></div></div></div><div class="row" style="padding:20px;" id="row-div">';
                cnt += 1;  
            }
      }
        }        
    })

}