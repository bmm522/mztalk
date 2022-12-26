window.onload = function(){
    console.log('실행');
    document.getElementById('card-header-div').innerHTML += '각 서비스 별 트래픽 수&nbsp&nbsp<small style="font-size: 1px;">&nbsp&nbsp오늘날짜 : '+nowDay+'&nbsp&nbsp(10초마다 업데이트 됩니다) </small> <input id="input-date"  value="'+nowDay+'" type="date"  style="width: 250px; margin-left:200px ; border-radius: 10px; border: 0.1px solid gainsboro;"/><input type="hidden" id="hidden-time"/>';
    
     
  
    getDailyTraffic();
  
    document.getElementById('input-date').onchange = function(){
      
       getDailyServiceTraffic();
      
    }
  
    getDailyServiceTraffic();
    setInterval(getDailyTraffic, 10000);
    setInterval(getDailyServiceTraffic, 10000);
    getMentor();
    getReportList();
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
                <td><div id="${userNo}"><button style="cursor:pointer;"  type="button" id="reg-btn" onclick="viewDocuments(${userNo});" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">look</button></div></td>
            </tr>`;
            } else{
                document.getElementById('table-body').innerHTML += `<tr>
                <td>${name}</td>
                <td>${currentJob}</td>
                <td>${phone}</td>
                <td>${email}</td>
                <td>${createDate}</td>
                <td>${authStatus}</td>
                <td></td>
            </tr>`;
            }
          
        }
    })
}




const viewDocuments = (userNo) =>{

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
                      
     document.getElementById("table-div").innerHTML += ` </table>

    </div>
</div>
<div class="modal-footer" style="margin: auto;">
    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
    <button type="button" class="btn btn-primary" onclick="admit(${userNo})">승인</button>
</div>`;
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
        document.getElementById(userNo).remove();
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

            document.getElementById('table-body2').innerHTML += `<tr>
                <td>${reportTitle}</td>
                <td>${reportContent}</td>
                <td>${boardId}</td>
                <td>${serviceName}</td>
                <td>${userId}</td>
                <td><div><button style="cursor:pointer;"  type="button" id="reg-btn"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">look</button></div></td>
            </tr>`;
        }
    })
}