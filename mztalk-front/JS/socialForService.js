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
        let nickname = board.nickname;
        let mentors = board.mentor;
        let bId = board.boardId;
        
        //let mentorNickname = board.nickname;
        //console.log(mentorId,"??");
        //console.log(mentorNickname+"//3333/");
   
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
                    <button data-bs-toggle="modal" data-bs-target="#exampleModalToggle" onclick="moveMainToMentor(${boardId});">자세히보기</button>
                    <div class="separator"></div>
                     <span class="badge text-bg-primary" id="serviceMetors">멘토</span>
                    <p class="card-text">${content}</p>
                    <div class="author" onclick="movementorToStory(${mentors.userId});">${nickname}
                        <input type="hidden" class="mentorId_find" value="${mentors.userId}">
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
                            <button onclick="moveMainToAuction(${bId});">자세히보기</button>
                            <div class="separator"></div>
                                <span class="badge text-bg-success" id="serviceAuction" value="serviceAuctions">경매</span>
                            <p class="card-text">${title}</p>
                            
                            <div class="author" id="auction_own" onclick="moveAuctionToStory(${i});">${writer}
                                <input type="hidden" value="${i}">
                                <input type="hidden" id="auctionWriter" value="${writer}">
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
                      <button onclick="moveMainToBung(${bId});">자세히보기</button>
                      <div class="separator"></div>
                       <span class="badge text-bg-info" id="serviceBung">벙</span>
                      <p class="card-text">${content}</p>
                      <div class="author" id="bung_own" onclick="moveBungToStory(${boardWriterId});">${writer}</div>
                        <input type="hidden" value="${boardWriterId}"> 
                        <input type="hidden" id="bungService" value="${serviceName}">           
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
                      <div class="separator"></div>
                      <span class="badge text-bg-secondary" id="story"">스토리</span>
                      <p class="card-text">${content}</p>
                      <div class="author" id="story_own" onclick="movePage(${own});">${writer}</div>
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
// const movePage = (own) =>{
     
//     localStorage.setItem('own', own);
//     location.href="individualpage.html";
// }
// const moveAuctionToStory = (i) =>{
    
//     localStorage.setItem('own', i);
//     location.href="individualpage.html";
// }
// const moveBungToStory = (boardWriterId)=>{
     
//     localStorage.setItem('own', boardWriterId);
//     location.href="individualpage.html";
// }

// const movementorToStory = (mentorId)=>{
   
//     localStorage.setItem('own', mentorId);
//     location.href="individualpage.html";

// }


//옥션 @GetMapping("/board/{bId}/{writer}")
const moveMainToAuction = (bId)=>{
    localStorage.setItem('bId', bId);
    location.href="auctionDetail.html";
}

// 벙 @GetMapping("/mainBoardSelect/{boardId}")
const moveMainToBung = (bId)=>{
    localStorage.setItem('bId', bId);
    location.href="bung-service-detail.html";
}
//멘토 @GetMapping("/board/{id}")
const moveMainToMentor = (bId)=>{
    localStorage.setItem('bId', bId);
    fetch("http://localhost:8000/mentors/board/"+bId,{
            method:"GET",
            headers:{
            "Content-Type":"application/json",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
            },
        })
        .then((res)=>res.json())
        .then(res =>{
            if(res != null){
                document.getElementById('exampleModalToggleLabel').innerHTML = '멘토-멘티 서비스'
                document.getElementById('modal-body').innerHTML = "자기소개 : " + res.data.introduction + "<br/>";
                document.getElementById('modal-body').innerHTML += "글 내용 : " + res.data.content;
                const modalMentoringDate = document.getElementById('modal-mentoringDate');
                modalMentoringDate.innerHTML = `멘토링 날짜: ${res.data.mentoringDate.substr(0, 10)}&nbsp;${res.data.mentoringDate.substr(11, 5)}`;
                document.getElementById('modal-salary').innerHTML = '1회 멘토링 : 1시간 / '+res.data.salary+'원';
            } else {
                console.log('실패');
            }
        })
        document.getElementById('modal-body').innerHTML = '';

    //location.href="bung-service-detail.html";
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
