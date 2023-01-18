// 캘린더 
window.onload = () =>{
		
}

document.addEventListener("DOMContentLoaded", function() {
    buildCalendar();
});

var today = new Date(); // @param 전역 변수, 오늘 날짜 / 내 컴퓨터 로컬을 기준으로 today에 Date 객체를 넣어줌
var date = new Date();  // @param 전역 변수, today의 Date를 세어주는 역할

/**
 * @brief   이전달 버튼 클릭
 */
function prevCalendar() {
    this.today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
    buildCalendar();    // @param 전월 캘린더 출력 요청
}

/**
 * @brief   다음달 버튼 클릭
 */
function nextCalendar() {
    this.today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
    buildCalendar();    // @param 명월 캘린더 출력 요청
}

/**
 * @brief   캘린더 오픈
 * @details 날짜 값을 받아 캘린더 폼을 생성하고, 날짜값을 채워넣는다.
 */
function buildCalendar() {

    let doMonth = new Date(today.getFullYear(), today.getMonth(), 1);
    let lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);

    let tbCalendar = document.querySelector(".scriptCalendar > tbody");

    document.getElementById("calYear").innerText = today.getFullYear();                                  // @param YYYY월
    document.getElementById("calMonth").innerText = autoLeftPad((today.getMonth() + 1), 2);   // @param MM월


    // @details 이전 캘린더의 출력결과가 남아있다면, 이전 캘린더를 삭제한다.
    while(tbCalendar.rows.length > 0) {
        tbCalendar.deleteRow(tbCalendar.rows.length - 1);
    }


    // @param 첫번째 개행
    let row = tbCalendar.insertRow();



    // @param 날짜가 표기될 열의 증가값
    let dom = 1;

    // @details 시작일의 요일값( doMonth.getDay() ) + 해당월의 전체일( lastDate.getDate())을  더해준 값에서
    //               7로 나눈값을 올림( Math.ceil() )하고 다시 시작일의 요일값( doMonth.getDay() )을 빼준다.
    let daysLength = (Math.ceil((doMonth.getDay() + lastDate.getDate()) / 7) * 7) - doMonth.getDay();

    // @param 달력 출력
    // @details 시작값은 1일을 직접 지정하고 요일값( doMonth.getDay() )를 빼서 마이너스( - )로 for문을 시작한다.
    for(let day = 1 - doMonth.getDay(); daysLength >= day; day++) {

        let column = row.insertCell();

        // @param 평일( 전월일과 익월일의 데이터 제외 )
        if(Math.sign(day) == 1 && lastDate.getDate() >= day) {


            // @param 평일 날짜 데이터 삽입

            column.innerText = autoLeftPad(day, 2);


            // @param 일요일인 경우
            if(dom % 7 == 1) {
                column.style.color = "#FF4D4D";
            }

            // @param 토요일인 경우
            if(dom % 7 == 0) {
                column.style.color = "#4D4DFF";
                row = tbCalendar.insertRow();   // @param 토요일이 지나면 다시 가로 행을 한줄 추가한다.
            }

        }

        // @param 평일 전월일과 익월일의 데이터 날짜변경
        else {
            let exceptDay = new Date(doMonth.getFullYear(), doMonth.getMonth(), day);
            column.innerText = autoLeftPad(exceptDay.getDate(), 2);
            column.style.color = "#A9A9A9";
        }

        // @brief   전월, 명월 음영처리
        // @details 현재년과 선택 년도가 같은경우
        if(today.getFullYear() == date.getFullYear()) {

            // @details 현재월과 선택월이 같은경우
            if(today.getMonth() == date.getMonth()) {

                // @details 현재일보다 이전인 경우이면서 현재월에 포함되는 일인경우
                if(date.getDate() > day && Math.sign(day) == 1) {
                    column.style.backgroundColor = "#E5E5E5";
                }

                // @details 현재일보다 이후이면서 현재월에 포함되는 일인경우
                else if(date.getDate() < day && lastDate.getDate() >= day) {
                    column.style.backgroundColor = "#FFFFFF";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ calendarChoiceDay(this); }
                }

                // @details 현재일인 경우
                else if(date.getDate() == day) {
                    column.style.backgroundColor = "#FFFFE6";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ calendarChoiceDay(this); }
                }

            // @details 현재월보다 이전인경우
            } else if(today.getMonth() < date.getMonth()) {
                if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                    column.style.backgroundColor = "#E5E5E5";
                }
            }

            // @details 현재월보다 이후인경우
            else {
                if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                    column.style.backgroundColor = "#FFFFFF";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ calendarChoiceDay(this); }
                }
            }
        }

        // @details 선택한년도가 현재년도보다 작은경우
        else if(today.getFullYear() < date.getFullYear()) {
            if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                column.style.backgroundColor = "#E5E5E5";
            }
        }

        // @details 선택한년도가 현재년도보다 큰경우
        else {
            if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                column.style.backgroundColor = "#FFFFFF";
                column.style.cursor = "pointer";
                column.onclick = function(){ calendarChoiceDay(this); }
            }
        }


        dom++;

    }
}

