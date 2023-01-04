const movePages = (own) =>{
     
    localStorage.setItem('own', own);
    location.href="individualpage.html";
}
const moveAuctionToStory = (userNo) =>{
    
    localStorage.setItem('own', userNo);
    location.href="individualpage.html";
}
const moveBungToStory = (boardWriterId)=>{
     
    localStorage.setItem('own', boardWriterId);
    location.href="individualpage.html";
}

const movementorToStory = (mentorId)=>{
   
    localStorage.setItem('own', mentorId);
    location.href="individualpage.html";

}