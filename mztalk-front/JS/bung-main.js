window.onload = function(){
    console.log(localStorage.getItem('authorization'));
    console.log(localStorage.getItem('refreshToken'));
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
                let fullGroup = board.fullGroup;
                let deadlineDate = board.deadlineDate;
                let createdDate = board.createdDate;
                let boardId = board.boardId;

                document.getElementById('output-div').innerHTML +=
                ` <div class="col" onclick='moveDetails(${boardId});' style="cursor:pointer;"><br>
                <div class="card h-100">            
                     <div class="card shadow-sm h-100">
                         <div class="card-header">
                            ${title}
                         </div>                      
                         <img src="${imageUrl}" style="height: 250px;">
                         <input type="hidden" name="imageName" value="${imageName}"/>
                         <div class="card-body">
                             <h5 class="card-title">${category}</h5>
                             <p class="card-text">${content}</p>
                         </div>
                         <hr>
                         <div id="count">${count}</div>
                         <div id="writer">마감일 : ${deadlineDate}</div>
                         <div id="writer">
                             ${writer}
                         </div>
                         <div id="groupTotal">
                            ${fullGroup}
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
             fullGroup = '';
              deadlineDate ='';
            }
        }
        // getNowGroup(fullGroup);
    })
}
// const getNowGroup = (fullGroup) =>{
//     fetch("http://localhost:8000/bung/bungBoardNowGroup/"+localStorage.getItem('bId'),{
//         method:"GET",
//         headers:{
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         }
//     })
//     .then((res)=>res.json())
//     .then(res=>{
//         let nowGroup = res;
//         document.getElementById('groupTotal').innerHTML=nowGroup+" / "+fullGroup;
//         localStorage.setItem('group', group);
//     })
// }
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
        location.href="bung-service-writer.html";
    })
});


const moveDetails = (boardId) =>{
    localStorage.setItem("bId", boardId);
    location.href="bung-service-detail.html";
}

// document.getElementById('searchBtn').addEventListener('click', function(){
//     let category = document.getElementById('type').value;
//     fetch('http://localhost:8000/bung/bungBoardSearch?'+ category + "=" +document.getElementById('searchBox').value,{
//         method: "GET",
//         headers: {
//             Authorization:localStorage.getItem('authorization'),
//             RefreshToken:localStorage.getItem('refreshToken'),
//         }
//     })
//     .then((res)=>res.json())
//     .then(res=>{
//         console.log("들어와?");
//         console.log("검색결과response: " + res);
//         for(let board of res.data){
//             let imageUrl = board.imageUrl;
//             let imageName = board.imageName;
//             let count = board.count;
//             let title = board.title;
//             let category = board.category;
//             let content = board.content;
//             let writer = board.writer;
//             let fullGroup = board.fullGroup;
//             let deadlineDate = board.deadlineDate;
//             let createdDate = board.createdDate;
//             let boardId = board.boardId;

//             document.getElementById('output-div').innerHTML +=
//             ` <div class="col" onclick='moveDetails(${boardId});' style="cursor:pointer;"><br>
//             <div class="card h-100">            
//                  <div class="card shadow-sm h-100">
//                      <div class="card-header">
//                         ${title}
//                      </div>                      
//                      <img src="${imageUrl}" style="height: 250px;">
//                      <input type="hidden" name="imageName" value="${imageName}"/>
//                      <div class="card-body">
//                          <h5 class="card-title">${category}</h5>
//                          <p class="card-text">${content}</p>
//                      </div>
//                      <hr>
//                      <div id="count">${count}</div>
//                      <div id="writer">마감일 : ${deadlineDate}</div>
//                      <div id="writer">
//                          ${writer}
//                      </div>
//                      <div id="groupTotal">
//                         ${fullGroup}
//                      </div>
//                  </div>
//              </div> 
//          </div>`

//           imageUrl = '';
//          imageName = '';
//          count = '';
//          title = '';
//           category = '';
//          content = '';
//           writer = '';
//          fullGroup = '';
//           deadlineDate ='';
//         }
//     })
// });

document.getElementById('searchBtn').addEventListener('click', function() {
    const checkedBoxes = ['study', 'hobby', 'enjoy', 'food'].filter(id => document.getElementById(id).checked);
    const arr = checkedBoxes.length ? checkedBoxes : ['study', 'hobby', 'enjoy', 'food'];
   
    let type = document.getElementById('type').value;

    if(type == 'null'){
        alert('제목, 내용, 닉네임을 지정해주세요');
    } else {
        fetch('http://localhost:8000/bung/search?category='+ arr.join(',')+'&type='+type+'&searchText='+document.getElementById('searchBox').value, {
            method:"GET",
            headers:{
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            }
        })
        .then(res=>res.json())
        .then(res=>{
            document.getElementById('output-div').innerHTML = '';
            for(let board of res.data){
                let imageUrl = board.imageUrl;
                let imageName = board.imageName;
                let count = board.count;
                let title = board.title;
                let category = board.category;
                let content = board.content;
                let writer = board.writer;
                let fullGroup = board.fullGroup;
                let deadlineDate = board.deadlineDate;
                let createdDate = board.createdDate;
                let boardId = board.boardId;

                document.getElementById('output-div').innerHTML +=
                ` <div class="col" onclick='moveDetails(${boardId});' style="cursor:pointer;"><br>
                <div class="card h-100">            
                     <div class="card shadow-sm h-100">
                         <div class="card-header">
                            ${title}
                         </div>                      
                         <img src="${imageUrl}" style="height: 250px;">
                         <input type="hidden" name="imageName" value="${imageName}"/>
                         <div class="card-body">
                             <h5 class="card-title">${category}</h5>
                             <p class="card-text">${content}</p>
                         </div>
                         <hr>
                         <div id="count">${count}</div>
                         <div id="writer">마감일 : ${deadlineDate}</div>
                         <div id="writer">
                             ${writer}
                         </div>
                         <div id="groupTotal">
                            ${fullGroup}
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
             fullGroup = '';
              deadlineDate ='';
            }
        })
    }

    
  });