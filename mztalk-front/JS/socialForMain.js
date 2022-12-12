window.onload=function(){
    console.log("main : " + localStorage.getItem('Authorization'));
    console.log("main : " + localStorage.getItem('RefreshToken'));
}

document.getElementById('logoutBtn').addEventListener('click',function(){
    // localStorage.removeItem("Authorization");
	// localStorage.removeItem("RefreshToken");
    // deleteCookie('Authorization');
    // deleteCookie('RefreshToken');
    location.href="test?userNo="+1+".html";
})

function deleteCookie(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}