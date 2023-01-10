
window.onload = ()=>{
    load(1);
    // ROLEVIP();
    // checkVip();
    console.log("main : " + localStorage.getItem('authorization'));
    console.log("main : " + localStorage.getItem('refreshToken'));
    console.log("main : " + localStorage.getItem('userNo'));
    console.log("main : " + localStorage.getItem('userNickname'));
    console.log("main : " + localStorage.getItem('role'));
}

window.onscroll = () => {
    if (window.innerHeight + window.scrollY >= document.querySelector("#storyList").offsetHeight) {      
        page++;
      load(page);
    }
  };

async function fetchData(endpoint) {
  const res = await fetch(endpoint, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("authorization"),
      RefreshToken: localStorage.getItem("refreshToken"),
    },
  });
  const json = await res.json();
  const data = json.data;
  return data;
}

async function load(page) {
   
  const own = localStorage.getItem("userNo");

  console.log(FOLLOW_URL + own + "/" + page ,"???");

  const newAuctionBoard = await fetchData(AUCTION_URL + page);
  const newFollowBoard = await fetchData(FOLLOW_URL + own + "/" + page);
  const newBungBoard = await fetchData(BUNG_URL + page);
  const newMentorBoard = await fetchData(MENTOR_URL + page);

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

  const sorted_list = result
    .sort(function (a, b) {
      return new Date(a.createdDate).getTime() - new Date(b.createdDate).getTime();
    })
    .reverse();
  
    for (let board of result) {
        
        //서비스별
        let title = board.title;
        let content = board.content;
        let boardId = board.id;
        let imageUrl = board.imageUrl;
        let writer = board.writer;
        let serviceName = board.serviceName;
        let bookTitle = board.bookTitle;
        let own = board.own;
        let userNo = board.userNo;
        let boardWriterId = board.boardWriterId;
        let nickname = board.nickname;
        let mentors = board.mentor;
        let bId = board.boardId;
        let postImageUrl = board.postImageUrl;
        let mentorUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/eca2a863-533a-4b19-9e98-d716addc5ad1-mentor.jpg";
        let ima = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";

        if (serviceName.includes('mentor')) {
            document.querySelector("#storyList").innerHTML +=
                `
            <div class="card mb-3" style="width: 750px;" style="height: 250px;">
                <div class="row g-0" style="height: 250px;"> 
                <div class="col-md-4" style="overflow: hidden; height:250px">
                <img class="profile-image" src='${mentorUrl}' onerror="this.src='${ima}'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
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
                     <img class="profile-image" src='${imageUrl}' onerror="this.src='${ima}'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
                    </div>

                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${bookTitle}</h5>
                            <button onclick="moveMainToAuction(${bId});">자세히보기</button>
                            <div class="separator"></div>
                                <span class="badge text-bg-success" id="serviceAuction" value="serviceAuctions">경매</span>
                            <p class="card-text">${title}</p>
                            
                            <div class="author" id="auction_own" onclick="moveAuctionToStory(${userNo});">${writer}
                                <input type="hidden" value="${userNo}">
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
                  <img class="profile-image" src='${imageUrl}' onerror="this.src='${ima}'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
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
                  <img class="profile-image" src='${postImageUrl}' onerror="this.src='${ima}'" id="userProfileImage"  width="100%;" height="100%;" object-fit="cover;">
                  </div>
                  <div class="col-md-8">
                      <div class="card-body">
                      <h5 class="card-title">${title}</h5>
                      <div class="separator"></div>
                      <span class="badge text-bg-secondary" id="story"">스토리</span>
                      <p class="card-text">${content}</p>
                      <div class="author" id="story_own" onclick="movePages(${own});">${writer}</div>
                        <input type="hidden" value="${own}">            
                      </div>
                  </div>
                </div>
            </div>
            `;
        }
    }
}

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
    fetch(`${LOCALHOST_URL}/mentors/board/${bId}`,{
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
}

