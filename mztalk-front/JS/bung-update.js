window.onload = function(){
    document.getElementById('bungTitle').value = localStorage.getItem('title');
    document.getElementById('bungContent').value = localStorage.getItem('content');
    document.getElementById('bId-hidden').value = localStorage.getItem('bId');
    
    const imageInfo = JSON.parse(localStorage.getItem("imageInfo"));

    document.getElementById('deadlineDate').value = localStorage.getItem('closeRead');
    document.getElementById('group-select').value = localStorage.getItem('fullGroup');

    for(let i = 0; i<document.getElementsByClassName('form-check-input').length ; i++){
        console.log(localStorage.getItem('category'));
        console.log(document.getElementsByClassName('form-check-input')[i].value);
        if(localStorage.getItem('category').includes(document.getElementsByClassName('form-check-input')[i].value)){
            console.log('실행');
            document.getElementsByClassName('form-check-input')[i].checked = true;
        }
    }
    for(let i = 0 ; i < imageInfo.length ;  i++){
        let objectData = imageInfo[i].objectKey;
        if(i == 0){
            document.getElementById('image-div-div').innerHTML +=  '<div class="'+objectData+'"><div class="imageDiv" id="imageDiv"> <div class="carousel-item active"><img class="d-block w-100" src="'+imageInfo[i].imageUrl+'" alt="사진이 없습니다" /></div></div></div>';
        } else {
            document.getElementById('image-div-div').innerHTML +=  '<div class="'+objectData+'"><div class="imageDiv" id="imageDiv"> <div class="carousel-item"><img class="d-block w-100" src="'+imageInfo[i].imageUrl+'" alt="사진이 없습니다" /></div></div></div>';
        }
        
        
        // console.log(imageInfo[i].imageName);
        // console.log(objectData);
        document.getElementById("carouselExampleControls").innerHTML += `<div class="${objectData}"><span class = "imageName">${imageInfo[i].imageName}</span><div class="count-div"></div><span id = "fileDelete" style="color: gray;margin-left: 5px; cursor: pointer;" onclick="deleteFile('${objectData}');">X<br></span></div>`;
        
    }

    // if(document.getElementsByClassName('count-div').length < 3){

    // } else {

    //     document.getElementById('fileUpload').remove();
    // }
    
   console.log(document.getElementsByClassName('count-div').length);
}



function deleteFile(objectData) {

    if(confirm('정말 삭제하시겠습니까?')) {
        console.log("삭제시objectKey: " + objectData);
        console.log(document.getElementsByClassName(objectData));
        document.getElementsByClassName(objectData)[1].remove();
        document.getElementsByClassName(objectData)[0].remove();
        
        fetch('http://localhost:8000/resource/image-detail?imageName=' + objectData, {
            method: 'DELETE',
            headers: {
                "Content-Type": "text/html",
                Authorization: localStorage.getItem('authorization'),
                RefreshToken: localStorage.getItem('refreshToken'),
            }
        })
        .then(res=>{
            if(res.status == 200) {
                console.log("이미지 삭제 완료");
            }
        })
    }

}

var count = document.getElementsByClassName('count-div').length;
const addBtn = () =>{
    console.log("1 : " + document.getElementsByClassName('count-div').length);

    if(document.getElementsByClassName('count-div').length < 3){
        const fileArea = document.querySelector('#fileArea');   

            console.log("2 : " + document.getElementsByClassName('count-div').length);
            console.log('클릭');
             for(let i = 0; i < 1; i++) {
                if(count < 3) {
                    count++;
                    const newDiv = document.createElement('div');
                    newDiv.classList.add('mb-3');
                    newDiv.innerHTML = '<div class="count-div"></div><input type="file" id="fileUpload" style="float: left;" name="image">';
			        fileArea.append(newDiv);
                } else {
                   
                    return false;
                }         
            }

    } else{
        alert('사진은 최대 3장까지만 가능합니다.');
    }

       
    
}

const uploadEvent = () =>{
    
}

// document.getElementById('fileUpload').addEventListener('click', function(){
    
//     if((document.getElementsByClassName('imageName').length + document.getElementsByClassName('imageDiv').length) < 3){

//     } else {
//         alert('사진은 최대 3장까지만 가능합니다.');
//          document.getElementById('fileUpload').style.display = 'none';
//     }
// })

document.getElementById('edit-btn').addEventListener('click', function(){
    let deadlineDate = '';

    // console.log('edit : ' +  document.getElementsByClassName('choiceDay')[0]);
    if(document.getElementsByClassName('choiceDay')[0] == undefined){
        deadlineDate = document.getElementById('deadlineDate').value;
    } else {
        deadlineDate = document.getElementById('calYear').innerHTML+"-"+document.getElementById('calMonth').innerHTML+"-"+document.getElementsByClassName('choiceDay')[0].innerHTML;
    }

    // console.log(deadlineDate);

    // console.log(document.getElementById('fileUpload').value);
    // if(!document.getElementById('fileUpload').value){
    //     alert('사진을 입력해주세요');
    //   } else {
        let checkValue = '';
        let cnt = 0;
         
        for(let i = 0 ; i < document.getElementsByClassName('form-check-input').length ; i++){
            
            if(document.getElementsByClassName('form-check-input')[i].checked){
                checkValue += document.getElementsByClassName('form-check-input')[i].value+ " ";
                cnt += 1;       
            }
           
        }
     
        if(cnt > 1){
            alert('카테고리는 하나만 선택해주세요');
        } else if(cnt ==0){
            alert('카테고리를 선택해주세요');
        } else {
           
            const form = document.getElementById('image-form');
            const payload = new FormData(form);
        
            fetch('http://localhost:8000/resource/update-image',{
                method: 'POST',
                body: payload,
            })
            .then(res=>{
                editBungData(checkValue, deadlineDate);      
            })
    
    
    
    
    
            
        }
    // }
    
})

const editBungData = (checkValue, deadlineDate) =>{
    fetch('http://localhost:8000/bung/mainBoardUpdate/'+localStorage.getItem('bId'), {
        method:"PATCH",
       headers:{
       "Content-Type" : "application/json",
       Authorization:localStorage.getItem('authorization'),
       RefreshToken:localStorage.getItem('refreshToken'),
   },
   body:JSON.stringify({
       boardWriter : localStorage.getItem("userNickname"),
       boardTitle : document.getElementById('bungTitle').value,
       boardContent : document.getElementById('bungContent').value,
       deadlineDate : deadlineDate,
        fullGroup:document.getElementById('group-select').value,
       category : checkValue
   })
   
})
.then(res=>{
   
   location.href="bung-service-main.html";
})
}