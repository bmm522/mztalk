window.onload=function(){
    
    console.log("옥션 : " + localStorage.getItem('authorization'));
    console.log("옥션 : " + localStorage.getItem('refreshToken'));
    console.log("옥션 : " + localStorage.getItem('userNo'));
    console.log("옥션 : " + localStorage.getItem('userNickname'));
    
    
}

//글쓰기
document.getElementById('writeBoard').addEventListener('click', function(){
    fetch('http://localhost:8000/auction/recent-board',{
        method:"GET"
    })
    .then(res=>res.json())
    .then(res => {
        localStorage.setItem("bId", res.bId);
        location.href="auctionWrite.html";
        document.getElementById("bId").innerHTML = res.bId;
    })
});

//상세 페이지 이동
document.getElementById('title').addEventListener('click', function() {
    
});

//카드 추가
window.onload = () => {
    fetch('http://localhost:8000/auction/board', {
        method: "GET"
    })
    .then(res=>res.json())
    .then(res => {
        for(var card)
    })
}


