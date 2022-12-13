
document.getElementById('sendSearch').addEventListener('click', function(){
    const categoryValue = document.getElementById('category').value;
    const salaryValue = document.getElementById('salary').value;
    const selected = document.getElementById('type').value;
    const searchValue = document.getElementById('searchValue').value;

    console.log("http://localhost:8000/mentors/board/search?category=" + categoryValue + "&salary=" + salaryValue + "&" + selected + "=" + searchValue);
    fetch("http://localhost:8000/mentors/board/search?category=" + categoryValue + "&salary=" + salaryValue + "&" + selected + "=" + searchValue,{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            // Authorization:localStorage.getItem('Authorization'),
            // RefreshToken:localStorage.getItem('RefreshToken')
        },
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

