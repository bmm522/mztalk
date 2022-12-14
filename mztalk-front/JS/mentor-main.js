window.onload = function(){
     getBoardList();
}

const getAccessToken = () =>{
    localStorage.removeItem('Authorization');
    let refreshToken = localStorage.getItem('RefreshToken');
    fetch("http://localhost:8000/login/access-token?refreshToken="+refreshToken,{
        method:"GET",            
    })
    .then((res)=>res.json())
    .then(res =>{
        localStorage.removeItem('Refreshtoken');
        localStorage.setItem('Authorization',res.jwtToken);
        localStorage.setItem('RefreshToken', res.refreshToken);
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
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" style="width: 13rem; height:14rem;"><div class="card-body" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.job+'</h6><p class="card-text">제목:'+board.title+'</p></div><button class="btn btn-outline-success" type="button">평점보기</button></div></div>';
                cnt += 1;  
            } else {
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" style="width: 13rem; height:14rem;"><div class="card-body" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.job+'</h6><p class="card-text">제목:'+board.title+'</p></div><button class="btn btn-outline-success" type="button">평점보기</button></div></div></div><div class="row" style="padding:20px;" id="row-div">';
                cnt += 1;  
            }
      }
        }        
    })

}


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
            Authorization:localStorage.getItem('Authorization'),
            RefreshToken:localStorage.getItem('RefreshToken')
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
            Authorization:localStorage.getItem('Authorization'),
            RefreshToken:localStorage.getItem('RefreshToken')
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

// document.getElementById('myBoard').addEventListener('click',function(){
//     const userId = document.getElementById('userId').value;
//     console.log(userId);
//     fetch("http://localhost:8000/mentors/board?userId="+value,{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         },
//     })
//     .then(res =>{
//         location.href="mentor-main.html";
//     })            
// });
































