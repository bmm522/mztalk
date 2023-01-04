//입찰가 가격 제한
document.getElementById("priceSubmitBtn").addEventListener('click', function() {
    console.log(document.getElementById('startPrice').innerHTML);
    console.log(document.getElementById('inputPrice').value);
    if(Number(document.getElementById('startPrice').innerHTML) >= Number(document.getElementById('inputPrice').value)) {
        alert('현재 금액보다 큰 금액을 입력해 주세요');
    }
});

//댓글 작성
document.getElementById('commInsertBtn').addEventListener('click', function() {
    fetch("http://localhost:8000/auction/comment", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            "boardId" : document.getElementById("hidden-bId").value,
            "content" : document.getElementById("commInput").value,
            "writer": localStorage.getItem("userNickname")
        }),
    })
    .then((res) => res.json())
    .then(res => {
        document.getElementById('commInput').value = "";
        console.log("댓글 작성 시 response: " + JSON.stringify(res));
        console.log("댓글 작성 성공");
        let cId = res.cid;
        let content = res.content;
        let writer = res.writer;
        let createDate = res.createDate;
        //CommentResponseDto 반환받음
        document.getElementById('commentArea').innerHTML += `<div class="row" id = "comment${cId}Start"><div id = "commNickname" class = "col-3"><span class = "${cId}" id = "commNickname">${writer}</span></div><div id = "commContent" class = "col-5"><span id = "${cId}">${content}</span></div><div id = "commCreateDate" class = "col-2"><span class = "${cId}" style="color: gray; font-size: smaller;">${createDate}</span></div><div id = "comm${cId}Btn" class = "col-2"><span class = "${cId}" id = "commUpdate" style = "color: gray; font-size: small; margin-right: 20px;" onclick = "updateComment(${cId});">수정</span><span class = "${cId}" id = "commDelete" style = "color: gray; font-size: small;" onclick = "deleteComment(${cId});">삭제</span></div></div>`;
        if(writer != localStorage.getItem('userNickname')) {
            document.getElementById('comm' + cId + 'Btn').innerHTML = '';
        }
    })
});

//댓글 수정 폼으로 변경
function updateComment(cId) {
    console.log("updateComment cId: " + cId);
    document.getElementById(cId).innerHTML = "<input type = 'text' id = 'commUpdateForm' style = 'width: 250px; margin-right: 5px;'/> <span id = 'updateComplete' style='color:gray; font-size: small; margin-right: 5px; cursor: pointer;'>확인</span><span id = 'updateCancle' style='color:gray; font-size: small; cursor: pointer;'>취소</span>"
    document.getElementById('commUpdateForm').focus();
    document.getElementById('updateComplete').addEventListener('click', function() {
        // console.log("수정할 content내용: " + document.getElementById('commUpdate').value);
        fetch("http://localhost:8000/auction/comment/" + cId, {
        method: "PATCH",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            "content" : document.getElementById("commUpdateForm").value
        }),
    })
    .then((res) => res.json())
    .then(res => {
        document.getElementById(cId).innerHTML = `<span>${res.content}</span>`;
    })
    });

    document.getElementById('updateCancle').addEventListener('click', function() {
        fetch("http://localhost:8000/auction/selectComment/" + cId, {
            method: "GET",
            headers: {
                "Content-Type":"application/json",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            }
        })
        .then((res) => res.json())
        .then(res => {
            console.log(res);
            document.getElementById(cId).innerHTML = `<span>${res.content}</span>`;
        })
    });
    
}

//댓글 삭제
function deleteComment(cId) {
    console.log("댓글삭제cId: " + cId);
    if(window.confirm("정말 삭제하시겠습니까?")) {
        fetch("http://localhost:8000/auction/deleteComment/" + cId, {
        method: "PATCH",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
            
        },
        body:JSON.stringify({
            "cId" : cId
        }),
    })
    .then(res => {
        document.getElementById('comment' + cId + 'Start').remove();
        console.log('통신 성공');
    })
    }
}

