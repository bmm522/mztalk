window.onload=function(){
    console.log("수정페이지 : " + localStorage.getItem('authorization'));
    console.log("수정페이지 : " + localStorage.getItem('refreshToken'));
    console.log("수정페이지 : " + localStorage.getItem('userNo'));
    console.log("수정페이지 : " + localStorage.getItem('userNickname'));
    
}




const image = document.querySelector(".modal-image");

function modalImage() {
    
    console.log(modalImage);
    image.classList
	image.style.display = "none";
}

function popup(image) {
    console.log(image);

    image.style.display = "flex";
}