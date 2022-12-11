window.onload=function(){
    console.log("main : " + localStorage.getItem('Authorization'));
    console.log("main : " + localStorage.getItem('RefreshToken'));
}

document.getElementById('logoutBtn').addEventListener('click',function(){
    localStorage.removeItem("Authorization");
<<<<<<< HEAD
   localStorage.removeItem("RefreshToken");
=======
	localStorage.removeItem("RefreshToken");
>>>>>>> origin/main
    deleteCookie('Authorization');
    deleteCookie('RefreshToken');
    location.href="loginpage.html";
})

function deleteCookie(name) {
<<<<<<< HEAD
   document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
=======
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

>>>>>>> origin/main
