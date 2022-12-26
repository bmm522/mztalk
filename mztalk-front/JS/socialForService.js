window.onload = () =>{
    newMentorBoard();

}



//멘토 최신글뽑아오기
const newMentorBoard = () =>{
    console.log("성공?");
    fetch("http://localhost:8000/mentors/board/latest",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
      })
      .then((res)=> res.json())
      .then(res=>{       
        
        console.log("멘토최신글"+res.data);
        for(let board of res.data){
            let boardId = board.id;
            let category = board.category;
            let nickname = board.nickname;
            let career = board.career;
            let title = board.title;    
        document.querySelector('.col-md-6').innerHTML=
        `
        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col p-4 d-flex flex-column position-static">
                <strong class="d-inline-block mb-2 text-primary">World{카테고리}</strong>
                <h3 class="mb-0">{글제목}</h3>
                <div class="mb-1 text-muted">Nov 12{작성일}</div>
                <p class="card-text mb-auto">
                zzz
                </p>
                <a href="#" class="stretched-link">자세히보기</a>
            </div>
            <div class="col-auto d-none d-lg-block">
                <img src="img/recommend_02.jpeg" style="width: 150px; height: 250px;" >      
            </div>
            </div>
        </div>
        `;
        }





        
            
    })
}