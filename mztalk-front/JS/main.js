const commentInput = document.querySelector('.commentWrap input');
const addComment = document.querySelector('.textWrap .comment');
const commetBtn = document.querySelector('.commetBtn');
const commentStart = document.querySelector('.commentStart');
const heart = document.querySelector('.heart');
const heartColor = document.querySelector('.heartColor');
const trashBtn = document.querySelector('i.far.fa-trash-alt');
const commentHeart = document.querySelector('.fa-heart');
const commentHeartColor = document.querySelector('.colorHeart');


commentInput.addEventListener('keypress', function(value){
    let myName = '94_yongyong_lee';
    let addCommentUnoderList = document.createElement('ul'); 
    let addCommentList = document.createElement('li');
    let boldNameWrap = document.createElement('h4');
    let commentInnerTextBox = document.createElement('div');
    let heartBox = document.createElement('div');

    if(value.code === "Enter"){
        boldNameWrap.innerText = myName;
        addCommentList.innerText = commentInput.value;
        commentStart.appendChild(addCommentUnoderList);
        addCommentUnoderList.appendChild(commentInnerTextBox);
        commentInnerTextBox.appendChild(boldNameWrap);
        commentInnerTextBox.appendChild(addCommentList);
        addCommentUnoderList.appendChild(commentInnerTextBox);

        addCommentUnoderList.appendChild(heartBox);
        addCommentUnoderList.appendChild(heartBox);
        heartBox.appendChild(commentHeartColor);
        heartBox.appendChild(commentHeart);
        heartBox.appendChild(trashBtn);
        
        
        addCommentUnoderList.style.justifyContent ="space-between";     
        addCommentUnoderList.style.display ="flex";     
        boldNameWrap.style.marginRight = "5px";
        heartBox.style.flexDirection = "row-reverse";
        heartBox.style.display = "flex";
        commentInnerTextBox.style.display = "flex";
        commentHeart.style.display = "flex";
        trashBtn.style.marginRight = "5px";
        trashBtn.style.display = "flex";
        return commentInput.value = "";
    };
})

function addCommentListBtn(){
    let myName = '94_yongyong_lee';
    let addCommentUnoderList = document.createElement('ul'); 
    let addCommentList = document.createElement('li');
    let boldNameWrap = document.createElement('h4');

    boldNameWrap.innerText = myName;
    addCommentList.innerText = commentInput.value;
    commentStart.appendChild(addCommentUnoderList);
    addCommentUnoderList.appendChild(boldNameWrap);
    addCommentUnoderList.appendChild(addCommentList);
    
    boldNameWrap.style.marginRight = "5px";
    addCommentUnoderList.style.display ="flex";
    return commentInput.value = "";
}

function redHeartColorChange(){
    heartColor.style.display = "inline-block";
    heart.style.display = "none";
}

function blackHeartColorChange(){
    heart.style.display = "inline-block";
    heartColor.style.display = "none";
}

function changeButtonColor(value){
    if(value.length !== 1){
        commetBtn.style.color = "#0095f6";
    }
    if(value.code === "Enter"){
        return commetBtn.style.color ="#BFE0FD";
    }
}
function buttonColorReset(){
    return commetBtn.style.color ="#BFE0FD";
}
        
function removeComment(){
    let removeText = document.querySelector('.commentStart ul');
    return removeText.remove();
}

function commentHeartChangeRed(){
    commentHeart.style.display = "none";
    commentHeartColor.style.display = "flex";
}

function commentHeartChangeBlack(){
    commentHeart.style.display = "flex";
    commentHeartColor.style.display = "none";
}



commetBtn.addEventListener('click',addCommentListBtn);
commetBtn.addEventListener('click',buttonColorReset);
commentInput.addEventListener('keydown',changeButtonColor);
trashBtn.addEventListener('click', removeComment);
commentHeart.addEventListener('click', commentHeartChangeRed);
commentHeartColor.addEventListener('click', commentHeartChangeBlack);
