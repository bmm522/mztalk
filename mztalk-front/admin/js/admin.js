// const { computeStyles } = require("@popperjs/core");

window.onload = function(){
    console.log('실행');
    console.log(nowDay);
    document.getElementById('card-header-div').innerHTML += '각 서비스 별 트래픽 수&nbsp&nbsp<small style="font-size: 1px;">&nbsp&nbsp오늘날짜 : '+nowDay+'&nbsp&nbsp(10초마다 업데이트 됩니다) </small> <input id="input-date"  value="'+nowDay+'" type="date"  style="width: 250px; margin-left:200px; border-radius: 10px; border: 0.1px solid gainsboro;"/><input type="hidden" id="hidden-time"/>';
    
     
  
    getDailyTraffic();
  
    document.getElementById('input-date').onchange = function(){
      
       getDailyServiceTraffic();
      
    }
  
    getDailyServiceTraffic();
    setInterval(getDailyTraffic, 10000);
    setInterval(getDailyServiceTraffic, 10000);
    getMentor();
    getReportList();
    getMaliciousUserList();
}

const getMentor = () =>{
    fetch('http://localhost:8000/mentors/applications', {
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res) => res.json())
    .then(res =>{
        for(let application of res.data){
            
          //  console.log(application.mentor[0].status);
            let name = application.name;
                let currentJob = application.job;
                let phone = application.phone;
                let email = application.email;
                let createDate = application.createdDate.substr(0,10);
                let authStatus = application.authStatus;
                let userNo = application.mentee.id;

            if(authStatus.includes('NO')){              
                document.getElementById('table-body').innerHTML += `<tr>
                <td>${name}</td>
                <td>${currentJob}</td>
                <td>${phone}</td>
                <td>${email}</td>
                <td>${createDate}</td>
                <td>${authStatus}</td>
                <td><div id="${userNo}"><button style="cursor:pointer;"  type="button" id="reg-btn-no" onclick="viewDocuments(${userNo}, 'NO');" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">서류 보기</button></div></td>
            </tr>`;
            } else{
                document.getElementById('table-body').innerHTML += `<tr>
                <td>${name}</td>
                <td>${currentJob}</td>
                <td>${phone}</td>
                <td>${email}</td>
                <td>${createDate}</td>
                <td>${authStatus}</td>
                <td><div id="${userNo}"><button style="cursor:pointer;"  type="button" id="reg-btn-yes" onclick="viewDocuments(${userNo}, 'YES');" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">서류 보기</button></div></td>
            </tr>`;
            }
          
        }
    })
}




const viewDocuments = (userNo, authStatus) =>{
fetch('http://localhost:8000/resource/files?id='+userNo, {
    method:"GET",
    headers:{
        "Content-Type" : "text/html",
    }
})
.then((res)=>res.json())
.then(res=>{
    
document.getElementById('modal-div').innerHTML += `<div class="modal-body">
    <div>
        <div><h1>증명 서류</h1></div>
        <table ><div id="table-div"></div>`;
            
    for(let file of res.data){
        let fileUrl = file.fileUrl;
        let fileName = file.fileName;
        console.log(fileUrl);
        console.log(fileName);
        document.getElementById("table-div").innerHTML +=`<tr>
            <td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a style="text-decoration:none;" href="${fileUrl}" download="${fileName}">${fileName}</a></td><br>
        <tr>`
    }
   
    if(authStatus.includes('NO')){
     document.getElementById("table-div").innerHTML += ` </table>

            </div>
        </div>
        <div class="modal-footer" style="margin: auto;">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            <button type="button" class="btn btn-primary" onclick="admit(${userNo})">승인</button>
        </div>`;
    } else{
        document.getElementById("table-div").innerHTML += ` </table>

            </div>
        </div>
        <div class="modal-footer" style="margin: auto;">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        </div>`;
    }
})
document.getElementById('modal-div').innerHTML = '';

    
}



const admit = (userNo) =>{
    if(confirm('신청을 받으시겠습니까 ?')){
        fetch('http://localhost:8000/mentors/member', {
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            "userId" : userNo
        })
    })
    .then(res=>{
        alert('신청 받기 완료');
        // document.getElementById(userNo).remove();
        location.href="index.html";
    })
    }
    
    
}

const getReportList = () =>{
    fetch('http://localhost:8000/login/report',{
        method:"GET"
    })
    .then((res)=>res.json())
    .then(res=>{
        for(let report of res.data){
            let reportTitle = report.reportTitle;
            let reportContent = report.reportContent;
            let boardId = report.boardId;
            let serviceName = report.serviceName;
            let userId = report.user.userId;
            let reportStatus = report.reportStatus;
            let path = report.path;
            if(reportStatus.includes('Y')){

            document.getElementById('table-body2').innerHTML += `<tr>
                <td>${reportTitle}</td>
                <td>${reportContent}</td>
                <td>${boardId}</td>
                <td>${serviceName}</td>
                <td>${userId}</td>
                <td><div><button style="cursor:pointer;"  type="button" id="report-btn"  onclick="getBoardDetail(${boardId} , ${userId},'${serviceName}', '${path}');"  
                    data-bs-toggle="modal" href="#exampleModalToggle">해당 글 보기</button></div></td>
            </tr>`;
        }
    }
    })
}

