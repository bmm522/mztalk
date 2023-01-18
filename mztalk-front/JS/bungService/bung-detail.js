let writerId = 1;
window.onload= function(){
    


    console.log(localStorage.getItem('bId'));
    fetch('http://localhost:8000/bung/mainBoardSelect/'+localStorage.getItem('bId'),{
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res)=>res.json())
    .then(res=>{
        let boardId = res.boardId;
        let title = res.title;
        let writer = res.writer;
        writerId = res.boardWriterId;
        let createDate = res.createDate;
        let imageInfo = res.imageInfo;
        let content = res.content;
        let fullGroup = res.fullGroup;
        let category = res.category;
        let deadlineDate = res.deadlineDate;
        let address = res.address;


        document.getElementById('bungTitle').value = title;
        document.getElementById('nameDiv').value = writer;
        document.getElementById('dateDiv').value= createDate;
        document.getElementById('bungContent').placeholder = content;
        document.getElementById('categoryInput').value = "카테고리 : " + category;
        document.getElementById('closeReadInput').value = "마감 날짜 : " + deadlineDate;
        document.getElementById('addressInput').value = "모임 장소 : " + address;

        localStorage.setItem('title', title);
        localStorage.setItem('writer', writer);
        localStorage.setItem('date', createDate);
        localStorage.setItem('bungContent', content);
        localStorage.setItem('category', category);
        localStorage.setItem('closeRead', deadlineDate);
        localStorage.setItem('fullGroup', fullGroup);
        localStorage.setItem('address', address);
        localStorage.setItem("imageInfo", JSON.stringify(imageInfo));

        for(let i = 0 ; i < imageInfo.length ;  i++){
            console.log(imageInfo[i]);
            if(i == 0){
                document.getElementById('image-div-div').innerHTML +=  ' <div class="carousel-item active"><img class="d-block" style="width: 654.5px; height: 400px; object-fit: cover;" src="'+imageInfo[i].imageUrl+'" alt="사진이 없습니다" /></div>';
            } else {
                document.getElementById('image-div-div').innerHTML +=  ' <div class="carousel-item"><img class="d-block" style="width: 654.5px; height: 400px; object-fit: cover;" src="'+imageInfo[i].imageUrl+'" alt="사진이 없습니다" /></div>';
            }
           
           
        }

        // for(let i = 0 ; i<imageInfo.length ; i++){
        //     document.getElementById('dot-div').innerHTML += '<span class="dot"></span>';
        // }

        getNowGroup(fullGroup);
        
        if(localStorage.getItem('writer') != localStorage.getItem('userNickname')) {
            document.getElementById('boardUpdate').innerHTML = "";
        }
    
        if(localStorage.getItem('writer') != localStorage.getItem('userNickname')) {
            document.getElementById('deleteBtn').innerHTML = "";
        }

        if(localStorage.getItem('writer') == localStorage.getItem('userNickname')) {
            document.getElementById('report').innerHTML = "";
        }

        if(localStorage.getItem('writer') == localStorage.getItem('userNickname')) {
            document.getElementById('question').innerHTML = "";
        }
    
        console.log("글쓴이 : " +localStorage.getItem('writer'));
        console.log("접속 유저 닉네임 : " + localStorage.getItem('userNickname'));
    
        if(localStorage.getItem('writer') == localStorage.getItem('userNickname')) {
            document.getElementById('addBtn').innerHTML = "";
        }
        
    
    })

    

}
const getNowGroup = (fullGroup) =>{
    fetch("http://localhost:8000/bung/bungBoardNowGroup/"+localStorage.getItem('bId'),{
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res)=>res.json())
    .then(res=>{
        let nowGroup = res;
        document.getElementById('groupInput').value="현재 인원 : " + nowGroup+" 명 / 총 인원 : "+fullGroup+" 명";
        localStorage.setItem('group', group);
    })
}
const deleteEvent = () =>{

    if(confirm("정말 삭제하시겠습니까?")) {
        fetch("http://localhost:8000/bung/mainBoardDelete/"+localStorage.getItem('bId'), {
            method:"PATCH",
            headers:{
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            }
        })
        .then(res => {
            deleteAllImage();
        })
    }

   
}


const deleteAllImage = () =>{
    fetch("http://localhost:8000/resource/images?bNo="+localStorage.getItem('bId')+"&serviceName=bung",{
        method:"DELETE",
        headers:{
            "Content-Type" : "text/html"
        }
    })
    .then(res=>{
        alert('삭제되었습니다.');
        location.href="bung-service-main.html";
    })
}

