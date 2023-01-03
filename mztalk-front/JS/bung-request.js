window.onload= function(){
    // document.getElementById('bungRequestUserName').value = localStorage.getItem('userNickname');
    // console.log("이거나옴?" + localStorage.getItem('bId'));

    // document.getElementById('addbtn').addEventListener('click', function(){
    //     fetch('http://localhost:8000/bung/bungAddBoard', {
    //     method:"POST",
    //     headers:{
    //         "Content-Type" : "application/json",
    //         Authorization:localStorage.getItem('authorization'),
    //         RefreshToken:localStorage.getItem('refreshToken'),
    //     },
    //     body:JSON.stringify({
    //         addNickName : localStorage.getItem("userNickname"),
    //         addContent : document.getElementById('bungRequestuserReason').value,
    //         addPhone : document.getElementById('bungRequestUserPhone').value,
    //         boardId : localStorage.getItem("bId")
    //     }),
    // })
    // .then((res) => res.json())
    // .then(res => {
    //     console.log("신청 시 RESPONSE:" + JSON.stringify(res));

    //     if(res.message == "신청이 완료되었습니다.") {
    //         alert(res.message);
    //         location.href="bung-service-main.html";
    //     } else if(res.message == "마감시간이 종료되었습니다.") {
    //         alert(res.message);
    //         location.href="bung-service-main.html";
    //     } else if(res.message == "이미 신청한 게시글입니다.") {
    //         alert(res.message);
    //         location.href="bung-service-main.html";
    //     }
    // });

    // });

    fetch('http://localhost:8000/bung/bungAddBoardSelect/'+localStorage.getItem('addId'), {
        method:"GET",
        headers:{
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        }
    })
    .then(res=>res.json())
    .then(res=>{
        document.getElementById('add-name').innerHTML = '이 름 : &nbsp'+res.addNickName;
        document.getElementById('phoneNumber').innerHTML = '번 호 : &nbsp' + res.addPhone;
        document.getElementById('reason').innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;사 유 : ' + res.addContent;
    })

}


        