const getBoardDetail = (bId, userId,serviceName, path) =>{
    if(serviceName == 'mentor'){
        console.log('멘토실행됨');
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
            if(res != null){
                document.getElementById('exampleModalToggleLabel').innerHTML = '멘토-멘티 서비스'
                document.getElementById('modal-body').innerHTML = "자기소개 : " + res.data.introduction + "<br/>";
                document.getElementById('modal-body').innerHTML += "글 내용 : " + res.data.content;
                document.getElementById('modal-salary').innerHTML = '1회 멘토링 : 1시간 / '+res.data.salary+'원';
                // document.getElementById('board-price').innerHTML = res.salary;
                document.getElementById('btn-div').innerHTML = ` <button type="button" id="rep-btn" onclick="postReport(${bId},${userId},'${serviceName}');">신고 받기</button>`;
            } else {
                console.log('실패');
            }
        })
        document.getElementById('modal-body').innerHTML = '';

    } else if(serviceName == 'auction'){
        fetch('http://localhost:8000/auction/board/' + bId +'/'+userId, {
            method: "GET",
            headers: {
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            }
        })
        .then((res)=>res.json())
        .then(res=>{
            if(res != null){
                document.getElementById('exampleModalToggleLabel').innerHTML = '책 중고거래 서비스'
                document.getElementById('modal-body').innerHTML = "제목 : " + res.title + "<br/>";
                document.getElementById('modal-body').innerHTML += "글 내용 : " + res.content;
                // document.getElementById('board-price').innerHTML = res.salary;
                document.getElementById('btn-div').innerHTML = ` <button type="button" id="detail-btn" onclick="moveDetail(${bId}, '${serviceName}', '${path}');">해당 글 상세보기</button>
                <button type="button" id="rep-btn" onclick="postReport(${bId},${userId},'${serviceName}');">신고 받기</button>
                
                `;
            } else {
                console.log('실패');
            }
        })
        document.getElementById('modal-body').innerHTML = '';
    } else if(serviceName == 'bung'){
        fetch('http://localhost:8000/bung/mainBoardSelect/'+bId, {
            method:"GET",
            headers:{
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            }
        })
        .then((res)=>res.json())
        .then(res=>{
            if(res != null){
                document.getElementById('exampleModalToggleLabel').innerHTML = '벙 서비스'
                document.getElementById('modal-body').innerHTML = "제목 : " + res.title + "<br/>";
                document.getElementById('modal-body').innerHTML += "글 내용 : " + res.content;
                // document.getElementById('board-price').innerHTML = res.salary;
                document.getElementById('btn-div').innerHTML = ` <button type="button" id="detail-btn" onclick="moveDetail(${bId}, '${serviceName}', '${path}');">해당 글 상세보기</button>
                <button type="button" id="rep-btn" onclick="postReport(${bId},${userId},'${serviceName}');">신고 받기</button>
                
                `;
            } else {
                console.log('실패');
            }
        })
    }
}

const postReport = (bId, userId,serviceName) =>{

    fetch('http://localhost:8000/login/report/?bId='+bId+'&userId='+userId+'&serviceName='+serviceName, {
        method:"PATCH",
    })
    .then(res=>{
        alert('신고처리 되었습니다.');
        location.href="index.html";
    })

}

const getMaliciousUserList = () =>{

    fetch('http://localhost:8000/login/malicious-user', {
        method:"GET",
    })
    .then((res) => res.json())
    .then(res=>{
        for(let user of res.data){
            let userNo = user.userNo;
            let username = user.username;
            let nickname = user.nickname;
            let createDate = user.createDate;
            let reportCount = user.reportCount;

            document.getElementById('table-body3').innerHTML += `<tr>
                <td>${userNo}</td>
                <td>${username}</td>
                <td>${nickname}</td>
                <td>${createDate}</td>
                <td>${reportCount}</td>
                <td><div><button style="cursor:pointer;"  type="button" id="out-btn"  onclick="outUser(${userNo});" >퇴출</button></div></td>
            </tr>`;
        
        }
    })
}

const outUser = (userNo) =>{

    fetch('http://localhost:8000/login/user/status?status=out&userNo='+userNo,{
        method:"PATCH"
    })
    .then(res=>{
        alert('해당 유저를 퇴출했습니다.');
        location.href="index.html";
    })
}

const moveDetail = (bId, serviceName, path) =>{
    console.log('admin : ' + bId);
    localStorage.setItem('bId', bId);
    location.href = path;
}