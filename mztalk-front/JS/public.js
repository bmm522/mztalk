const movePage = (own) =>{
     
    localStorage.setItem('own', own);
    location.href="individualpage.html";
}
const moveAuctionToStory = (i) =>{
    
    localStorage.setItem('own', i);
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