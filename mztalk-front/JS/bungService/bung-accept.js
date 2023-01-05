window.onload = function(){
    fetch('http://localhost:8000/bung/accept?nickname='+localStorage.getItem('userNickname'), {
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then(res=>res.json())
    .then(res=>{
        for(let accept of res.data){
            document.getElementById('accept-div').innerHTML +=    
            ` <br><br>

            <div class="listArea" style="width: 935px;">
            <div class="requestList">
                <div class="d-grid gap-2 col-6 requestUserBtn">
                <button type="button" class="btn btn-outline-warning" onclick="moveAcceptDetail(${accept.addId})">상세 보기</button>
                </div>
                <div class="requestUserId">
                    <input type="text" style="text-align: center; width: 150px; border-radius: 50px; border-style: none;" id="groupInput" value="${accept.addNickname}" class="InputGroup" readonly>
                </div>
            </div>
        </div>`;
            
        }
    })
}

const moveAcceptDetail = (addId) =>{
    
    localStorage.setItem('addId', addId);
    location.href="bung-service-request.html";
}

//멘토서비스보내기
document.getElementById('move-mentor-service').addEventListener('click',function(){
    
    fetch("http://localhost:8000/mentors",{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            nickname : localStorage.getItem('userNickname'),
            id : localStorage.getItem('userNo')
        })
    })
    .then(res =>{
        location.href="mentor-main.html";
    })             
});


//벙서비스보내기
document.getElementById('move-bung-service').addEventListener('click',function(){

    //이 구조는 그냥 버튼에 자기페이지 가는 로직
    //해당유저 게시판엔 userNo이 input hidden 걸려있어야 가게끔
    //개인페이지에서 다른 페이지 넘어가면 own을 초기화 삭제 해줘야한다
    //친구페이지 갈때는 set하면 안될듯
    
    fetch("http://localhost:8000/bung/mainBoards",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
            
        },
    })
    .then((res)=> res.json())
    .then(res=>{

        location.href="bung-service-main.html";
    })

});



//경매서비스 보내기
document.getElementById('move-auction-service').addEventListener('click',function(){

    //이 구조는 그냥 버튼에 자기페이지 가는 로직
    //해당유저 게시판엔 userNo이 input hidden 걸려있어야 가게끔
    //개인페이지에서 다른 페이지 넘어가면 own을 초기화 삭제 해줘야한다
    //친구페이지 갈때는 set하면 안될듯
    
    fetch("http://localhost:8000/auction/",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
            
        },
    })
    .then((res)=> res.json())
    .then(res=>{

        location.href="auction.html";

    })

});