window.onload = function(){
    localStorage.removeItem('bId');
    fetch('http://localhost:8000/bung/')
}

document.getElementById('write-btn').addEventListener('click',function(){

    fetch('http://localhost:8000/bung/recent-board', {
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res)=>res.json())
    .then(res=>{
        console.log(res.bId);
        localStorage.setItem("bId", res.bId);
        location.href="bung-Service-writerPage.html";
    })
});