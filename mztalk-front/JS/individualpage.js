function writeboard() {
    
    const open = document.querySelector(".write_board");
    const modal = document.querySelector(".modal");
    const close = document.querySelector(".btn-close");

    //console.log(open);

    open.addEventListener("click", function(){
        //console.log(open);
        
        modal.classList.remove("hidden");
        open.classList.add("hidden");

    });
    close.addEventListener("click", function(){
        modal.classList.add("hidden");
        open.classList.remove("hidden");
    });
}
writeboard();




console.log(localStorage.getItem('userNickname'));
console.log(localStorage.getItem('userNo'));
console.log(localStorage.getItem('own'));






// function replyList(){
//    console.log('실행됨');
//     for(let i = 0 ; i < 10 ; i++){
//         document.getElementById('reply-div').innerHTML += "<div> <div id='reply-nickname'>{작성자 닉네임}</div> <div id='reply-content'>{댓글 내용}</div><div id='reply-date'>{date}</div> <div id='reply-edit-btn'><button style='cursor:pointer;' type='button'>X</button></div></div>";



//         // const reply = document.createElement("div");
//         // reply.id='reply-nickname';
//         // reply.innerHTML = "작성자";
//         // const reply2 = document.createElement("div");
//         // reply.id='reply-content';
//         // reply.innerHTML = "댓글내용";
//         // const reply3 = document.createElement("div");
//         // reply3.id='reply-date';
//         // reply.innerHTML = "date";
//         // // const reply4 = document.createElement("div").attributes('reply-edit-btn');
//         // // reply.innerHTML = "X";
        


//         // document.getElementById('reply-div').appendChild(reply);
//         // document.getElementById('reply-div').appendChild(reply2);
//         // document.getElementById('reply-div').appendChild(reply3);
//         // document.getElementById('reply-div').appendChild(reply4);
//     }
// }