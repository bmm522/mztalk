window.addEventListener('load', async () => {
    //경매
    const newAuctionBoard = await fetch("http://localhost:8000/auction/board", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: localStorage.getItem('authorization'),
            RefreshToken: localStorage.getItem('refreshToken'),
        },
    })
        .then((res) => res.json())
        .then(res => {
            const A = []
            for (let auction of res.data) {
                A.push(auction)
            }
            return A
        })
    console.log('newAuctionBoard:', newAuctionBoard);

    //팔로우들 글
    let own = localStorage.getItem('userNo');

    const newFollowBoard = await fetch("http://localhost:8000/story/main/" + own, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: localStorage.getItem('authorization'),
            RefreshToken: localStorage.getItem('refreshToken'),
        },
    })
        .then((res) => res.json())
        .then(res => {
            const F = []
            for (let follow of res.data) {
                F.push(follow)
            }
            return F
        })

    console.log('newFollowBoard:', newFollowBoard);



    //벙
    const newBungBoard = await fetch("http://localhost:8000/bung/mainBoards", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: localStorage.getItem('authorization'),
            RefreshToken: localStorage.getItem('refreshToken'),
        },
    })
        .then((res) => res.json())
        .then(res => {

            const B = []

            for (let bung of res.data) {

                B.push(bung)

            }

            return B
        })
    console.log('newBungBoard:', newBungBoard);

    //멘토newMentorBoard
    const newMentorBoard = await fetch("http://localhost:8000/mentors/board/latest", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: localStorage.getItem('authorization'),
            RefreshToken: localStorage.getItem('refreshToken'),
        },
    })
        .then((res) => res.json())
        .then(res => {

            const M = []

            for (let mentor of res.data) {

                M.push(mentor)

            }

            return M
        })
    console.log('newMentorBoard:', newMentorBoard);

    const all = [];
    all.push(newMentorBoard);
    all.push(newBungBoard);
    all.push(newFollowBoard);
    all.push(newAuctionBoard);

    const result = [];

    for (const innerArray of all) {
        for (const element of innerArray) {
            result.push(element);
        }
    }

    let index = 0;
    for (const obj of result) {
        obj.i = index++;
    }

    const dateArr = [];
    for (const obj of result) {
        dateArr.push(obj.createDate);
    }


    const sorted_list = result.sort(function (a, b) {
        return new Date(a.createdDate).getTime() - new Date(b.createdDate).getTime();
    }).reverse();

    console.log(sorted_list, "?");

    for (let board of result) {
        //console.log(board+"??");
        //서비스별
        let title = board.title;
        let content = board.content;
        let boardId = board.id;
        let imageUrl = board.imageUrl;
        let writer = board.writer;
        let serviceName = board.serviceName;
        let bookTitle = board.bookTitle;
        let own = board.own;
        let i = board.i;
        let boardWriterId = board.boardWriterId;
        
        if (serviceName.includes('mentor')) {
            document.querySelector("#storyList").innerHTML +=
                `
            <div class="card mb-3" style="width: 750px;" style="height: 250px;">
                <div class="row g-0" style="height: 250px;"> 
                <div class="col-md-4" style="overflow: hidden; height:250px">
                <img class="profile-image" src='${imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                    <h5 class="card-title">${title}</h5>
                    <span class="badge text-bg-primary" id="serviceMetors">멘토</span>
                    <p class="card-text">${content}</p>
                    <div class="userNo">${writer}
                    <input type="hidden" value="">
                    </div>
                    </div>
                </div>
                </div>
            </div>
            `;
        }
        else if (serviceName.includes('auction')) {
            document.querySelector("#storyList").innerHTML +=
                `
            <div class="card mb-3" style="width: 750px;" style="height: 250px;">
                <div class="row g-0" style="height: 250px;">
                <div class="col-md-4" style="overflow: hidden; height:250px">
                  <img class="profile-image" src='${imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                    <h5 class="card-title">${bookTitle}</h5>
                    <span class="badge text-bg-success" id="serviceAuction">경매</span>
                    <p class="card-text">${title}</p>
                    <div id="auction_own" onclick="moveAuctionToStory(${i});">${writer}
                        <input type="hidden" value="${i}">
                    </div>
                    </div>
                </div>
                </div>
            </div>
            `;
        }
        else if (serviceName.includes('bung')) {
            document.querySelector("#storyList").innerHTML +=
                `
                <div class="card mb-3" style="width: 750px;" style="height: 250px;">
                <div class="row g-0" style="height: 250px;">
                  <div class="col-md-4" style="overflow: hidden; height:250px">
                  <img class="profile-image" src='${imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
                  </div>
                  <div class="col-md-8">
                      <div class="card-body">
                      <h5 class="card-title">${title}</h5>
                      <span class="badge text-bg-secondary" id="story"">스토리</span>
                      <p class="card-text">${content}</p>
                      <div id="bung_own" onclick="moveBungToStory(${boardWriterId});">${writer}</div>
                        <input type="hidden" value="${boardWriterId}">            
                      </div>
                  </div>
                </div>
            </div>
            `;
        }
        else {
            document.querySelector("#storyList").innerHTML +=
                `
            <div class="card mb-3" style="width: 750px;" style="height: 250px;">
                <div class="row g-0" style="height: 250px;">
                  <div class="col-md-4" style="overflow: hidden; height:250px">
                  <img class="profile-image" src='${imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
                  </div>
                  <div class="col-md-8">
                      <div class="card-body">
                      <h5 class="card-title">${title}</h5>
                      <span class="badge text-bg-secondary" id="story"">스토리</span>
                      <p class="card-text">${content}</p>
                      <div id="story_own" onclick="movePage(${own});">${writer}</div>
                        <input type="hidden" value="${own}">            
                      </div>
                  </div>
                </div>
            </div>
            `;
        }


    }
})

//이름 클릭 시 페이지 넘어가게
// const goStory = (own) =>{

//     fetch("http://localhost:8000/story/"+own,{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         },
//     })
//     .then((res)=> res.json())
//     .then(res=>{
//         location.href="individualpage.html";
//     })
// }

//페이지이동 userNo
const movePage = (own) =>{
    localStorage.removeItem('own'); 
    localStorage.setItem('own', own);
    location.href="individualpage.html";
  }
const moveAuctionToStory = (i) =>{
    localStorage.removeItem('own'); 
    localStorage.setItem('own', i);
    location.href="individualpage.html";

}
const moveBungToStory = (boardWriterId)=>{
    localStorage.removeItem('own'); 
    localStorage.setItem('own', boardWriterId);
    location.href="individualpage.html";

}





//스토리 스크롤 페이징하기
let page = 0;
window.onscroll = function (e) {

    let checkNum = window.scrollY - (document.body.offsetHeight - window.innerHeight);

    if (checkNum < 1 && checkNum > -1) {
        page++;


    }

}

console.log("윈도우 scrollTop :", window.scrollY);
console.log("문서의 높이 :", document.body.offsetHeight);
console.log("윈도우 높이(window height):", window.innerHeight);



//사진 로테이션
let slideIndex = 0;
showSlides();
function showSlides() {
    let i;
    let slides = document.getElementsByClassName("advertisement");
    //console.log(slides);
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) { slideIndex = 1 }
    slides[slideIndex - 1].style.display = "block";
    setTimeout(showSlides, 10000);
} 
