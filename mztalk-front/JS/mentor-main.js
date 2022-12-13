
// 글 검색 조건 ( 동적 쿼리 )
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
        } else {
            console.log('실패');
        }
    })
});


// 정렬 조건
const sort = document.getElementById('sort');

sort.addEventListener('change', function(){
    const sortValue = this.value;
    console.log("http://localhost:8000/mentors/board/search?sort="+sortValue);

    fetch("http://localhost:8000/mentors/board/search?sort="+sortValue,{
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
        } else {
            console.log('실패');
        }
    })
});