window.onload=function(){
    console.log("main : " + localStorage.getItem('authorization'));
    console.log("main : " + localStorage.getItem('refreshToken'));
    console.log("main : " + localStorage.getItem('userNo'));
    console.log("main : " + localStorage.getItem('userNickname'));
    console.log("main : " + localStorage.getItem('role'));
    ROLEVIP();
    checkVip();

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


const checkVip = () =>{
    
    let userNo = localStorage.getItem('userNo');

    fetch("http://localhost:8000/story/checkVip/"+userNo,{
          method:"PUT",
          headers:{
              "Content-Type":"application/json",
              Authorization:localStorage.getItem('authorization'),
              RefreshToken:localStorage.getItem('refreshToken'),
          },
          body:JSON.stringify({
            userNo : localStorage.getItem('userNo'),
        })
    })
    .then((res)=>res.json())
    .then(res =>{
        console.log(res.data,"?????????");
        if(res.data==1){
            fetch("http://localhost:8000/login/role/user/"+userNo,{
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json;",
                    Authorization:localStorage.getItem('authorization'),
                    RefreshToken:localStorage.getItem('refreshToken')
                },
                data: JSON.stringify({
                    userNo : localStorage.getItem('userNo'),
                })
            })
            alert('VIP이용이 종료되었습니다. 감사합니다');
            localStorage.removeItem('role')
            localStorage.setItem('role', 'ROLE_USER');

            location.href="main.html";
        }
        
      })
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

 
