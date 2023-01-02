
//뿌려주기
window.onload = function(){
    console.log(localStorage.getItem('authorization'));
    console.log(localStorage.getItem('refreshToken'));
    console.log(new Date().getTime());
    
    fetch('http://localhost:8000/auction/board', {
        method:"GET",
        headers:{
            // "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
        .then((res)=>res.json())
        .then(res=>{
            let i = 1;
            document.getElementById('list-div').innerHTML = '<div class="row " id="auctionCard">';
            for(let board of res.data){
                console.log("전체 게시글 데이터: " + JSON.stringify(board));
                let title = board.title;
                let timeLimitHour = -board.timeLimit.hour;
                let timeLimitMinute = -board.timeLimit.minute;
                let currentPrice = board.currentPrice;
                let imageUrl = board.imageUrl;
                let imageName = board.imageName;
                let boardId = board.boardId;
                let writer = board.writer;
                let count = board.count;
                let createDate = board.createDate;

                if(i%2 !== 0){
                document.getElementById('auctionCard').innerHTML += `
                <div class="col-6" onclick = 'moveDeatils(${boardId});' style="pointer:cursor;">
                    <!-- bId-->
                    <input type="hidden" id="hidden-bId"/>
                    <div class="card card-cover m-1 h-20 overflow-hidden text-white bg-dark rounded-5 shadow-lg" class="titleCard"
                    style="background-image: url('${imageUrl}');background-position: 10% 20%">
                    <input type="hidden" name="imageName" value="${imageName}"/>
                    <input type="hidden" name="bNo" value="${boardId}"/>
                    <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                        <div class="row">
                            <small class="col-6">${writer}</small>
                            <small class="col-6" style="text-align: right;">조회수 ${count}</small>
                        </div>
                        <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold" id="title">${title}</h2>
                        <ul class="d-flex list-unstyled mt-auto">
                        <li class="d-flex align-items-center me-3">
                            <small>남은 시간&nbsp:&nbsp&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                        </li>
                        <li class="d-flex align-items-center">
                            <svg class="bi me-2" width="1em" height="1em">
                            <use xlink:href="#calendar3" />
                            </svg>
                            <small style="margin-right: 50px;">${currentPrice}원</small>
                            <small>${createDate}</samll>
                        </li>
                        </ul>
                    </div>
                    </div>
                </div>`
                i++;
                } else{
                    document.getElementById('auctionCard').innerHTML += `<div class="col-6" onclick ="moveDeatils(${boardId});" style="pointer:cursor;">
                    <!-- bId-->
                    <input type="hidden" id="hidden-bId" />
                    <div class="card card-cover m-1 h-20 overflow-hidden text-white bg-dark rounded-5 shadow-lg" class="titleCard"
                    style="background-image: url('${imageUrl}');background-position: 10% 20%">
                        <input type="hidden" name="imageName" value="${imageName}"/>
                        <input type="hidden" name="bNo" value="${boardId}"/>
                            <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                                <div class="row">
                                    <small class="col-6">${writer}</small>
                                    <small class="col-6" style="text-align: right;">조회수 ${count}</small>
                                </div>
                                <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold" id="title">${title}</h2>
                                <ul class="d-flex list-unstyled mt-auto">
                                <li class="d-flex align-items-center me-3">
                                    <small>남은 시간&nbsp:&nbsp&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                                </li>
                                <li class="d-flex align-items-center">
                                    <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#calendar3" />
                                    </svg>
                                    <small style="margin-right: 50px;">${currentPrice}원</small>
                                    <small>${createDate}</samll>
                                </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    </div><div class="row " id="auctionCard">`
                    i++;
                }
                console.log("for문 맨마지막 hour,minute: " + timeLimitHour +", " + timeLimitMinute);
                if(timeLimitHour == 0 && timeLimitMinute == 0) {
                    console.log("for문 안에 hour,minute: " + timeLimitHour +", " + timeLimitMinute);
                    let timeLimitAlert = document.getElementsByClassName("timeLimitAlert");
                    for(let i = 0; i < timeLimitAlert.length; i++) {
                        timeLimitAlert[i].innerHTML = '입찰마감';
                    }
                }
            }
        })

}

//detail 넘어갈 때 boardId 주기
const moveDeatils = (boardId) => {
    localStorage.setItem("bId", boardId);
    location.href="auctionDetail.html";
}


//글쓰기 이동
document.getElementById('writeBoard').addEventListener('click', function(){
    fetch('http://localhost:8000/auction/recent-board',{
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res)=>res.json())
    .then(res => {
        console.log("글쓰기 이동 bId: " + res.bId);
        localStorage.setItem("bId", res.bId);
        location.href="auctionWrite.html";
    })
});

//검색, 뿌려주기
document.getElementById("searchBtn").addEventListener('click', function() {
    fetch('http://localhost:8000/auction/board/keyword/' + document.getElementById('searchBoard').value, {
        method: "GET",
        headers: {
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res)=>res.json())
    .then(res => {
        let i = 1;
            document.getElementById('list-div').innerHTML = '<div class="row " id="auctionCard">';
            for(let board of res.data){
                let title = board.title;
                let timeLimit = board.timeLimit;
                let currentPrice = board.currentPrice;
                let imageUrl = board.imageUrl;
                let imageName = board.imageName;
                let boardId = board.boardId;
                
                if(i%2 !== 0){
                document.getElementById('auctionCard').innerHTML += `
                <div class="col-6" onclick = 'moveDeatils(${boardId});' style="pointer:cursor;">
                    <!-- bId-->
                    <input type="hidden" id="hidden-bId" />
                    <div class="card card-cover m-1 h-20 overflow-hidden text-white bg-dark rounded-5 shadow-lg" class="titleCard"
                    style="background-image: url('${imageUrl}');background-position: 10% 20%">
                    <input type="hidden" name="imageName" value="${imageName}"/>
                    <input type="hidden" name="bNo" value="${boardId}"/>
                    <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                        <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold" id="title">${title}</h2>
                        <ul class="d-flex list-unstyled mt-auto">
                        <li class="d-flex align-items-center me-3">
                            <small>남은 시간&nbsp:&nbsp&nbsp<span style="color: red;">${timeLimit}시간전</span></small>
                        </li>
                        <li class="d-flex align-items-center">
                            <svg class="bi me-2" width="1em" height="1em">
                            <use xlink:href="#calendar3" />
                            </svg>
                            <small>${currentPrice}원</small>
                        </li>
                        </ul>
                    </div>
                    </div>
                </div>`
                i++;
                } else{
                    document.getElementById('auctionCard').innerHTML += `<div class="col-6" onclick ="moveDeatils(${boardId});" style="pointer:cursor;">
                    <!-- bId-->
                    <input type="hidden" id="hidden-bId" />
                    <div class="card card-cover m-1 h-20 overflow-hidden text-white bg-dark rounded-5 shadow-lg" class="titleCard"
                    style="background-image: url('${imageUrl}');background-position: 10% 20%">
                    <input type="hidden" name="imageName" value="${imageName}"/>
                    <input type="hidden" name="bNo" value="${boardId}"/>
                    <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                        <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold" id="title">${title}</h2>
                        <ul class="d-flex list-unstyled mt-auto">
                        <li class="d-flex align-items-center me-3">
                            <small>남은 시간&nbsp:&nbsp&nbsp<span style="color: red;">${timeLimit}시간전</span></small>
                        </li>
                        <li class="d-flex align-items-center">
                            <svg class="bi me-2" width="1em" height="1em">
                            <use xlink:href="#calendar3" />
                            </svg>
                            <small>${currentPrice}원</small>
                        </li>
                        </ul>
                    </div>
                    </div>
                </div>
        
                
            </div><div class="row " id="auctionCard">`
            i++;
                }
            }
        })
});

//마감 게시글 제외
function isClose() {
    if(document.getElementById('flexSwitchCheckChecked2').checked) {
        fetch("http://localhost:8000/auction/board/close", {
            method: "GET",
            headers: {
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            }
        })
        .then((res)=>res.json())
        .then(res=>{
            console.log("마감시간 제외 응답값: " + JSON.stringify(res));
            let i = 1;
            document.getElementById('list-div').innerHTML = '<div class="row " id="auctionCard">';
            for(let board of res.data){
                let title = board.title;
                let timeLimitHour = -board.timeLimit.hour;
                let timeLimitMinute = -board.timeLimit.minute;
                let currentPrice = board.currentPrice;
                let imageUrl = board.imageUrl;
                let imageName = board.imageName;
                let boardId = board.boardId;
                let writer = board.writer;
                let count = board.count;

                if(i%2 !== 0){
                document.getElementById('auctionCard').innerHTML += `
                <div class="col-6" onclick = 'moveDeatils(${boardId});' style="pointer:cursor;">
                    <!-- bId-->
                    <input type="hidden" id="hidden-bId"/>
                    <div class="card card-cover m-1 h-20 overflow-hidden text-white bg-dark rounded-5 shadow-lg" class="titleCard"
                    style="background-image: url('${imageUrl}');background-position: 10% 20%">
                    <input type="hidden" name="imageName" value="${imageName}"/>
                    <input type="hidden" name="bNo" value="${boardId}"/>
                    <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                        <div class="row">
                            <small class="col-6">${writer}</small>
                            <small class="col-6" style="text-align: right;">조회수 ${count}</small>
                        </div>
                        <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold" id="title">${title}</h2>
                        <ul class="d-flex list-unstyled mt-auto">
                        <li class="d-flex align-items-center me-3">
                            <small>남은 시간&nbsp:&nbsp&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                        </li>
                        <li class="d-flex align-items-center">
                            <svg class="bi me-2" width="1em" height="1em">
                            <use xlink:href="#calendar3" />
                            </svg>
                            <small>${currentPrice}원</small>
                        </li>
                        </ul>
                    </div>
                    </div>
                </div>`
                i++;
                } else{
                    document.getElementById('auctionCard').innerHTML += `<div class="col-6" onclick ="moveDeatils(${boardId});" style="pointer:cursor;">
                    <!-- bId-->
                    <input type="hidden" id="hidden-bId" />
                    <div class="card card-cover m-1 h-20 overflow-hidden text-white bg-dark rounded-5 shadow-lg" class="titleCard"
                    style="background-image: url('${imageUrl}');background-position: 10% 20%">
                        <input type="hidden" name="imageName" value="${imageName}"/>
                        <input type="hidden" name="bNo" value="${boardId}"/>
                            <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                                <div class="row">
                                    <small class="col-6">${writer}</small>
                                    <small class="col-6" style="text-align: right;">조회수 ${count}</small>
                                </div>
                                <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold" id="title">${title}</h2>
                                <ul class="d-flex list-unstyled mt-auto">
                                <li class="d-flex align-items-center me-3">
                                    <small>남은 시간&nbsp:&nbsp&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                                </li>
                                <li class="d-flex align-items-center">
                                    <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#calendar3" />
                                    </svg>
                                    <small>${currentPrice}원</small>
                                </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    </div><div class="row " id="auctionCard">`
                    i++;
                }
            }
        });
    } else {
        fetch('http://localhost:8000/auction/board', {
        method:"GET",
        headers:{
            // "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
        .then((res)=>res.json())
        .then(res=>{
            let i = 1;
            document.getElementById('list-div').innerHTML = '<div class="row " id="auctionCard">';
            for(let board of res.data){
                let title = board.title;
                let timeLimitHour = -board.timeLimit.hour;
                let timeLimitMinute = -board.timeLimit.minute;
                let currentPrice = board.currentPrice;
                let imageUrl = board.imageUrl;
                let imageName = board.imageName;
                let boardId = board.boardId;
                let writer = board.writer;
                let count = board.count;

                if(i%2 !== 0){
                document.getElementById('auctionCard').innerHTML += `
                <div class="col-6" onclick = 'moveDeatils(${boardId});' style="pointer:cursor;">
                    <!-- bId-->
                    <input type="hidden" id="hidden-bId"/>
                    <div class="card card-cover m-1 h-20 overflow-hidden text-white bg-dark rounded-5 shadow-lg" class="titleCard"
                    style="background-image: url('${imageUrl}');background-position: 10% 20%">
                    <input type="hidden" name="imageName" value="${imageName}"/>
                    <input type="hidden" name="bNo" value="${boardId}"/>
                    <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                        <div class="row">
                            <small class="col-6">${writer}</small>
                            <small class="col-6" style="text-align: right;">조회수 ${count}</small>
                        </div>
                        <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold" id="title">${title}</h2>
                        <ul class="d-flex list-unstyled mt-auto">
                        <li class="d-flex align-items-center me-3">
                            <small>남은 시간&nbsp:&nbsp&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                        </li>
                        <li class="d-flex align-items-center">
                            <svg class="bi me-2" width="1em" height="1em">
                            <use xlink:href="#calendar3" />
                            </svg>
                            <small>${currentPrice}원</small>
                        </li>
                        </ul>
                    </div>
                    </div>
                </div>`
                i++;
                } else{
                    document.getElementById('auctionCard').innerHTML += `<div class="col-6" onclick ="moveDeatils(${boardId});" style="pointer:cursor;">
                    <!-- bId-->
                    <input type="hidden" id="hidden-bId" />
                    <div class="card card-cover m-1 h-20 overflow-hidden text-white bg-dark rounded-5 shadow-lg" class="titleCard"
                    style="background-image: url('${imageUrl}');background-position: 10% 20%">
                        <input type="hidden" name="imageName" value="${imageName}"/>
                        <input type="hidden" name="bNo" value="${boardId}"/>
                            <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
                                <div class="row">
                                    <small class="col-6">${writer}</small>
                                    <small class="col-6" style="text-align: right;">조회수 ${count}</small>
                                </div>
                                <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold" id="title">${title}</h2>
                                <ul class="d-flex list-unstyled mt-auto">
                                <li class="d-flex align-items-center me-3">
                                    <small>남은 시간&nbsp:&nbsp&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                                </li>
                                <li class="d-flex align-items-center">
                                    <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#calendar3" />
                                    </svg>
                                    <small>${currentPrice}원</small>
                                </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    </div><div class="row " id="auctionCard">`
                    i++;
                }
                console.log("for문 맨마지막 hour,minute: " + timeLimitHour +", " + timeLimitMinute);
                if(timeLimitHour == 0 && timeLimitMinute == 0) {
                    console.log("for문 안에 hour,minute: " + timeLimitHour +", " + timeLimitMinute);
                    let timeLimitAlert = document.getElementsByClassName("timeLimitAlert");
                    for(let i = 0; i < timeLimitAlert.length; i++) {
                        timeLimitAlert[i].innerHTML = '입찰마감';
                    }
                }
            }
        })


    }
}









