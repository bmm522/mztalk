 //시작가 0,000 형식으로
function priceStandard(text) {
    const startPrice = document.getElementById("startPrice").value;
    const under = startPrice.slice(-3);
    const upper = startPrice.slice(0, startPrice.length-3);
    text.value = upper + "," + under;

}

function timeStandard(time) {
    const timeLimit = document.getElementById("timeLimit").value;
    if(timeLimit > 72) {
        alert("제한 시간은 3일을 넘길 수 없습니다.");
        time.value = 3;
        return false;
    }
}


//글쓰기 insert
function boardWrite() {
    const form = document.getElementById('image-form-form');
    const payload = new FormData(form);

    fetch('http://localhost:8000/resource/images',{
        method: 'POST',
        body: payload,
    })
    .then(res=>{
        postData();
    })
}

const postData = () =>{

    const startPrice = document.getElementById('startPrice').value;
    const startPriceSplit = startPrice.split(',');
    const startPriceTrans = Number(startPriceSplit[0].concat(startPriceSplit[1]));
    let date = new Date();

    fetch("http://localhost:8000/auction/board", {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            "title" : document.getElementById('title').value,
            "writer":localStorage.getItem('userNickname'),
            "content":document.getElementById('content').value,
            "startPrice": startPriceTrans,
            "timeLimit":  date.setHours(date.getHours() +  Number(document.getElementById('timeLimit').value)),
            "currentTime" : new Date().getTime(),
        }),
    })
    .then(res => {
        // localStorage.removeItem('bId');
        location.href="auction.html";
    })
}

//파일추가
window.onload = () => {
    console.log(document.getElementById('image-form-form'));
    document.getElementById('hidden-bId').value = localStorage.getItem('bId');
    console.log('bId : ' + document.getElementById('hidden-bId').value);
    const fileArea = document.getElementById("fileArea");
    
    document.getElementById("addFile").addEventListener('click', () => { 
        if(document.getElementsByName("files").length < 2) {
            const newDiv = document.createElement('div');
            newDiv.classList.add('col-10');
            newDiv.innerHTML = '<input type = "file" class = "form-control" name = "image" accept="image/*"">';
            fileArea.append(newDiv);
        } else {
            alert("파일은 3개까지 첨부 가능합니다.");
        }
    });
}

//파일 preview
function showPreview(event) {
    console.log("들어옴?");
    if(event.target.files.length > 0) {
        var src = URL.createObjectURL(event.target.files[0]);
        var preview = document.getElementById("file-ip-1-preview");
        preview.src = src;
        preview.style.display = "block";
    }
}

//책 검색
document.getElementById('searchBtn').addEventListener('click', function() {
    fetch("https://dapi.kakao.com/v3/search/book", {
        method: "GET",
        headers: {
            Authorization: "KakaoAK d7041cb01ccfe4c12792028ae9cb5fff"
        }
    })
    .then(res => {
        if(res.status == 200) {
            console.log("api서비스 통신 성공");
            console.log(res.documents[0].title);
        }
    })
});