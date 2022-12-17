window.onload = function(){
    getBoardList();
}

const getAccessToken = () =>{
    localStorage.removeItem('authorization');
    let refreshToken = localStorage.getItem('refreshToken');
    fetch("http://localhost:8000/login/access-token?refreshToken="+refreshToken,{
        method:"GET",            
    })
    .then((res)=>res.json())
    .then(res =>{
        localStorage.removeItem('refreshtoken');
        localStorage.setItem('authorization',res.jwtToken);
        localStorage.setItem('refreshToken', res.refreshToken);
     })
}

// 멘토 등록 신청서 작성 이미존재할 경우 return false 강제로 작성하면 서버측에서 Exception발생
document.getElementById('sendResume').addEventListener('click', function(){
    const userId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/application?userId="+userId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })    
    .then((res)=>res.json())
    .then(res =>{
        if(res){
            window.alert('이미 신청한 지원서가 존재합니다.');
            location.href="mentee-mypage.html";
            return false;
        } else {
            fetch("http://localhost:8000/mentors/application",{
            method:"POST",
            headers:{
                "Content-Type":"application/json;",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
             },
            body:JSON.stringify({
                name :document.getElementById("name").value,
                phone : document.getElementById("phone").value,
                email : document.getElementById("email").value,
                job : document.getElementById("job").value,
                bank : document.getElementById("bank").value,
                account : document.getElementById("account").value,
                userId : localStorage.getItem('userNo')
            })
        })    
        .then((res)=>res.json())
        .then(res =>{
            if(res > 0){
                window.alert('멘토 신청 완료');
                location.href="mentee-mypage.html";
            } else {
                window.alert('멘토 신청 실패');
                return false;
            }
    })
        }
    })
});


// 리뷰 제출하기
const sendReview = () => {
    document.getElementById('writeReview').addEventListener('click', function(){
        fetch("http://localhost:8000/mentors/score",{
            method:"POST",
            headers:{
                "Content-Type":"application/json;",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
            },
            body:JSON.stringify({
                boardId :document.getElementById("boardId").value,
                count : document.getElementById("count").value,
                content : document.getElementById("content").value,
                userId : localStorage.getItem('userNo')
            })
        })    
        .then((res)=>res.json())
        .then(res =>{
            console.log(res);
            if(res > 0){
                window.alert('리뷰 작성 완료');
                location.href="mentee-mypage.html";
            } else {
                window.alert('리뷰 작성 실패');
            }
        })
    });
}

// 메인페이지 글 뿌려주기
const getBoardList = () =>{
    fetch("http://localhost:8000/mentors/boards",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },  
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res.status==401){
            getAccessToken(); 
            location.href = "mentor-main.html";
        } else {
            let cnt = 1;
            document.getElementById('board-list-div').innerHTML += '<div class="row" style="padding:20px;" id="row-div">';
            for(let board of res.data){
                let boardId = board.id;
                let category = board.category;
                let nickname = board.nickname;
                let career = board.career;
                let title = board.title;
            if(cnt%4 !== 0 ){
                document.getElementById('row-div').innerHTML +=  `<div class="col-3">
                <div class="card" style="width: 13rem; height:14rem;">
                <div class="card-body" onclick="getBoardDetail(${boardId});"  
                data-bs-toggle="modal" href="#exampleModalToggle">
                <h5 class="card-title">${category}</h5><h6 class="card-subtitle mb-2 text-muted">
                ${nickname}</h6><h6 class="card-subtitle mb-2 text-muted">
                ${career}</h6><p class="card-text">제목:${title}</p>
                </div><input class="hidden-board-id" id=${boardId} type="hidden" value=board.id><button class="btn btn-outline-success" onclick="watchReview('${nickname}');" 
                type="button" data-bs-toggle="modal" data-bs-target="#showReview">평점보기</button></div></div>`;
                cnt += 1;
                participate(boardId);  
            } else {
                document.getElementById('row-div').innerHTML +=  
                `<div class="col-3">
                <div class="card" style="width: 13rem; height:14rem;">
                <div class="card-body" onclick="getBoardDetail(${boardId});"
                data-bs-toggle="modal" href="#exampleModalToggle">
                <h5 class="card-title">${category}</h5><h6 class="card-subtitle mb-2 text-muted">
                ${nickname}</h6><h6 class="card-subtitle mb-2 text-muted">
                ${career}</h6><p class="card-text">제목:${title}</p>
                </div><input class="hidden-board-id" id=${boardId} type="hidden" value='+board.id+'><button class="btn btn-outline-success" onclick="watchReview('${nickname}');"
                 type="button" data-bs-toggle="modal" data-bs-target="#showReview">평점보기</button></div></div>
                 </div><div class="row" style="padding:20px;" id="row-div">`;
                cnt += 1;
                participate(boardId);  
            }
      }
        }        
    })
}

