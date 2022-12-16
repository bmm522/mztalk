window.onload=function(){
    
    console.log("옥션 : " + localStorage.getItem('authorization'));
    console.log("옥션 : " + localStorage.getItem('refreshToken'));
    console.log("옥션 : " + localStorage.getItem('userNo'));
    console.log("옥션 : " + localStorage.getItem('userNickname'));
    
    
}

document.getElementById('writeBoard').addEventListener('click', function(){
    fetch('http://localhost:8000/auction/recent-board',{
        method:"GET"
    })
    .then(res=>res.json())
    .then(res => {
        console.log(res.bNo);
    })
});