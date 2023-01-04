document.getElementById('logoutBtn').addEventListener('click',function(){
    // localStorage.removeItem("Authorization");
	// localStorage.removeItem("RefreshToken");
    // deleteCookie('Authorization');
    // deleteCookie('RefreshToken');

    location.href="mentor-main.html";
})

function deleteCookie(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

// 토큰 만료 시 토큰 재발급
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
        localStorage.setItem('refreshToken',res.refreshToken);
    })
}

 