/**
 * @brief   날짜 선택
 * @details 사용자가 선택한 날짜에 체크표시를 남긴다.
 */
function calendarChoiceDay(column) {

    // @param 기존 선택일이 존재하는 경우 기존 선택일의 표시형식을 초기화 한다.
    if(document.getElementsByClassName("choiceDay")[0]) {
        document.getElementsByClassName("choiceDay")[0].style.backgroundColor = "#FFFFFF";
        document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");
    }

    // @param 선택일 체크 표시
    column.style.backgroundColor = "#FF9999";


    // @param 선택일 클래스명 변경
    column.classList.add("choiceDay");
}

/**
 * @brief   숫자 두자릿수( 00 ) 변경
 * @details 자릿수가 한자리인 ( 1, 2, 3등 )의 값을 10, 11, 12등과 같은 두자리수 형식으로 맞추기위해 0을 붙인다.
 * @param   num     앞에 0을 붙일 숫자 값
 * @param   digit   글자의 자릿수를 지정 ( 2자릿수인 경우 00, 3자릿수인 경우 000 … )
 */
function autoLeftPad(num, digit) {
    if(String(num).length < digit) {
        num = new Array(digit - String(num).length + 1).join("0") + num;
    }
    return num;
}

// 카카오 지도 api
// 마커를 담을 배열입니다
let markers = [];

var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
mapOption = {
    center: new window.kakao.maps.LatLng(37.567910, 126.983120), // 지도의 중심좌표
    level: 5, // 지도의 확대 레벨
};  

// 지도를 생성합니다    
var map = new window.kakao.maps.Map(mapContainer, mapOption);



// var geocoder = new kakao.maps.services.Geocoder();

//     // 주소로 좌표를 검색합니다
//     geocoder.addressSearch('', function(result, status) {

//     // 정상적으로 검색이 완료됐으면 
//     if (status === kakao.maps.services.Status.OK) {

//     var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

//     // 결과값으로 받은 위치를 마커로 표시합니다
//     var marker = new kakao.maps.Marker({
//         map: map,
//         position: coords
//     });

//     // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
//     map.setCenter(coords);
//     } 
//     }); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            // var detailAddr = result[0].road_address.address_name;
            var detailAddr = result[0].address.address_name;
            
            var content = '<div class="bAddr">' +
                            '<span class="title">주소정보</span>' + 
                            detailAddr + 
                        '</div>';

            // 마커를 클릭한 위치에 표시합니다 
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.open(map, marker);

            // 클릭한 주소 정보를 가져옵니다 
            var address = detailAddr;

            var message = detailAddr;

            var resultDiv = document.getElementById('addressName'); 
            resultDiv.innerHTML = message;
        }   
    });
});

// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');

        for(var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }    
}

    // 지도를 클릭한 위치에 표출할 마커입니다
    var marker = new kakao.maps.Marker({ 
        // 지도 중심좌표에 마커를 생성합니다 
        position: map.getCenter() 
    }); 
    // 지도에 마커를 표시합니다
    marker.setMap(map);

    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    var mapTypeControl = new kakao.maps.MapTypeControl();

    // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
    // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

// 장소 검색 객체를 생성합니다
var ps = new window.kakao.maps.services.Places();  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new window.kakao.maps.InfoWindow({zIndex:1});

