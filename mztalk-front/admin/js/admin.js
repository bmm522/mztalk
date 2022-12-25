window.onload = function(){
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
            let name = application.name;
                let currentJob = application.job;
                let phone = application.phone;
                let email = application.email;
                let createDate = application.createdDate.substr(0,9);
                let authStatus = application.authStatus;
            if(authStatus.includes('NO')){              
                document.getElementById('table-body').innerHTML += `<tr>
                <td>${name}</td>
                <td>${currentJob}</td>
                <td>${phone}</td>
                <td>${email}</td>
                <td>${createDate}</td>
                <td>${authStatus}</td>
                <td><button style="cursor:pointer;"  type="button" id="reg-btn">신청받기</button></td>
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