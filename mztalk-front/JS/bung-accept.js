window.onload = function(){
    fetch('http://localhost:8000/bung/accept?nickname='+localStorage.getItem('userNickname'), {
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then(res=>res.json())
    .then(res=>{
        for(let accept of res.data){
            document.getElementById('accept-div').innerHTML +=    
            ` <br><br>

            <div class="listArea" style="width: 935px;">
                <div class="requestList">
                    <div class="d-grid gap-2 col-6 requestUserBtn">
                        <button type="button" class="btn btn-warning" onclick="moveAcceptDetail(${accept.addId})">상세 보기</button>
                    </div>
                    <div class="requestUserId">
                        ${accept.addNickname}
                    </div>
                </div>
            </div>`;
        }
    })
}

const moveAcceptDetail = (addId) =>{
    
    localStorage.setItem('addId', addId);
    location.href="bung-service-request.html";
}