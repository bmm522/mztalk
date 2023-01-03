//let auctionResult = null;


window.addEventListener('load',  async ()=> {
    //경매
    const newAuctionBoard = await fetch("http://localhost:8000/auction/board",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=> res.json())
    .then(res=>{     

      const A = []
      
      for(let auction of res.data){
          
        A.push(auction)
       
      }
      //console.log('A:', A)
      return A
  })
  console.log('newAuctionBoard:', newAuctionBoard);

    //팔로우들 글
    let own = localStorage.getItem('userNo');
    
    const newFollowBoard = await fetch("http://localhost:8000/story/main/"+own,{
            method:"GET",
            headers:{
                "Content-Type":"application/json",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            },
        })
        .then((res)=> res.json())
        .then(res=>{       
        const F = []

        for(let follow of res.data){
            
         F.push(follow)

        }
        //console.log('A:', A)
        return F
        })

        console.log('newFollowBoard:', newFollowBoard);

    

    //벙
    const newBungBoard = await fetch("http://localhost:8000/bung/mainBoards",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
        .then((res)=> res.json())
        .then(res=>{     

        const B = []
        
        for(let bung of res.data){
            
            B.push(bung)
        
        }
        //console.log('A:', A)
        return B
    })
     console.log('newBungBoard:', newBungBoard);

    //멘토newMentorBoard
    const newMentorBoard = await fetch("http://localhost:8000/mentors/board/latest",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
        .then((res)=> res.json())
        .then(res=>{     

        const M = []
        
        for(let mentor of res.data){
            
            M.push(mentor)
        
        }
        //console.log('A:', A)
        return M
    })
     console.log('newMentorBoard:', newMentorBoard);

    const all=[];
    all.push(newMentorBoard);
    all.push(newBungBoard);
    all.push(newFollowBoard);
    all.push(newAuctionBoard);

    console.log(all, "뭐있니?");

    const result = [];

    for (const innerArray of all) {
        for (const element of innerArray) {
            result.push(element);
        }
    }
  

    result.sort((a, b) => new Date(a.createDate) - new Date(b.createDate));
    console.log(result, "ㅎㅎ");

    for(let board of result){
        //console.log(board+"??");
        //서비스별
        let title = board.title;
        let content = board.content;
        let boardId = board.id;
        let imageUrl = board.imageUrl;
        let writer = board.writer;

        document.querySelector('#storyList').innerHTML +=
        `
        <div class="card mb-3" style="width: 750px;" style="height: 150px;">
            <div class="row g-0" style="height: 150px;"> 
            <div class="col-md-4" style="overflow: hidden; height:250px">
              <img class="profile-image" src='${imageUrl}' onerror="this.src='duck.jpg'" id="userProfileImage" width: 250px" height="150"> 
            </div>
            <div class="col-md-8">
                <div class="card-body">
                <h5 class="card-title">${content}</h5>
                <input type="hidden" value="${boardId}">
                <p class="card-text">${title}</p>
                </div>
            </div>
            </div>
        </div>
        `
    }
})

{/* <span class="badge text-bg-primary" id="serviceMetors">멘토</span> */}

// array.sort(function(a, b) {
//     a = new Date(a.dateModified);
//     b = new Date(b.dateModified);
//     return a>b ? -1 : a<b ? 1 : 0;
// });





// (2) 스토리 스크롤 페이징하기
let page =0;
window.onscroll = function(e){
    //window height + window scrollY 값이 document height 보다 클 경우

    // console.log("윈도우 scrollTop :", window.scrollY);
	// console.log("문서의 높이 :", document.body.offsetHeight);
	// console.log("윈도우 높이(window height):", window.innerHeight);
    
    let checkNum = window.scrollY -(document.body.offsetHeight -window.innerHeight);

    if(checkNum < 1 && checkNum > -1){
		page++;
		//newMentorBoard();
        //실행할 로직
    
	}

}

//팔로우한 사람 글 뿌려쥐
//  const newFollowBoard = async()=>{
//     // /main/{own}
    
//     let own = localStorage.getItem('userNo');

//     await fetch("http://localhost:8000/story/main/"+own,{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         },
//       })
//       .then((res)=> res.json())
//       .then(res=>{       
        
//         console.log("팔로우최신글"+res.data);
//         for(let follow of res.data){
//             console.log(follow+"뭐있니?");
//             return follow;
//         }
        
//     })

// }

//나중에 불러오기
//멘토 최신글뽑아오기
const newMentorBoard = () =>{ 
    //console.log("성공?");
    fetch("http://localhost:8000/mentors/board/latest",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
      .then((res)=> res.json())
      .then(res=>{       
        
        console.log("멘토최신글"+res.data);

      

        //document.getElementById('storyList').innerHTML += '<div class="card mb-3" style="max-width: 900px;">';
        
        for(let board of res.data){
            let boardId = board.id;
            let category = board.category;
            let nickname = board.nickname;
            let title = board.title;
            let content = board.content;
            //let date = board.lastModifiedDate.substr(0,10); // 0~10까지

            
            document.querySelector('.col-md-6').innerHTML +=
            `
            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                    <strong class="d-inline-block mb-2 text-primary">${category}</strong>
                    <h3 class="mb-0">${title}</h3>
                    <div class="mb-1 text-muted">${date}</div>
                    <p class="card-text mb-auto">
                    ${content}
                    </p>
                    <a href="#" class="stretched-link">자세히보기</a>
                </div>
                <div class="col-auto d-none d-lg-block">
                    <img src="img/recommend_02.jpeg" style="width: 150px; height: 250px;" >      
                </div>
                </div>
            </div>
            `;
            }
        } 
    )}


//3개 다 받을때 db를 하나 만들어서 status으로  해서 클릭시 status가 no가 되게끔
//if(mentor&&bung&&auction){newMentorBoard(); getBungBoard(); getAuctionBoard();}




//경매서비스
//auction/board
// async function newAuctionBoard(){ 
    
//     const response = fetch("http://localhost:8000/auction/board",{
//         method:"GET",
//         headers:{
//             "Content-Type":"application/json",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         },
//     })
//     .then((res)=> res.json())
//     .then(res=>{       
//       const A = []
      
//       for(let auction of res.data){
          
//         A.push(auction)
       
//       }
//       //console.log('A:', A)
//       return A
//   })
// }


//벙서비스
///mainBoards
async function newBungBoard(){

    const responses = fetch("http://localhost:8000/bung/mainBoards",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
    })
    .then((res)=> res.json())
    .then(res=>{       
      
      console.log("벙최신글"+res.data);
      for(let bung of res.data){
      return bung;
      }
  })

}

// const latest = () =>{

//     const modi = [newAuctionBoard(),newMentorBoard()];


//     console.log(modi);
//     //const result = modi.filter(board => board.board.lastModifiedDate);

//     let dList = new Array();

//     dList.push(newAuctionBoard());
//     dList.push(newMentorBoard());

//     console.log(dList.reduce((prev, curr) => {
//         return new Date(prev).getTime() <= new Date(curr).getTime() ? curr : prev;
//     }));


// }


// 화살표 함수를 이용해서 콜백함수를 전달한다.
// console.log(sDayList.reduce((prev,curr) => {
// 	// 이전것과 비교해 더 큰 것을 리턴
//     return new Date(prev).getTime() <= new Date(curr).getTime() ? curr : prev;
// }));
 
// console.log(sDayList.reduce((prev,curr) => {
// 	// 이전것과 비교해 더 작은 것 리턴
//     return new Date(prev).getTime() <= new Date(curr).getTime() ? prev : curr;
// }));
 
// => 최종적으로 남은 하나만 리턴


//사진 로테이션
let slideIndex = 0;
showSlides();
function showSlides(){ 
    let i;
    let slides = document.getElementsByClassName("advertisement");
//console.log(slides);
        for(i = 0; i<slides.length; i++){
        slides[i].style.display = "none";
        }  
        slideIndex ++;
        if (slideIndex > slides.length) {slideIndex = 1}
        slides[slideIndex-1].style.display = "block";  
        setTimeout(showSlides, 10000);      
} 
