
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
        let createDate = res.createDate;
        let imageInfo = res.imageInfo;
        let content = res.content;
        let fullGroup = res.fullGroup;
        let category = res.category;
        let deadlineDate = res.deadlineDate;
        let address = res.address;

        console.log(JSON.stringify(imageInfo));

        document.getElementById('header').innerHTML = '<strong>'+title+'</strong>';
        document.getElementById('writer').innerHTML = writer;
        document.getElementById('date').innerHTML=createDate;
        document.getElementById('bungContent').placeholder = content;
        document.getElementById('category').innerHTML = "카테고리 : " + category;
        document.getElementById('closeRead').innerHTML = "마감 날짜 : " + deadlineDate;
        document.getElementById('address').innerHTML = "모임 장소 : " + address;

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
                document.getElementById('image-div-div').innerHTML +=  ' <div class="carousel-item active"><img class="d-block w-100" src="'+imageInfo[i].imageUrl+'" alt="사진이 없습니다" /></div>';
            } else {
                document.getElementById('image-div-div').innerHTML +=  ' <div class="carousel-item"><img class="d-block w-100" src="'+imageInfo[i].imageUrl+'" alt="사진이 없습니다" /></div>';
            }
           
           
        }

        // for(let i = 0 ; i<imageInfo.length ; i++){
        //     document.getElementById('dot-div').innerHTML += '<span class="dot"></span>';
        // }

        getNowGroup(fullGroup);
    console.log("사이즈 : "  + imageInfo.length);
        
    
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
        document.getElementById('group').innerHTML="현재 인원 : " + nowGroup+" 명 / 총 인원 : "+fullGroup+" 명";
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
    console.log('title : ' +document.getElementById('recipient-name').value);
    console.log('content : ' + document.getElementById('message-text').value);
    console.log('bId : ' + bId);
    console.log('userNo : ' + localStorage.getItem('userNo'));
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
    
    fetch('http://localhost:8000/login/chat/front-nickname', {
        method:"POST",
        headers:{
            "Content-Type" : "application/json"
        },
        body:JSON.stringify({
            "serviceName" : "bung",
            "fromUserNickname" :document.getElementById('writer').innerHTML,
            "toUserNickname" :  localStorage.getItem('userNickname'),
            
        })
    })
    .then(res=>res.json())
    .then(res=>{
        if(res.result == 0){
            alert('이미 문의가 완료된 요청입니다.');
        } else {
        alert('문의가 완료되었습니다.');
        location.href = 'bung-service-detail.html';
        }
    })
}

document.getElementById('addBtn').addEventListener('click',function(){
    fetch('http://localhost:8000/bung/bungAddBoard/'+localStorage.getItem('bId'),{
            method:"GET",
            headers:{
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            }
        })
        .then((res)=>res.json())
        .then(res=>{
            console.log(res.bId);
            localStorage.setItem("bId", res.bId);
            location.href="bung-service-request.html";
        })
    });

