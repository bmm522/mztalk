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

// console.log(localStorage.getItem('userNickname'));
// console.log(localStorage.getItem('userNo')); // 세션값
// console.log(localStorage.getItem('own')); //파라미터 

window.onload=function(){
    console.log("개인 : " + localStorage.getItem('authorization'));
    console.log("개인 : " + localStorage.getItem('refreshToken'));
    console.log("개인 : " + localStorage.getItem('userNo'));
    console.log("개인 : " + localStorage.getItem('userNickname'));
    console.log("페이지주인: " + localStorage.getItem('own'));

    const own = document.getElementById('#own');
    
}

//개인페이지
document.getElementById('profile-edit-btn').addEventListener('click',function(){
    
    location.href="editpage.html";

    // fetch("http://localhost:8000/story/"+own,{
    //     method:"GET",
    //     headers:{
    //         "Content-Type":"application/json",
    //         Authorization:localStorage.getItem('authorization'),
    //         RefreshToken:localStorage.getItem('refreshToken'),
    //     },
    // })
    // .then((res)=> res.json())
    // .then(res=>{
    //     console.log("자료있니?" +res.data);
    //     console.log("!!!!!!!!!!");
    //     console.log(own);
    //     location.href="individualpage.html";
    // })


});





const write_board = document.getElementById('write-board');
const privacyBounds = document.getElementById('privacyBounds');
//글쓰기
write_board.addEventListener('click', function(){
    console.log("클릭됨??");
    
    if(privacyBounds.options[privacyBounds.selectedIndex].value === 'no'){
        alert("공개범위를 설정하세요");
    }else{

        fetch("http://localhost:8000/story/saveForm",{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            },
            body:JSON.stringify({
                nickname: localStorage.getItem('userNickname'),
                title: document.getElementById('title-input-text').value,
                content: document.getElementById("content-input-text").value,
                own: localStorage.getItem('own'),
                privacy: privacyBounds.options[privacyBounds.selectedIndex].value
            })
        })
            .then((res)=>res.json())
            .then(res =>{
                console.log("res : " + res);
                if(res > 0){
                    console.log('통신성공');
                   
                } else {
                    console.log('실패');
                }
            })
        
    }        
});






















//결국엔 페이지 주인은 own
//접속자도 userNo

//접속자의 own으로 가는거니까




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