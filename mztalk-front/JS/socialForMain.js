// window.addEventListener('DOMContentLoaded', function(){
//  console.log('window addEventListener DOMContentLoaded');
//  const httpRequest = new XMLHttpRequest();
//  console.log(httpRequest.getAllResponseHeaders());
// //  getHeaders();
    
// });

// const getHeaders = () =>{
//     fetch("http://localhost:8000/login/social/setting",{
//         method:"GET",
//     })
//     .then(res=>{
//         console.log('통신성공');
//     })
// }
 

window.onload = function(){

console.log(getCookieValue('Authorization'));
console.log(getCookieValue('RefreshToken'));
console.log(getCookieValue('LoginResult'));


    // fetch("http://localhost:8000/login/social/setting",{
    //     method:"GET",
    //     mode:"no-cors",
    // })
    // .then(res=>{
    //     console.log('통신성공');
    // })
//     console.log('실행됨?');
//      let httpRequest = new XMLHttpRequest();
//      console.log(httpRequest)
    
//   console.log(httpRequest.getAllResponseHeaders());
//   console.log(httpRequest.responseXML)

//   console.log(localStorage.getItem("Authorization"));
//   console.log(localStorage.getItem("RefreshToken"));

// var httpRequest = new XMLHttpRequest();
//     httpRequest.onreadystatechange = function() {
//         if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200) {
//             console.log(httpRequest.responseText);
//             console.log(httpRequest.getAllResponseHeaders());
//             console.log(httpRequest.getResponseHeader("hayanHeader"));
//         }
//     };
};



const getCookieValue = (key) => {
    let cookieKey = key + "="; 
    let result = "";
    const cookieArr = document.cookie.split(";");
    
    for(let i = 0; i < cookieArr.length; i++) {
      if(cookieArr[i][0] === " ") {
        cookieArr[i] = cookieArr[i].substring(1);
      }
      
      if(cookieArr[i].indexOf(cookieKey) === 0) {
        result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
        return result;
      }
    }
    return result;
  }