//입찰가 가격 제한
document.getElementById("priceSubmitBtn").addEventListener('click', function() {
    console.log(document.getElementById('startPrice').innerHTML);
    console.log(document.getElementById('inputPrice').value);
    if(Number(document.getElementById('startPrice').innerHTML) >= Number(document.getElementById('inputPrice').value)) {
        alert('시작 금액보다 큰 금액을 입력해 주세요');
    }
});
//입찰가 전달
document.getElementById("priceSubmitBtn").addEventListener('click', function() {
    fetch("http://localhost:8000/auction/board/" + document.getElementById("hidden-bId"), {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "currentPrice" : Number(document.getElementById('inputPrice').value)
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
});

//댓글 작성
function writeComment() {
    fetch("http://localhost:8000/auction/comment/" + document.getElementById("hidden-bId"), {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "bId" : document.getElementById("hidden-bId").value,
            "commInput" : document.getElementById("commInput").value
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
}

//댓글 수정
function updateComment() {
    document.getElementById('commContent').innerHTML = "<input type = 'text' id = 'commUpdate' style = 'width: 450px'/>"
    fetch("http://localhost:8000/auction/comment/" + cId, {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "cId" : document.getElementById("hidden-cId").value(),
            "content" : document.getElementById("commUpdate").value()
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
}

//댓글 삭제
function deleteComment() {
    if(window.confirm("정말 삭제하시겠습니까?")) {
        fetch("http://localhost:8000/auction/deleteComment/" + cId, {
        method: "PUT",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "cId" : document.getElementById("hidden-cId").value()
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
    }
}

window.onload = () => {
    //뿌려주기
    document.getElementById('hidden-bId').value = localStorage.getItem("bId");
    console.log("auctionDetail bId: " + document.getElementById('hidden-bId').value);

    fetch('http://localhost:8000/auction/board/' + localStorage.getItem("bId"), {
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
        let content = res.content;
        let currentPrice = res.currentPrice;
        let writer = res.writer;
        let createDate = res.createDate;
        let timeLimit = res.timeLimit;
        let imageInfo = res.imageInfo;
        

        //update처리 위한 localStorage 저장
        localStorage.setItem("title", title);
        localStorage.setItem("content", content);
        localStorage.setItem("timeLimit", timeLimit);
        localStorage.setItem("currentPrice", currentPrice);
        for(let i = 0; i < imageInfo.length; i++) {
            console.log("deatil imageInfo: " + JSON.stringify(imageInfo));
            localStorage.setItem("imageInfo", JSON.stringify(imageInfo));
        }

        // console.log("auctionDetail getItem: " + localStorage.getItem("userNickname"));
        // console.log("auctionDetail res: " + writer);

        //수정, 삭제 버튼 보이기
        if(localStorage.getItem("userNickname") == writer) {
            document.getElementById("buttonArea").innerHTML = '<button type="button" id="updateBtn" style="margin-right:5px;" onclick="updateBoard();">수정</button><button type="button" id="deleteBtn" onclick="deleteBoard();">삭제</button>';
        }
        document.getElementById('title').innerHTML = title;
        document.getElementById('date').innerHTML = createDate;
        document.getElementById('textContent').innerHTML = content;
        document.getElementById('startPrice').innerHTML = currentPrice;
        document.getElementById('time').innerHTML = timeLimit.substr(11, 13);

        //사진 처리
        for(let i = 0; i < imageInfo.length; i++) {
            console.log("deatil 사진처리: " + imageInfo[i].imageUrl);
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
        //localStroage bId 삭제
        localStorage.removeItem("bId");
    })
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