window.onload = () => {
    //게시글 뿌려주기
    document.getElementById('hidden-bId').value = localStorage.getItem("bId");
    console.log("auctionDetail bId: " + document.getElementById('hidden-bId').value);

    fetch('http://localhost:8000/auction/board/' + localStorage.getItem("bId") + "/" + localStorage.getItem("userNickname"), {
        method: "GET",
        headers: {
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res) => res.json())
    .then(res => {
        let boardId = res.boardId;
        let title = res.title;
        let bookTitle = res.bookTitle;
        let content = res.content;
        let currentPrice = res.currentPrice;
        let writer = res.writer;
        let createDate = res.createDate.substr(0,10);
        let timeLimitHour = -res.timeMap.hour;
        let timeLimitMinute = -res.timeMap.minute;
        let imageInfo = res.imageInfo;
        let buyer = res.buyer;
        let isClose = res.isClose;
        
        //update처리 위한 localStorage 저장
        localStorage.setItem("title", title);
        localStorage.setItem("content", content);

        localStorage.setItem("currentPrice", currentPrice);
        localStorage.setItem("bookTitle", bookTitle);
        // localStroage.setItem("createDate", createDate);
        for(let i = 0; i < imageInfo.length; i++) {
            localStorage.setItem("imageInfo", JSON.stringify(imageInfo));
        }

        // console.log("auctionDetail getItem: " + localStorage.getItem("userNickname"));
        // console.log("auctionDetail res: " + writer);

        //수정, 삭제, 신고, 홈 버튼 보이기
        if(localStorage.getItem("userNickname") == writer) {
            document.getElementById("buttonArea").innerHTML = '<button type="button" id="updateBtn" style="margin-right:5px; cursor: pointer;" onclick="updateBoard();">수정</button><button type="button" id="deleteBtn" style="margin-right:5px; cursor: pointer;" onclick="deleteBoard();">삭제</button><div class = "modal-header" style = "display: inline-block;"></div>';
        } else {
            document.getElementById("buttonArea").innerHTML = '<button type="button" id="reportBtn" data-bs-toggle="modal" data-bs-target="#reportModal" onclick="reportBoard();">신고</button';
        }
        document.getElementById('homeArea').innerHTML = `<span style="margin-left:20px;"s><a style="font-weight: bold; text-decoration: none; color: burlywood;" href = "auction.html">HOME</a></span><input type = "hidden" id = "hidden-writer" value = ${writer}/>`
        document.getElementById('title').innerHTML = title;
        document.getElementById('date').innerHTML = createDate;
        document.getElementById('textContent').innerHTML = content;
        document.getElementById('startPrice').innerHTML = currentPrice;
        let currentPriceForm = currentPrice.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        if(timeLimitHour == 0 && timeLimitMinute == 0) {
            document.getElementById('time').innerHTML = "";    
        } else {
            document.getElementById('time').innerHTML = '<span style="margin-left:10px; font-size: small; color: gray;"><span style="color: gray;">마감까지...</span>' + '<span style = "color:black;">' + timeLimitHour + "</span>시간" + '<span style = "color:black;">' + timeLimitMinute + '</span>분 남았습니다.</span>';
        }

        //alert
        if(buyer == null && writer != localStorage.getItem("userNickname")) {
            document.getElementById('alert').innerHTML = '<span style="color:gray; font-size: smaller; margin-left: 10px;">입찰에 참여해 보세요.</span>';
        } else if(buyer == null && writer == localStorage.getItem('userNickname') && isClose == 'N') {
            document.getElementById('alert').innerHTML = '<span style="color:gray; font-size: smaller; margin-left: 10px;">아직 입찰한 사용자가 없습니다.</span>';
        } else if(buyer != null && isClose == 'Y') {
            document.getElementById('alert').innerHTML = `<span style = "color:gray; font-size: smaller;"><span style="color:gray; font-weight: bold;">${buyer}</span>님이 <span style = "color:gray; font-weight: bold;">${currentPriceForm}원</span>으로 입찰되었습니다!</span>`;
        } else if(buyer == null && writer == localStorage.getItem('userNickname') && isClose == 'Y') {
            document.getElementById('alert').innerHTML = '<span style = "color:gray; font-size: smaller;"><a href = "auctionWrite.html" style="text-decoration: none; color: gray;">입찰한 사용자가 없이 종료되었습니다. 글을 다시 올려 보세요.</a></span>';
        } else {
            document.getElementById('alert').innerHTML = `<span style = "color:gray; font-size: smaller;"><span style="color:gray; font-weight: bold;">${buyer}</span>님이 <span style = "color:gray; font-weight: bold;">${currentPriceForm}원</span>으로 입찰 중입니다!</span>`;
        }
        
        //사진 처리
        for(let i = 0; i < imageInfo.length; i++) {
            document.getElementById('slides').innerHTML += `<li><img src="${imageInfo[i].imageUrl}" width="300px" height="300px"/><input type="hidden" class="obejctKey" value="${imageInfo[i].objectKey}"/> <input type="hidden" class="imageLevel" value="${imageInfo[i].imageLevel}"/></li>`;
            
            //슬라이드 관련
            const slides = document.querySelector('#slides');
            const slideImg = document.querySelectorAll('#slides li');
            let currentIdx = 0;
            const slideCount = slideImg.length; //슬라이드 개수

            const prev = document.querySelector('.prev');
            const next = document.querySelector('.next');
            const slideWidth = 300;
            const slideMargin = 100;

            slides.style.width = (slideWidth + slideMargin + slideMargin) * slideCount + 'px';

            function moveSlide(num) {
                console.log("슬라이드개수: " + slideCount); 
                slides.style.left = -num * 400 + 'px';
                currentIdx = num;
            }

            prev.addEventListener('click', function() {
                if(currentIdx !== 0) moveSlide(currentIdx - 1);
            });

            next.addEventListener('click', function() {
                if(currentIdx !== slideCount -1) {
                moveSlide(currentIdx + 1);
                }
            });
        }
        console.log("입찰버튼 조건확인: " + buyer);
        //입찰 버튼
        if(localStorage.getItem("userNickname") != writer && isClose == 'N') {
            document.getElementById('modalBtn').innerHTML = '<button type="button" data-bs-toggle="modal" data-bs-target="#priceModal" id="priceBtn" style="margin-left: 100px;">입찰</button>'
        } else if(localStorage.getItem("userNickname") != writer && isClose == 'Y') {
            document.getElementById('modalBtn').innerHTML = '<button type="button" id = "priceBtnDisabled" disabled>거래완료</button>';
        } else if(localStorage.getItem('userNickname') == writer && isClose == 'Y' && buyer == "null") {
            document.getElementById('modalBtn').innerHTML = '';
        } else if(localStorage.getItem('userNickname') == writer && isClose == 'Y' && buyer != "null") {
            document.getElementById('modalBtn').innerHTML = '<button type = "button" id = "chatBtn">입찰자와 채팅</button>';
        }
        
    })

    //입찰가 전달
    document.getElementById("priceSubmitBtn").addEventListener('click', function() {
        console.log("입찰버튼 눌렀을 시 bId: " + document.getElementById('hidden-bId').value);
        console.log("입찰버튼 눌렀을 시 currentPrice: " + document.getElementById('inputPrice').value);
        console.log("입찰버튼 눌렀을 시 buyer: " + localStorage.getItem("userNickname"));
        
        
        fetch("http://localhost:8000/auction/board/price", {
            method: "PATCH",
            headers: {
                "Content-Type":"application/json",
                Authorization:localStorage.getItem('authorization'),
                RefreshToken:localStorage.getItem('refreshToken'),
            },
            body:JSON.stringify({
                "boardId": document.getElementById('hidden-bId').value,
                "buyer": localStorage.getItem("userNickname"),
                "currentPrice": document.getElementById('inputPrice').value
            }),
        })
        .then((res) => res.json())
        .then(res => {
            console.log("입찰가 통신 성공");
            document.querySelector('.btn-close').click();
            document.getElementById('inputPrice').value = "";
            console.log("입찰가 전달 시 response: " + JSON.stringify(res));

            document.getElementById('startPrice').innerHTML = res.currentPrice;
            //alert
            let writer = document.getElementById('hidden-writer').value;
            if(res.buyer == null && writer != localStorage.getItem("userNickname")) {
                document.getElementById('alert').innerHTML = '<span style="color:gray; font-size: smaller; margin-left: 10px;">입찰에 참여해 보세요.</span>';
            } else if(res.buyer == null && writer == localStorage.getItem('userNickname')) {
                document.getElementById('alert').innerHTML = '<span style="color:gray; font-size: smaller; margin-left: 10px;">아직 입찰한 사용자가 없습니다.</span>';
            } else {
                let currentPriceForm = res.currentPrice.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
                document.getElementById('alert').innerHTML = `<span style = "color:gray; font-size: smaller;"><span style="color:gray; font-weight: bold;">${res.buyer}</span>님이 <span style = "color:gray; font-weight: bold;">${currentPriceForm}원</span>으로 입찰 중입니다!</span>`;
            }
        });
    });
    

    //댓글 뿌려주기
    fetch('http://localhost:8000/auction/comment/' + document.getElementById('hidden-bId').value, {
        method: "GET",
        headers: {
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res) => res.json())
    .then(res => {
        //commentResponseDtoList 반환받음
        showCommentList(res);
    }); 
}
function showCommentList(res) {
    console.log("showCommentList실행됨");
    for(let comment of res.data) {
        console.log("onload cid: " + comment.cid); 
        let cId = comment.cid;
        let content = comment.content;
        let writer = comment.writer;
        let createDate = comment.createDate;
        
        document.getElementById('commentArea').innerHTML += `<div class="row" id = "comment${cId}Start"><div id = "comm${cId}Nickname" class = "col-3"><span class = "${cId}" id = "comm${cId}Nickname">${writer}</span></div><div id = "comm${cId}Content" class = "col-5"><span id = "${cId}">${content}</span></div><div id = "comm${cId}CreateDate" class = "col-2"><span class = "${cId}" style="color: gray; font-size: smaller;">${createDate}</span></div><div id = "comm${cId}Btn" class = "col-2"><span class = "${cId}" id = "commUpdate" style = "color: gray; font-size: small; margin-right: 20px;" onclick = "updateComment(${cId});">수정</span><span class = "${cId}" id = "commDelete" style = "color: gray; font-size: small;" onclick = "deleteComment(${cId});">삭제</span></div></div>`;
        if(writer != localStorage.getItem('userNickname')) {
            document.getElementById('comm' + cId + 'Btn').innerHTML = '';
            document.getElementById('comm' + cId + 'CreateDate').style.marginLeft = "132px";
        }
    }
}

//게시글 수정 페이지로
function updateBoard() {
    localStorage.setItem("bId", document.getElementById("hidden-bId").value);
    location.href="auctionUpdate.html";
}

//게시글 삭제
function deleteBoard() {
    localStorage.setItem("bId", document.getElementById("hidden-bId").value);
    console.log("게시글 삭제 bId" + localStorage.getItem("bId"));
    console.log("게시글 삭제 writer: " + localStorage.getItem("userNickname"));
    if(confirm("정말 삭제하시겠습니까?")) {
        fetch("http://localhost:8000/auction/boardDelete/" + localStorage.getItem("bId") + "/" + localStorage.getItem("userNickname"), {
        method: "PATCH",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        },
        body:JSON.stringify({
            "bId" : localStorage.getItem("bId"),
            "writer" : localStorage.getItem("userNickname")
        }),
    })
    .then(res => {
            deleteFatch();
    })
    }
}

const deleteFatch = () =>{
    fetch('http://localhost:8000/resource/images?bNo='+document.getElementById("hidden-bId").value+'&serviceName=auction',{
        method:"DELETE",
        headers:{
            "Content-Type" : "text/html"
        }
    })
    .then(res=>{
        console.log('통신 성공');
        alert("삭제되었습니다.");
        location.href="auction.html";
    })
}

//신고
const postReport=()=>{
    const bId = document.getElementById('hidden-bId').value;
    document.getElementById('report-btn').addEventListener('click', function(){
      const bId = document.getElementById('hidden-bId').value;
      console.log('신고 클릭됨');
      console.log('title : ' +document.getElementById('reportTitle').value);
      console.log('content : ' + document.getElementById('reportContent').value);
      console.log('bId : ' + bId);
      console.log('userNo : ' + localStorage.getItem('userNo'));
      fetch("http://localhost:8000/login/report",{
              method:"POST",
              headers:{
                  "Content-Type":"application/json",
              },
              body:JSON.stringify({
                  reportTitle : document.getElementById('reportTitle').value,
                  reportContent : document.getElementById('reportContent').value,
                  boardId : bId,
                  serviceName : "auction",
                  userNo : localStorage.getItem('userNo'),                   
                  })
              })
              .then(res =>{
                  alert('신고 접수 되었습니다.');
                  location.href="auctionDetail.html";
              })
    })
}

//입찰자 채팅
function chatConnection() {
    fetch('http://localhost:8000/auction/chat',  {
        method: "GET",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken')
        }
    })
    .then(res => {
        if(res.status == 200) {
            console.log("채팅 연결 성공");
        }
    })
}
