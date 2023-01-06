
const LOCALHOST_URL = "http://localhost:8000";

const moveMain = ()=>{
    window.open('loginpage.html', 'MZTALK').opener.close();
}

const move =() =>{
    fetch(`${LOCALHOST_URL}/login/username/`+document.getElementById('searchEmail').value,{
      method:"GET",
    })
    .then((res) => res.json())
    .then(res => {  
      document.getElementById('searchIdResult').value = res.result
      resultOfSearchId();
      search();
    });
}


const resultOfSearchId = () =>{
  let searchIdResult = document.getElementById('searchIdResult').value;
	if(document.getElementById('searchIdResult').value == 'notExist'){
		document.getElementById('re').innerHTML = "검색 결과가 없습니다.";
	} else {
		document.getElementById('re').innerHTML = "회원님의 아이디는 "+searchIdResult+" 입니다";
	}
}



const search=()=>{
  var inputForm = document.getElementById('input-form');
  var resultForm = document.getElementById('result');
  inputForm.style.transform="translateY(-700px)";
  inputForm.style.transition="2s";
  
  result.style.transform="translateY(-900px)";
  result.style.transition="2s";
  let size = document.getElementById('total-form');
  size.style.height='480px';
  size.style.overflowY='hidden';
  result.style.overflowY='hidden';
  // inputForm.style.display="none";
  
  
}