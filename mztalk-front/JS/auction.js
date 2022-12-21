
//뿌려주기
window.onload = function(){
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
                let title = board.title;
                let timeLimit = board.timeLimit;
                let currentPrice = board.currentPrice;
                let imageUrl = board.imageUrl;
                let imageName = board.imageName;
                let boardId = board.boardId;
                if(i%2 !== 0){
                document.getElementById('auctionCard').innerHTML += `
                <div class="col-6" onclick = 'moveDeatils(${boardId});' style="cursor:pointer;">
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










