window.onload =function(){
    console.log(localStorage.getItem('Authorization'));
    console.log(localStorage.getItem('RefreshToken'));
}


document.getElementById('sendResume').addEventListener('click', function(){

    fetch("http://localhost:8000/mentors/application",{
        method:"POST",
        headers:{
            "Content-Type":"application/json;",
            Authorization:localStorage.getItem('Authorization'),
            RefreshToken:localStorage.getItem('RefreshToken')
        },
        body:JSON.stringify({
            name :document.getElementById("name").value,
            phone : document.getElementById("phone").value,
            email : document.getElementById("email").value,
            job : document.getElementById("job").value,
            bank : document.getElementById("bank").value,
            account : document.getElementById("account").value
        })
    })
     .then((res)=>res.json())
    .then(res =>{
        console.log("res : " + res);
        if(res > 0){
            console.log('통신성공');
            document.getElementById('mentorForm').submit();
        } else {
            console.log('실패');
        }
    })
});