// 키워드로 장소를 검색합니다
searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

const keyword = document.getElementById('keyword').value;

if (!keyword.replace(/^\s+|\s+$/g, '')) {
    // alert('키워드를 입력해주세요!');
    return false;
}

// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
ps.keywordSearch( keyword, placesSearchCB); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
if (status === window.kakao.maps.services.Status.OK) {

    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    displayPlaces(data);

    // 페이지 번호를 표출합니다
    displayPagination(pagination);

} else if (status === window.kakao.maps.services.Status.ZERO_RESULT) {

    alert('검색 결과가 존재하지 않습니다.');
    return;

} else if (status === window.kakao.maps.services.Status.ERROR) {

    alert('검색 결과 중 오류가 발생했습니다.');
    return;

}
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

var listEl = document.getElementById('placesList');
menuEl = document.getElementById('menu_wrap');
fragment = document.createDocumentFragment();
bounds = new window.kakao.maps.LatLngBounds();
listStr = '';

// 검색 결과 목록에 추가된 항목들을 제거합니다
removeAllChildNods(listEl);

// 지도에 표시되고 있는 마커를 제거합니다
removeMarker();

for ( var i=0; i<places.length; i++ ) {

    // 마커를 생성하고 지도에 표시합니다
    var placePosition = new window.kakao.maps.LatLng(places[i].y, places[i].x);
        marker = addMarker(placePosition, i);
        itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(placePosition);

    // 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
    (function(marker, title) {
        window.kakao.maps.event.addListener(marker, 'mouseover', function() {
            displayInfowindow(marker, title);
        });

        window.kakao.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
        });

        itemEl.onmouseover =  function () {
            displayInfowindow(marker, title);
        };

        itemEl.onmouseout =  function () {
            infowindow.close();
        };
    })(marker, places[i].place_name);

    fragment.appendChild(itemEl);
}

// 검색결과 항목들을 검색결과 목록 Element에 추가합니다
listEl.appendChild(fragment);
menuEl.scrollTop = 0;

// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
map.setBounds(bounds);
}


// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

var el = document.createElement('li');
itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
            '<div class="info">' +
            '   <h5>' + places.place_name + '</h5>';

if (places.road_address_name) {
    itemStr += '    <span>' + places.road_address_name + '</span>' +
                '   <span class="jibun gray">' +  places.address_name  + '</span>';
} else {
    itemStr += '    <span>' +  places.address_name  + '</span>'; 
}
             
  itemStr += '  <span class="tel">' + places.phone  + '</span>' +
            '</div>';           

el.innerHTML = itemStr;
el.className = 'item';

return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png'; // 마커 이미지 url, 스프라이트 이미지를 씁니다
    imageSize = new window.kakao.maps.Size(36, 37);  // 마커 이미지의 크기
    imgOptions =  {
        spriteSize : new window.kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin : new window.kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new window.kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    };
    markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);
        marker = new window.kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage 
    });

marker.setMap(map); // 지도 위에 마커를 표출합니다
markers.push(marker);  // 배열에 생성된 마커를 추가합니다

return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
for ( var i = 0; i < markers.length; i++ ) {
    markers[i].setMap(null);
}   
markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
var paginationEl = document.getElementById('pagination');
    fragment = document.createDocumentFragment();
    i; 

// 기존에 추가된 페이지번호를 삭제합니다
while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild (paginationEl.lastChild);
}

for (i=1; i<=pagination.last; i++) {
    var el = document.createElement('a');
    el.href = "#";
    el.innerHTML = i;

    if (i===pagination.current) {
        el.className = 'on';
    } else {
        el.onclick = (function(i) {
            return function() {
                pagination.gotoPage(i);
            }
        })(i);
    }

    fragment.appendChild(el);
}
paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

infowindow.setContent(content);
infowindow.open(map, marker);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
while (el.hasChildNodes()) {
    el.removeChild (el.lastChild);
}
}




window.onload = function(){
    console.log(localStorage.getItem('bId'));
document.getElementById('bId-hidden').value=localStorage.getItem('bId');
   console.log( document.getElementById('bId-hidden').value);
   console.log("이미지 value 값 : " + document.getElementById('fileUpload').value);
  
}















