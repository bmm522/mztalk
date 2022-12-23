
window.onload= function(){
    


    console.log(localStorage.getItem('bId'));
    fetch('http://localhost:8000/bung/mainBoardSelect/'+localStorage.getItem('bId'),{
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then((res)=>res.json())
    .then(res=>{
        let boardId = res.boardId;
        let title = res.title;
        let writer = res.writer;
        let createDate = res.createDate;
        let imageInfo = res.imageInfo;
        let content = res.content;
        let nowGroup = res.nowGroup;
        let fullGroup = res.fullGroup;
        let category = res.category;
        let deadlineDate = res.deadlineDate;
        // console.log(imageInfo);
        console.log(JSON.stringify(imageInfo));
        document.getElementById('header').innerHTML = '<strong>'+content+'</strong>' 
        document.getElementById('writer').innerHTML = writer;
        document.getElementById('date').innerHTML=createDate;
        document.getElementById('bungContent').placeholder = content;
        document.getElementById('group').innerHTML=nowGroup + " / " +fullGroup;
        document.getElementById('category').innerHTML = category;
        document.getElementById('closeRead').innerHTML = deadlineDate;



        for(let i = 0 ; i < imageInfo.length ;  i++){
            console.log(imageInfo[i].objectKey);
            document.getElementsByClassName('slideshow-container')[0].innerHTML +=  '<div class="mySlides fade"><img src="'+imageInfo[i].imageUrl+'" style="height: 150%; width: 100%;" alt="alt text">  <input type="hidden" class="imageName" value="${imageInfo[i].objectKey}"/>  <input type="hidden" class="imageLevel" value="${imageInfo[i].imageLevel}"/>  </div>';
      
        }

        for(let i = 0 ; i<imageInfo.length ; i++){
            document.getElementById('dot-div').innerHTML += '<span class="dot"></span>';
        }
    console.log("사이즈 : "  + imageInfo.length);
     
        





    //     document.getElementById('main-div').innerHTML += 

    // `<div id="header" style="height: 50px;">
    //     <strong>${title}</strong>
    // </div>
    // <hr>
    // <div id="writer" style="float: left; width: 50%;">
    //     ${writer}
    // </div>
    // <div id="date" style="float: left; width: 50%;">
    //     ${createDate}
    // </div>

    // <hr><hr><hr>

    // <div class="slideshow-container" id="slide-div">

    // `
    // for(let i = 0 ; i < imageInfo.length ;  i++){
    //     document.getElementById('main-div').innerHTML += `       <div class="mySlides fade">
    //     <img src="${imageInfo[i].imageUrl}" style="width:150%" height="100px;" alt="alt text"/>
    //     <input type="hidden" class="imageName" value="${imageInfo[i].objectKey}"/>
    //     <input type="hidden" class="imageLevel" value="${imageInfo[i].imageLevel}"/>      </div>
    // `
    // }
    // document.getElementById('main-div').innerHTML +=`
  
    // </div>
    
    // <br>

    // <div style="text-align:center">
    //     <span class="dot"></span> 
    //     <span class="dot"></span> 
    //     <span class="dot"></span> 
    // </div>

    // <div style="width: 100%; height: 225px; float: left; text-align: center;">
    //     <br>
    //     <input type="text" Placeholder="${content}" id="bungContent" name="bungContent" readonly>
    // </div>

    // <br><br><br><br><br>

    // <div class="infomationBtn" style="height: 200px; float: left; width: 33%;">
    //     <br>
    //     <div id="group" style="height: 66.66px;">
    //         ${nowGroup} / ${fullGroup}
    //     </div>
    //     <div id="category" style="height: 66.66px;">
    //         ${category}
    //     </div>
    //     <div id="closeRead" style="height: 66.66px;">
    //         ${deadlineDate}
    //     </div>
    // </div>
    // <div class="writerBtn" style="height: 200px; float: left; width: 33%;">
    //     <br>
    //     <div id="group" style="height: 66.66px;">
    //         <button type="button" class="btn btn-outline-warning">신청자 현황 보기</button>
    //     </div>
    //     <div id="category" style="height: 66.66px;">
    //         <button type="button" class="btn btn-outline-warning">게시글 수정</button>
    //     </div>
    //     <div id="closeRead" style="height: 66.66px;">
    //         <button type="button" class="btn btn-outline-warning">게시글 삭제</button>
    //     </div>
    // </div>
    // <div class="actionBtn" style="height: 200px; float: left; width: 33%;">
    //     <br>
    //     <div id="group" style="height: 66.66px;">
    //         <button type="button" class="btn btn-outline-danger">신고하기</button>
    //     </div>
    //     <div id="category" style="height: 66.66px;">
    //         <button type="button" class="btn btn-outline-warning">문의하기</button>
    //     </div>
    //     <div id="closeRead" style="height: 66.66px;">
    //         <button type="button" class="btn btn-outline-warning">신청하기</button>
    //     </div>
    // </div>`
    })

    
    showSlides();
}

let slideIndex =0;
function showSlides() {
    

    let i;
    const slides = document.getElementsByClassName("mySlides");
    const dots = document.getElementsByClassName("dot");
    
    // console.log(slides[0] + " :222");
    

    
    
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) { slideIndex = 1 }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
        console.log(slideIndex-1)
        console.log(slides[slideIndex-1]);

        slides[slideIndex-1].style.display = "block";
        dots[slideIndex - 1].className += " active";

    
    }
    // console.log(slideIndex)
    // console.log(slides[slideIndex - 1])
    // slides[slideIndex-1].style.display = "block";
    // dots[slideIndex - 1].className += " active";
    setTimeout(showSlides, 2500); // Change image every 2 seconds
}