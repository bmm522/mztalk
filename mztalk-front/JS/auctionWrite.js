//시작가 0,000 형식으로
function priceStandard(text) {
    const startPrice = document.getElementById("startPrice").value;
    const under = startPrice.slice(-3);
    const upper = startPrice.slice(0, startPrice.length-3);
    text.value = upper + "," + under;

}

function timeStandard(time) {
    const timeLimit = document.getElementById("timeLimit").value;
    if(timeLimit > 36) {
        alert("제한 시간은 3일을 넘길 수 없습니다.");
        time.value = 3;
        return false;
    }
}


// //제한시간 00:00 형식으로
// function timeStandard(time) {
//     const timeLimit = document.getElementById("timeLimit").value;
//     if(timeLimit.length >= 4 && timeLimit.length < 5) {
//         const hours = timeLimit.substring(0, 2);
//         const minute = timeLimit.substring(2, 4);

//         //제한시간 최대 3일
//         if(hours + minute > 7200) {
//             alert("제한시간은 3일을 넘길 수 없습니다."),
//             time.value = "72:00";
//             return false;
//         }

//         if(minute > 60) {
//             alert('60분을 넘길 수 없습니다.');
//             time.value = hours + ":00";
//             return false;
//         }

//         time.value = hours + ":" + minute;
//     }
// }

// window.onload = function() {
//     document.getElementById("wirteBtn").addEventListener('click', function(e) {
//         //유효성 검사
//         if(document.getElementById('title').value.trim() == null) {
//             alert('제목을 입력해 주세요.');
//         } else if(document.getElementById('content').value.trim() == null) {
//             alert('내용을 입력해 주세요.');
//         } else if(document.getElementById('minPrice').value.trim() == null) {
//             alert('최저가를 입력해 주세요.');
//         } else if(document.getElementById('timeLimit').value.trim() == null) {
//             alert('제한 시간을 입력해 주세요.'
//                 )
//         }
//     });
// }

// document.getElementById('writeBtn').addEventListener('click',function(){
//     console.log('클릭됨');
//     fetch("http://localhost:8000/auction/boards",{
//         method:"POST",
//         headers:{
//             "Content-Type":"application/json",
//             "Content-Type":"multipart/form-data"
//         },
    
//         body:JSON.stringify({
//             "title" : document.getElementById('title').value,
//             "content" : document.getElementById('content').value,
//             "file" : document.getElementById('inputFile').value,
//             "minPrice" : document.getElementById('minPrice').value,
//             "timeLimit" : document.getElementById('timeLimit').values
//         }),
//     })
//     .then(res => {
//         console.log('통신성공');
//     })
// });

//글쓰기 test
// function test(){
//     console.log('클릭됨');
//     fetch("http://localhost:8000/auction/boards",{
//         method:"POST",
//         headers:{
//             "Content-Type":"application/json",
//             "Content-Type":"multipart/form-data"
//         },
    
//         body:JSON.stringify({
//             "title" : document.getElementById('title').value,
//             "content" : document.getElementById('content').value,
//             "file" : document.getElementById('inputFile').value,
//             "minPrice" : document.getElementById('minPrice').value,
//             "timeLimit" : document.getElementById('timeLimit').values
//         }),
//     })
//     .then(res => {
//         console.log('통신성공');
//     })
// }

//글쓰기
function boardWrite() {
    const startPrice = document.getElementById('startPrice').value;
    const startPriceSplit = startPrice.split(',');
    const startPriceTrans = Number(startPriceSplit[0].concat(startPriceSplit[1]));

    
    fetch("http://localhost:8000/auction/board", {
        method: "POST",
        headers: {
            "Content-Type":"application/json",
        },
        body:JSON.stringify({
            "title" : document.getElementById('title').value,
            "content": document.getElementById('content').value,
            "startPrice": startPriceTrans,
            "timeLimit": document.getElementById('timeLimit').value
        }),
    })
    .then(res => {
        console.log('통신 성공');
    })
}

//파일추가
window.onload = () => {
    const fileArea = document.getElementById("fileArea");
    document.getElementById("addFile").addEventListener('click', () => {
        if(document.getElementsByName("files").length < 3) {
            const newDiv = document.createElement('div');
            newDiv.classList.add('col-10');
            newDiv.innerHTML = '<input type = "file" class = "form-control" name = "files" accept="image/*" onclick="showPreview(event);">';
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