document.getElementById('bung-write-btn').addEventListener('click', function(){
  if(!document.getElementById('fileUpload').value){
    alert('사진을 입력해주세요');
  } else {
    let checkValue = '';
    let cnt = 0;
    for(let i = 0 ; i < document.getElementsByClassName('form-check-input').length ; i++){
        
        if(document.getElementsByClassName('form-check-input')[i].checked){
            checkValue += document.getElementsByClassName('form-check-input')[i].value;
            cnt += 1;       
        }
       
    }
 
    if(cnt > 1){
        alert('카테고리는 하나만 선택해주세요');
    } else if(cnt ==0){
        alert('카테고리를 선택해주세요');
    } else {
       
        const form = document.getElementById('image-form-bung');
        const payload = new FormData(form);
    
        fetch('http://localhost:8000/resource/images',{
            method: 'POST',
            body: payload,
        })
        .then(res=>{
            postBungData(checkValue);      
        })





        
    }
}
});



const postBungData = (checkValue) =>{
    if(document.getElementById('bungTitle').value === '') {
        alert('제목을 작성해주세요!');
    } else if(document.getElementById('bungContent').value === '') {
        alert('내용을 작성해주세요!');
    } else if(document.getElementById('full-group').value === '') {
        alert('모집 인원을 지정해주세요!');
    } else if(document.getElementById('addressName').value === '') {
        alert('모임 장소 지정해주세요!');
    } else {
        fetch('http://localhost:8000/bung/mainInsertBoard', {
        method:"POST",
        headers:{
        "Content-Type" : "application/json",
        Authorization:localStorage.getItem('authorization'),
        RefreshToken:localStorage.getItem('refreshToken'),
   },
   body:JSON.stringify({
        boardWriterId : localStorage.getItem('userNo'),
       boardWriter : localStorage.getItem("userNickname"),
       boardTitle : document.getElementById('bungTitle').value,
       boardContent : document.getElementById('bungContent').value,
       deadlineDate : document.getElementById('calYear').innerHTML+"-"+document.getElementById('calMonth').innerHTML+"-"+document.getElementsByClassName('choiceDay')[0].innerHTML,
       fullGroup:document.getElementById('full-group').value,
       category : checkValue,
       address : document.getElementById('addressName').value
        })
        })
    .then(res=>{
   location.href="bung-service-main.html";
    })
}       
}

//멘토서비스보내기
document.getElementById('move-mentor-service').addEventListener('click',function(){
    
    fetch("http://localhost:8000/mentors",{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
        },
        body:JSON.stringify({
            nickname : localStorage.getItem('userNickname'),
            id : localStorage.getItem('userNo')
        })
    })
    .then(res =>{
        location.href="mentor-main.html";
    })             
});


//벙서비스보내기
document.getElementById('move-bung-service').addEventListener('click',function(){

    //이 구조는 그냥 버튼에 자기페이지 가는 로직
    //해당유저 게시판엔 userNo이 input hidden 걸려있어야 가게끔
    //개인페이지에서 다른 페이지 넘어가면 own을 초기화 삭제 해줘야한다
    //친구페이지 갈때는 set하면 안될듯
    
    fetch("http://localhost:8000/bung/mainBoards",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
            
        },
    })
    .then((res)=> res.json())
    .then(res=>{

        location.href="bung-service-main.html";
    })

});



//경매서비스 보내기
document.getElementById('move-auction-service').addEventListener('click',function(){

    //이 구조는 그냥 버튼에 자기페이지 가는 로직
    //해당유저 게시판엔 userNo이 input hidden 걸려있어야 가게끔
    //개인페이지에서 다른 페이지 넘어가면 own을 초기화 삭제 해줘야한다
    //친구페이지 갈때는 set하면 안될듯
    
    fetch("http://localhost:8000/auction/",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:localStorage.getItem('authorization'),
            RefreshToken:localStorage.getItem('refreshToken'),
            
        },
    })
    .then((res)=> res.json())
    .then(res=>{

        location.href="auction.html";

    })

});

