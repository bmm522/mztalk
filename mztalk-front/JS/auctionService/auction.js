let searchPage = 1;
let finishPage = 1;
let mainForm = true;
let searchForm = false;
let finishForm = false;

//뿌려주기
window.onload = function(){
    mainLoad(1);
}

window.onscroll = () =>{
    // console.log(isSearchPerformed);
   
    if (window.innerHeight + window.scrollY >= document.getElementById('list-div').offsetHeight &&mainForm) {
        document.getElementById('auctionCard2').innerHTML = '';
        document.getElementById('auctionCard3').innerHTML = '';
        page++;
        mainLoad(page);
    }

    if (window.innerHeight + window.scrollY >= document.getElementById('search-list-div').offsetHeight &&searchForm) {
        searchPage++;
        searchLoad(searchPage);
    }

    if(window.innerHeight + window.scrollY >= document.getElementById('list-finish-div').offsetHeight &&finishForm){
        finishPage++;
        closeEvent(finishPage);
    }
}

//검색, 뿌려주기
document.getElementById("searchBtn").addEventListener('click', function() {
    document.getElementById('auctionCard1').innerHTML = '';
    document.getElementById('auctionCard2').innerHTML = '';
    mainForm = false;
    searchForm = true;
    searchLoad(1);
});

// 메인 리스트 뽑아오기
const mainLoad = (page) =>{

    fetch('http://localhost:8000/auction/board/page/'+page, {
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
            //document.getElementById('list-div').innerHTML = '<div class="row " id="auctionCard">';
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
                let createdDate = board.createdDate.substr(0,10);

                if(i%2 !== 0){
                document.getElementById('auctionCard1').innerHTML += `
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
                            <small>남은 시간:&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                            <input type="hidden" class="timeLimitHour" value="${timeLimitHour}"/>
                            <input type="hidden" class="timeLimitMinute" value="${timeLimitMinute}"/>
                        </li>
                        <li class="d-flex align-items-center">
                            <svg class="bi me-2" width="1em" height="1em">
                            <use xlink:href="#calendar3" />
                            </svg>
                            <small>${createdDate}</samll>
                            <span class = "currnetPriceArea" style="font-weight: bold; margin-left: 5px;">${currentPrice}원</span>
                        </li>
                        </ul>
                    </div>
                    </div>
                </div>`
                i++;
                } else{
                    document.getElementById('auctionCard1').innerHTML += `<div class="col-6" onclick ="moveDeatils(${boardId});" style="pointer:cursor;">
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
                                    <small>남은 시간:&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                                </li>
                                <li class="d-flex align-items-center">
                                    <svg class="bi me-2" width="1em" height="1em">
                                    <use xlink:href="#calendar3" />
                                    </svg>
                                    <small>${createdDate}</samll>
                                    <span class = "currnetPriceArea" style="font-weight: bold; margin-left: 5px;">${currentPrice}원</span>
                                    <input type="hidden" class="timeLimitHour" value="${timeLimitHour}"/>
                                    <input type="hidden" class="timeLimitMinute" value="${timeLimitMinute}"/>
                                </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    </div><div class="row " id="auctionCard">`
                    i++;
                }
                
            }
            let timeLimitAlert = document.getElementsByClassName("timeLimitAlert");
            for(let i = 0; i < timeLimitAlert.length; i++) {
                if(document.getElementsByClassName('timeLimitHour')[i].value == 0 && document.getElementsByClassName('timeLimitMinute')[i].value == 0) {
                    timeLimitAlert[i].innerHTML = '입찰마감';
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

// 검색 리스트
const searchLoad = (searchPage) =>{
    fetch('http://localhost:8000/auction/board/keyword/' + document.getElementById('searchBoard').value+'/'+searchPage, {
        method: "GET",
        headers: {
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res)=>res.json())
    .then(res => {
        let i = 1;
            // document.getElementById('list-div').innerHTML = '<div class="row " id="auctionCard">';
            for(let board of res.data){
                let title = board.title;
                let timeLimit = board.timeLimit;
                let currentPrice = board.currentPrice;
                let imageUrl = board.imageUrl;
                let imageName = board.imageName;
                let boardId = board.boardId;
                let timeLimitHour = -board.timeLimit.hour;
                let timeLimitMinute = -board.timeLimit.minute;
                let createdDate = board.createdDate.substr(0, 10);
                
                if(i%2 !== 0){
                document.getElementById('auctionCard3').innerHTML += `
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
                        <small>남은 시간:&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                        <input type="hidden" class="timeLimitHour" value="${timeLimitHour}"/>
                        <input type="hidden" class="timeLimitMinute" value="${timeLimitMinute}"/>
                        </li>
                        <li class="d-flex align-items-center">
                            <svg class="bi me-2" width="1em" height="1em">
                            <use xlink:href="#calendar3" />
                            </svg>
                            <small>${createdDate}</samll>
                            <span style="font-weight: bold;">${currentPrice}원</span>
                        </li>
                        </ul>
                    </div>
                    </div>
                </div>`
                i++;
                } else{
                    document.getElementById('auctionCard3').innerHTML += `<div class="col-6" onclick ="moveDeatils(${boardId});" style="pointer:cursor;">
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
                        <small>남은 시간:&nbsp<span style="color: red;" class = "timeLimitAlert">${timeLimitHour}시간 ${timeLimitMinute}분 전</span></small>
                        <input type="hidden" class="timeLimitHour" value="${timeLimitHour}"/>
                        <input type="hidden" class="timeLimitMinute" value="${timeLimitMinute}"/>
                        </li>
                        <li class="d-flex align-items-center">
                            <svg class="bi me-2" width="1em" height="1em">
                            <use xlink:href="#calendar3" />
                            </svg>
                            <small>${createdDate}</samll>
                            <span style="font-weight: bold;">${currentPrice}원</span>
                        </li>
                        </ul>
                    </div>
                    </div>
                </div>
        
                
            </div><div class="row " id="auctionCard">`
            i++;
                }
            }
            let timeLimitAlert = document.getElementsByClassName("timeLimitAlert");
            for(let i = 0; i < timeLimitAlert.length; i++) {
                if(document.getElementsByClassName('timeLimitHour')[i].value == 0 && document.getElementsByClassName('timeLimitMinute')[i].value == 0) {
                    console.log('클래스 : ' + timeLimitAlert[i]);
                    timeLimitAlert[i].innerHTML = '입찰마감';
                }
            }
        })
}

// document.getElementById('flexSwitchCheckChecked2').addEventListener('click', function(){

// })

//마감 게시글 제외
function isClose() {
    if(document.getElementById('flexSwitchCheckChecked2').checked) {
        document.getElementById('auctionCard1').innerHTML = '';
        document.getElementById('auctionCard3').innerHTML = '';
        finishForm = true;
        mainForm = false;
        searchForm = false;
        closeEvent(1);
    } else {
        location.href="auction.html";
        document.getElementById('auctionCard2').innerHTML = '';
        finishForm = false;
        mainForm = true;
        mainLoad(1);

    }
    
}

const closeEvent = (finishPage) => {

        
        fetch('http://localhost:8000/auction/board/close/' + finishPage, {
            method: "GET",
            headers: {
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            }
        })
        .then((res)=>res.json())
        .then(res=>{
         
            let i = 1;
            // document.getElementById('list-div').innerHTML = '<div class="row " id="auctionCard">';
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
                let createDate = board.createDate;

                if(i%2 !== 0){
                document.getElementById('auctionCard2').innerHTML += `
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
                    
                    document.getElementById('auctionCard2').innerHTML += `<div class="col-6" onclick ="moveDeatils(${boardId});" style="pointer:cursor;">
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
  
}