const getBoardDetail = (bId) =>{
    console.log("http://localhost:8000/mentors/board/"+bId);
    fetch("http://localhost:8000/mentors/board/"+bId,{
        method:"GET",
        headers:{
        "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        console.log("res : " + res);
        if(res != null){
            document.getElementById('modal-body').innerHTML = res.content;
            document.getElementById('modal-salary').innerHTML = res.salary;
        } else {
            console.log('실패');
        }
    })
}


//글 작성자 닉네임을 이용해서 멘토에 대한 모든 리뷰 가져오기.
const watchReview = (nickname) =>{
    fetch("http://localhost:8000/mentors/score?nickname="+nickname,{
        method:"GET",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        if(res != null){
            let star ='';
            for(const score of res.data){
                switch(score.count){
                    case 5 : star ='★★★★★'; break;
                    case 4 : star ='★★★★'; break; 
                    case 3 : star ='★★★'; break; 
                    case 2 : star ='★★'; break; 
                    case 1 : star ='★'; break; 
                }
                document.getElementById('reviewBody').innerHTML +=  '<br/>' + star + '<br/>' + '<br/>' + score.content + '<br/>';
            }
        } else {
            console.log('실패');
        }
    })
    document.getElementById('reviewBody').innerHTML ='';
}

//참가 신청
const participate = (bId) =>{
    document.getElementById('participant-btn').addEventListener('click', function(){
        fetch("http://localhost:8000/mentors/participant",{
            method:"POST",
            headers:{
                "Content-Type":"application/json;",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
            },
            body:JSON.stringify({
                userId : localStorage.getItem('userNo'),
                boardId : bId,
                name :document.getElementById("name").value,
                phone : document.getElementById("phone").value,
                email : document.getElementById("email").value,
                message : document.getElementById("message").value
            })
        })
        .then((res)=>res.json())
        .then(res =>{
            console.log("res : " + res);
            if(res > 0){
                console.log('통신성공');
                location.href="mentor-main.html";
            } else {
                console.log('실패');
            }
        })
    });
}


// 완료된 멘토링 목록
const endMentoring = ()=>{
    document.getElementById('completedMentoring').addEventListener('click',function(){
        const userId = localStorage.getItem('userNo');
        fetch("http://localhost:8000/mentors/board?userId="+userId,{
            method:"GET",
            headers:{
                "Content-Type":"application/json;",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
            },
        })
        .then((res)=>res.json())
        .then(res =>{
            console.log("res : " + res);
            if(res!=null){
                for(const endBoard of res.data){
                    document.getElementById('endBoardList').innerHTML +=
                    `<td>${endBoard.nickname}</td>
                    <td>${endBoard.title}</td>
                    <td style="text-align: center;">
                        <button class="btn btn-outline-success" type="button" data-bs-toggle="modal" href="#writeReview"
                        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">리뷰쓰기</button>
                    </td>`
                }
            } else {
                console.log('완료된 멘토링 목록 가져오기 실패');
                for(const endBoard of res.data){
                    document.getElementById('endBoardList').innerHTML +=
                    `<td>${endBoard.nickname}</td>
                    <td>${endBoard.title}</td>
                    <td style="text-align: center;">
                        <button class="btn btn-outline-success" type="button" data-bs-toggle="modal" href="#writeReview"
                        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">리뷰쓰기</button>
                    </td>`
                }
            }
        })
    });
}

// 신청한 멘토링 목록
const allMentoring = ()=>{
    document.getElementById('showAllMentoring').addEventListener('click',function(){
        const userId = localStorage.getItem('userNo');
        fetch("http://localhost:8000/mentors/board?userId="+userId,{
            method:"GET",
            headers:{
                "Content-Type":"application/json;",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken')
            },
        })
        .then((res)=>res.json())
        .then(res =>{
            console.log("res : " + res);
            if(res!=null){
                for(const allBoard of res.data){
                    document.getElementById('mentoringRow').innerHTML +=
                    `<th scope="row">${allBoard.id}</th>
                    <td>${allBoard.nickname}</td>
                    <td>${allBoard.title}</td>
                    <td style="text-align: center;">${allBoard.status}</td>`
                }
            } else {
                console.log('완료된 멘토링 목록 가져오기 실패');
                for(const endBoard of res.data){
                    document.getElementById('mentoringRow').innerHTML +=
                    `<th scope="row">${allBoard.id}</th>
                    <td>${allBoard.nickname}</td>
                    <td>${allBoard.title}</td>
                    <td style="text-align: center;">${allBoard.status}</td>`
                }
            }
        })
    });
}

//마이 페이지 이동, 권한 확인 후 true면 멘토 > 멘토페이지 false면 멘티 > 멘티페이지
document.getElementById('myPage').addEventListener('click', function(){
    const userId = localStorage.getItem('userNo');
    fetch("http://localhost:8000/mentors/member?userId="+userId,{
        method:"GET",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
    })
    .then((res)=>res.json())
    .then(res =>{
        console.log("res : " + res);
        if(res){
            location.href="mentor-mypage.html";
        } else {
            location.href="mentee-mypage.html";
        }
    })
});

//멘토서비스보내기
document.getElementById('move-mentor-service').addEventListener('click',function(){
    location.href="mentor-main.html";   
});