window.onload = function(){
     localStorage.removeItem('bId');
    
    fetch('http://localhost:8000/bung/mainBoards', {
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res)=>res.json())
    .then(res =>{
        
        if(res.status==401){

        } else {
            for(let board of res.data){
                let imageUrl = board.imageUrl;
                let imageName = board.imageName;
                let count = board.count;
                let title = board.title;
                let category = board.category;
                let content = board.content;
                let writer = board.writer;
                let nowGroup = board.nowGroup;
                let fullGroup = board.fullGroup;
                let deadlineDate = board.deadlineDate;
                let boardId = board.boardId;

                document.getElementById('output-div').innerHTML +=
                ` <div class="col" onclick='moveDetails(${boardId});' style="cursor:pointer;">
                <div class="card h-100">
             
                     <div class="card shadow-sm h-100">
                         <div class="card-header">
                            ${title}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp조회수 : &nbsp${count}
                         </div>
                         
                         <img src="${imageUrl}" style="height: 250px;">
                         <input type="hidden" name="imageName" value="${imageName}"/>
                         <div class="card-body">
                             <h5 class="card-title">${category}</h5>
                             <p class="card-text">${content}</p>
                         </div>
                         <hr>
                         <div id="writer">마감일 : ${deadlineDate}</div>
                         <div id="writer">
                             ${writer}
                         </div>
                         <div id="groupTotal">
                             ${nowGroup} / ${fullGroup}
                         </div>
                     </div>
                 </div> 
             </div>`

              imageUrl = '';
             imageName = '';
             count = '';
             title = '';
              category = '';
             content = '';
              writer = '';
             nowGroup ='';
             fullGroup = '';
              deadlineDate ='';
            }
        }
    })
}
document.getElementById('write-btn').addEventListener('click',function(){

    fetch('http://localhost:8000/bung/recent-board', {
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
        location.href="bung-Service-writerPage.html";
    })
});


const moveDetails = (boardId) =>{
    localStorage.setItem("bId", boardId);
    location.href="bung-service-detailPage.html";
}