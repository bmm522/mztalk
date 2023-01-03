window.onload=function(){
    console.log("main : " + localStorage.getItem('authorization'));
    console.log("main : " + localStorage.getItem('refreshToken'));
    console.log("main : " + localStorage.getItem('userNo'));
    console.log("main : " + localStorage.getItem('userNickname'));
    console.log("main : " + localStorage.getItem('role'));
    ROLEVIP();

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

// ROLE_VIP
function ROLEVIP(){
    
    console.log("main ??: " + localStorage.getItem('role'));
    //console.log("v-pills-profile-tab");
    let ROLE_VIP = localStorage.getItem('role');
    
    //let buttonz = `<button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false" >비밀번호 변경</button>`;
    if(ROLE_VIP.includes("ROLE_USER")){
        document.querySelector("#advertis");
    }
    if(ROLE_VIP.includes('ROLE_VIP')){
        document.querySelector("#advertis").style.display = "none";
    }
  }
