//let auctionResult = null;
window.onload = () =>{
   //newMentorBoard();
   //scroll();
   //newAuctionBoard();
   console.log('here!');
   //newAuctionBoard();
   //console.log(auctionResult);
   //latest();
   
}



let boardsURL = "main.html";

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
async function newAuctionBoard(){ 
    
    const response = fetch("http://localhost:8000/auction/board",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      });

      return response;
     
    }

    // newAuctionBoard()
    // .then(res => res.json())
    // .then(json => test(json));

    // function test(data, data){
    //     console.log('here! hear!!')
    //     console.log(data);
    //     let boardAuction = [data, data];

    //     console.log("엄마!"+boardAuction);

    //     return data;
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
      });

      return responses;
}
newBungBoard()
.then(res => res.json())
.then(json => test(json));






    //             exec();
      
    //    async function exec(){
    //         let text;
    //         try{
    //             text = await newAuctionBoard();
    //             console.log(text[0].board);
    //         }
    //         catch(error){
    //             console.log(error);
    //         }
    //    }     


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