const updateMove = () =>{
    location.href="bung-Service-edit.html";
}

document.getElementById('report-btn').addEventListener('click', function(){
    const bId = localStorage.getItem('bId');
    fetch("http://localhost:8000/login/report",{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
            },
            body:JSON.stringify({
                reportTitle : document.getElementById('recipient-name').value,
                reportContent : document.getElementById('message-text').value,
                boardId : bId,
                serviceName : "bung",
                userNo : localStorage.getItem('userNo'),                   
                })
            })
            .then(res =>{
                alert('신고 접수 되었습니다.');
                location.href="bung-service-detail.html";
            })
} );

const chatOpen = () =>{
    console.log('writer : ' + document.getElementById('nameDiv').value);
    console.log('localstorage : ' + localStorage.getItem('userNickname'));
    
    fetch('http://localhost:8000/login/chat/front-nickname', {
        method:"POST",
        headers:{
            "Content-Type" : "application/json"
        },
        body:JSON.stringify({
            "serviceName" : "bung",
            "fromUserNickname" : document.getElementById('nameDiv').value,
            "toUserNickname" :  localStorage.getItem('userNickname'),
            
        })
    })
    .then(res=>res.json())
    .then(res=>{
        if(res.result == 0){
            alert('이미 문의가 완료된 요청입니다.');
        } else {
        alert('문의가 완료되었습니다.');
        window.open(`http://localhost:3000/chat-bung/?userId=${localStorage.getItem('userNo')}&userNickname=${localStorage.getItem('userNickname')}`)
        // location.href = 'bung-service-detail.html';
        }
    })
}

// document.getElementById('addBtn').addEventListener('click',function(){
//     fetch('http://localhost:8000/bung/bungAddBoardRequest/'+localStorage.getItem('bId'),{
//             method:"GET",
//             headers:{
//                 Authorization:localStorage.getItem('authorization'),
//                 RefreshToken:localStorage.getItem('refreshToken'),
//             }
//         })
//         .then((res)=>res.json())
//         .then(res=>{
//             console.log(res.bId);
//             localStorage.setItem("bId", res.bId);
//             location.href="bung-service-request.html";
//         })
// });

console.log("이거나옴?" + localStorage.getItem('bId'));
const boardId = localStorage.getItem('bId');

document.getElementById('addBtn').addEventListener('click',function(boardId){
    console.log(localStorage.getItem('bId'));
    location.href="bung-service-add.html";
});

document.getElementById('bung-modal-btn').addEventListener('click', function(){
    fetch('http://localhost:8000/bung/bungRequestList/'+localStorage.getItem('bId'),{
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then(res=>res.json())
    .then(res=>{
        for(let add of res.data){
            document.getElementById('modal-div-div').innerHTML += `<div class="groupUser" style="height: 50px; float: left; width: 70%; font-size: x-large;">
            ${add.addNickName}
        </div>`;

        if(add.addNickName == add.bungBoard.boardWriter){

        } else if(add.bungBoard.boardWriter != localStorage.getItem('userNickname')){

        } else {

            document.getElementById('modal-div-div').innerHTML +=   `
        <div class="groupUserDrop" style="height: 50px; float: left; width: 30%;">
            <button type="button" class="btn btn-outline-danger btn-sm" id="drop-btn" style="width: 35px; height: 35px; font-size: small;" onclick="deleteAdd(${add.addId});">X</button>
        </div>`;
        }
        
        }
    })
    document.getElementById('modal-div-div').innerHTML = '';
})

const deleteAdd = (addId) => {
    fetch('http://localhost:8000/bung/bungAddBoardGroupDrop/'+localStorage.getItem('bId')+'/'+addId,{
        method:"DELETE",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then(res=>{
        alert('추방되었습니다.');
        location.href="bung-service-detail.html";
    })
}

// const deleteSelfAdd = (addId) => {
//     fetch('http://localhost:8000/bung/bungAddBoardGroupSelfDrop/',{
//         method:"DELETE",
//         headers:{
//             "Content-Type":"application/json",
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         },
//         body:JSON.stringify({
//             boardId : localStorage.getItem('bId'),
//             userNo : localStorage.getItem('userNo'),
//             addId : addId
//         })
//     })
//     .then(res=>{
//         alert('탈퇴되었습니다.');
//         location.href="bung-service-detail.html";
//     })
// }

document.getElementById('writer').addEventListener('click', function(){
    console.log('writerId : ' + writerId);
    moveBungToStory(writerId);
})

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

        location.href="auction.html";

    })

});
