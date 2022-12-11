window.onload =function(){
    console.log(localStorage.get("Authorization"));
    console.log(localStorage.get("RefreshToken"));
}


document.getElementById('sendResume').addEventListener('click', function(){

    fetch("http://localhost8000/mentors/application",{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            "Authorization":localStorage.get("Authorization"),
            "RefreshToken":localStorage.get("RefreshToken")
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
        if(res.result > 0){
            console.log('통신성공');
            document.getElementById('mentorForm').submit();
        } else {
            console.log('실패');
        }
    })
});
