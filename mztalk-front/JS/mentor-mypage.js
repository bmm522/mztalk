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