window.onload = function(){
    getBoardList();
}

document.getElementById('mentor-write-btn').addEventListener('click',function(){

    fetch("http://localhost:8000/mentors/board",{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            category:document.getElementById('write-category').value,
            title : document.getElementById('title').value,
            nickname : localStorage.getItem('userNickname'),
            userId : localStorage.getItem('userNo'),
            content : document.getElementById('content').value,
            introduction : document.getElementById('introduction').value,
            career : document.getElementById('career').value,
            salary : document.getElementById('salary').value
        })
    })
        .then(res =>{
            window.location.replace("mentor-main.html");
        })
             
});

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
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" id="modal" style="width: 13rem; height:14rem;"><div class="card-body" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.career+'</h6><p class="card-text">제목:'+board.title+'</p></div><input type="hidden" value='+board.id+'/><button class="btn btn-outline-success" type="button">평점보기</button></div></div>';
                cnt += 1;  
            } else {
                document.getElementById('row-div').innerHTML +=  '<div class="col-3"><div class="card" id="modal" style="width: 13rem; height:14rem;"><div class="card-body" data-bs-toggle="modal" href="#exampleModalToggle"><h5 class="card-title">'+board.category+'</h5><h6 class="card-subtitle mb-2 text-muted">'+board.nickname+'</h6><h6 class="card-subtitle mb-2 text-muted">'+board.career+'</h6><p class="card-text">제목:'+board.title+'</p></div><input type="hidden" value='+board.id+'/><button class="btn btn-outline-success" type="button">평점보기</button></div></div></div><div class="row" style="padding:20px;" id="row-div">';
                cnt += 1;  
            }
      }
        }        
    })

}


// document.getElementById('myPage').addEventListener('click',function(){
    
//     fetch("http://localhost:8000/mentors/board",{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         },
//         body:JSON.stringify({
//             category:document.getElementById('write-category').value,
//             title : document.getElementById('title').value,
//             mentorNickname : localStorage.getItem('userNickname'),
//             userNo : localStorage.getItem('userNo'),
//             content : document.getElementById('content').value,
//             introduction : document.getElementById('introduction').value,
//             career : document.getElementById('career').value
//         })
//     })
//         .then(res =>{
//             location.href="mentee-myPege.html";
//         })
             
// });