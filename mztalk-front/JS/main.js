// const commentInput = document.querySelector('.commentWrap input');
// const addComment = document.querySelector('.textWrap .comment');
// const commetBtn = document.querySelector('.commetBtn');
// const commentStart = document.querySelector('.commentStart');
// const heart = document.querySelector('.heart');
// const heartColor = document.querySelector('.heartColor');
// const trashBtn = document.querySelector('i.far.fa-trash-alt');
// const commentHeart = document.querySelector('.fa-heart');
// const commentHeartColor = document.querySelector('.colorHeart');


// commentInput.addEventListener('keypress', function(value){
//     let myName = '94_yongyong_lee';
//     let addCommentUnoderList = document.createElement('ul'); 
//     let addCommentList = document.createElement('li');
//     let boldNameWrap = document.createElement('h4');
//     let commentInnerTextBox = document.createElement('div');
//     let heartBox = document.createElement('div');

//     if(value.code === "Enter"){
//         boldNameWrap.innerText = myName;
//         addCommentList.innerText = commentInput.value;
//         commentStart.appendChild(addCommentUnoderList);
//         addCommentUnoderList.appendChild(commentInnerTextBox);
//         commentInnerTextBox.appendChild(boldNameWrap);
//         commentInnerTextBox.appendChild(addCommentList);
//         addCommentUnoderList.appendChild(commentInnerTextBox);

//         addCommentUnoderList.appendChild(heartBox);
//         addCommentUnoderList.appendChild(heartBox);
//         heartBox.appendChild(commentHeartColor);
//         heartBox.appendChild(commentHeart);
//         heartBox.appendChild(trashBtn);
        
        
//         addCommentUnoderList.style.justifyContent ="space-between";     
//         addCommentUnoderList.style.display ="flex";     
//         boldNameWrap.style.marginRight = "5px";
//         heartBox.style.flexDirection = "row-reverse";
//         heartBox.style.display = "flex";
//         commentInnerTextBox.style.display = "flex";
//         commentHeart.style.display = "flex";
//         trashBtn.style.marginRight = "5px";
//         trashBtn.style.display = "flex";
//         return commentInput.value = "";
//     };
// })

// function addCommentListBtn(){
//     let myName = '94_yongyong_lee';
//     let addCommentUnoderList = document.createElement('ul'); 
//     let addCommentList = document.createElement('li');
//     let boldNameWrap = document.createElement('h4');

//     boldNameWrap.innerText = myName;
//     addCommentList.innerText = commentInput.value;
//     commentStart.appendChild(addCommentUnoderList);
//     addCommentUnoderList.appendChild(boldNameWrap);
//     addCommentUnoderList.appendChild(addCommentList);
    
//     boldNameWrap.style.marginRight = "5px";
//     addCommentUnoderList.style.display ="flex";
//     return commentInput.value = "";
// }

// function redHeartColorChange(){
//     heartColor.style.display = "inline-block";
//     heart.style.display = "none";
// }

// function blackHeartColorChange(){
//     heart.style.display = "inline-block";
//     heartColor.style.display = "none";
// }

// function changeButtonColor(value){
//     if(value.length !== 1){
//         commetBtn.style.color = "#0095f6";
//     }
//     if(value.code === "Enter"){
//         return commetBtn.style.color ="#BFE0FD";
//     }
// }
// function buttonColorReset(){
//     return commetBtn.style.color ="#BFE0FD";
// }
        
// function removeComment(){
//     let removeText = document.querySelector('.commentStart ul');
//     return removeText.remove();
// }

// function commentHeartChangeRed(){
//     commentHeart.style.display = "none";
//     commentHeartColor.style.display = "flex";
// }

// function commentHeartChangeBlack(){
//     commentHeart.style.display = "flex";
//     commentHeartColor.style.display = "none";
// }

// function toggleLike() {
// 	let likeIcon = $("#storyLikeIcon-1");
// 	if (likeIcon.hasClass("far")) {
// 		likeIcon.addClass("fas");
// 		likeIcon.addClass("active");
// 		likeIcon.removeClass("far");
// 	} else {
// 		likeIcon.removeClass("fas");
// 		likeIcon.removeClass("active");
// 		likeIcon.addClass("far");
// 	}
// }




// commetBtn.addEventListener('click',addCommentListBtn);
// commetBtn.addEventListener('click',buttonColorReset);
// commentInput.addEventListener('keydown',changeButtonColor);
// trashBtn.addEventListener('click', removeComment);
// commentHeart.addEventListener('click', commentHeartChangeRed);
// commentHeartColor.addEventListener('click', commentHeartChangeBlack);

//
// localStorage.removeItem('own');
// localStorage.setItem('own', userNo);


//개인페이지
document.getElementById('move-story-service').addEventListener('click',function(){
     const own = localStorage.getItem("userNo");
  
     localStorage.setItem("own", own);
    
    //이 구조는 그냥 버튼에 자기페이지 가는 로직
    //해당유저 게시판엔 userNo이 input hidden 걸려있어야 가게끔
    //개인페이지에서 다른 페이지 넘어가면 own을 초기화 삭제 해줘야한다
    //친구페이지 갈때는 set하면 안될듯
    
    fetch("http://localhost:8000/story/"+own,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        
    })
    .then((res)=> res.json())
    .then(res=>{
        console.log(own);
        location.href="individualpage.html";
    })
});

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
// document.getElementById('move-bung-servicce').addEventListener('click', function(){
    
//     fetch("http://localhost:8000/",{

//     })





// });


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
        
        // console.log("옥션 : " + localStorage.getItem('authorization'));
        // console.log("옥션 : " + localStorage.getItem('refreshToken'));
        // console.log("옥션 : " + localStorage.getItem('userNo'));
        // console.log("옥션 : " + localStorage.getItem('userNickname'));
        
        
        



        location.href="auction.html";
        
        


    })


});


