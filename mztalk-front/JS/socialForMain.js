window.onload=function(){
    console.log("main : " + localStorage.getItem('authorization'));
    console.log("main : " + localStorage.getItem('refreshToken'));
    console.log("main : " + localStorage.getItem('userNo'));
    console.log("main : " + localStorage.getItem('userNickname'));
    

}

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