<h1>MZTALK</h1>

## 🔊프로젝트 소개

---

- 총 5개의 멘토링, 번개 만남, 중고거래, 채팅, 개인 피드 서비스를 하는 MZ 세대를 위한 플랫폼
- 프론트(Vanilla js) + 7개의 모듈(REST API)기반으로 구성된 프로젝트

---


<h3> :book: Stacks </h3>

<img width="454" alt="image" src="https://user-images.githubusercontent.com/102157839/211439509-926a9090-68be-4b8a-897f-7e07ffe22931.png">




---
<h3> :pager: Architecture</h3>

<img width="553" alt="image" src="https://user-images.githubusercontent.com/102157839/211434879-b7e957a3-ca21-4879-bef6-bc0f93f2a0ff.png">

---

### :bulb: Commit Convention 

---

- feat : 새로운 기능에 대한 커밋 
- fix : build 빌드 관련 파일 수정에 대한 커밋 
- build : 빌드 관련 파일 수정에 대한 커밋 
- chore : 그 외 자잘한 수정에 대한 커밋(기타 변경) 
- ci : CI 관련 설정 수정에 대한 커밋 
- docs : 문서 수정에 대한 커밋 
- style : 코드 스타일 혹은 포맷 등에 관한 커밋 
- refactor : 코드 리팩토링에 대한 커밋 
- test : 테스트 코드 수정에 대한 커밋 

---

### :tv: View 

---

#### :inbox_tray: LoginService 

<img width="650" height="400" alt="image" src="https://user-images.githubusercontent.com/102157839/213100943-fce709c7-d2a6-4259-9b44-f03e55633785.png">


  - <small>로그인(구글, 카카오, 네이버 소셜 로그인) / 회원가입</small><br>
  - <small>트래픽 모니터링</small><br>


#### :running: BungService 

<img width="650"  alt="image" src="https://user-images.githubusercontent.com/102157839/213105086-2ec90c96-68c6-4214-8557-b0bf6e21639b.png">


  - <small>메인페이지(글 목록, 글 검색)</small><br>
  - <small>카카오 맵 API를 이용한 장소 검색 / 글 작성,수정,삭제 / 모임 신청, 강퇴, 마감 제한</small>

 
#### :incoming_envelope: ChatService 
 

 <img width="650" alt="image" src="https://user-images.githubusercontent.com/102157839/213106460-f72ed95d-4dcb-448e-9686-03ae1ca0d507.png">
 
 
  - <small>각 서비스 이용자간 채팅 연결</small><br>
  - <small>맞팔 상태인 회원과의 채팅방 개설</small>
 

#### :chart_with_upwards_trend: AuctionService

 <img width="650" height="400" alt="image" src="https://user-images.githubusercontent.com/102157839/213106848-4e8adb4a-977c-4c0e-a057-d646170ceb18.png">

- <small>게시글 등록, 수정, 삭제, 검색 / 댓글 수정, 삭제</small><br>
- <small>책 검색 / 입찰 기능 / 입찰 현황</small>
 

 
#### :green_book: MentorService
 
 <img width="650"  height="400" alt="image" src="https://user-images.githubusercontent.com/102157839/213112240-6cafc55b-e1a9-4187-ba6e-b8967e5f2755.png">

  - <small>메인페이지(글 목록, 리뷰보기) / 글 검색(Querydsl)</small><br>
  - <small>금융결제원API를 이용한 실명인증 / 글 작성</small><br>

 
#### :two_men_holding_hands: StoryService

<img width="650"   alt="image" src="https://user-images.githubusercontent.com/102157839/213104260-5f8d2217-4f5c-45d5-a26e-8dfb7100d9df.png">

  - <small>메인 페이지에서 각 서비스 별 최신 글 가져와 솔팅 / 각 게시글로 이동</small><br>
  - <small>유저 별 게시글 등록, 수정, 삭제 / 댓글 수정, 삭제</small><br>
  - <small>유저 별 팔로우 팔로잉 리스트</small><br>
  - <small>유저 별 회원정보 수정 </small><br>
 
 
 ---
### 💡서버 배포

---

 <img width="347" alt="image" src="https://user-images.githubusercontent.com/102157839/218383209-6c6f3aec-2012-4c76-97fd-27d52ee667bf.png">
 
 - 각 0.1 버전은 ddl-auto:create 설정이며, 0.2 이상 버전부터는 ddl-auto:validate 설정 입니다.

<img width="756" alt="image" src="https://user-images.githubusercontent.com/102157839/218383427-2d27009d-b6a1-4117-b93c-8a39ec589254.png">

- 각 서버는 보안설정이 쉬운 aws ec2서비스를 이용해 배포하였고, 테스트는 프리티어인 t2.micro인스턴스를, 실 배포시에는 많은 트래픽을 감당해야하는 게이트웨이 api 서버는 m5.xlarge 인스턴스를, 나머지는 m5.large 인스턴스를 이용했습니다. 각 서버의 컨테이너 위에서 구동했으며, db와 서비스를 볼륨 마운트를 통해서 구동했습니다.
