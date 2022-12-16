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

document.getElementById('sendResume').addEventListener('click', function(){

    fetch("http://localhost:8000/mentors/application",{
        method:"POST",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('Authorization'),
            RefreshToken:localStorage.getItem('RefreshToken')
        },
        body:JSON.stringify({
            userId : localStorage.getItem('userNo'),
            name :document.getElementById("name").value,
            phone : document.getElementById("phone").value,
            email : document.getElementById("email").value,
            job : document.getElementById("job").value,
            bank : document.getElementById("bank").value,
            account : document.getElementById("account").value
        })
    })
    .then((res)=>res.json())
    .then(res =>{
        console.log("res : " + res);
        if(res > 0){
            console.log('통신성공');
            location.href="mentor-mypage.html";
        } else {
            console.log('실패');
        }
    })
});