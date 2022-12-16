//관리자 페이지에서 권한부여 누를 경우 해당 유저의 authStatus를 YES로 변하게 한다.
document.getElementById('auth-btn').addEventListener('click', function(){

    fetch("http://localhost:8000/mentors/member",{
        method:"POST",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
        body:JSON.stringify({
            userId : document.getElementById('userId').value
        })
    })    
    .then((res)=>res.json())
    .then(res =>{
        console.log(res);
        if(res > 0){
            window.alert('권한 부여 성공');
        } else {
            window.alert('권한 부여 실패');
        }
    })
});