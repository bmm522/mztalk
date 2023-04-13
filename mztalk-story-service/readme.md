

# MZTALK-StoryService

### Description

- 🔊프로젝트 소개
  - 개인 페이지 CRUD 로직 구현
  - 팔로우 언팔로우 REST API 방식 구현
  - 맞팔 리스트 팔로우 리스트 REST API로 채팅 서비스로 전달
  - MZTALK 프로젝트 : https://github.com/bmm522/mztalk

- 🏗️개발 기간 : 2022.12 ~ 2023.1

- 🛠️사용기술
  - Java
  - Spring Boot, Spring Data JPA
  - MySQL

## Views

#### 1. 팔로우 언팔로우
---
##### 팔로우
<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/112769188/231098826-665c7817-7c0d-495c-858c-cf3c8398e44c.png">

##### 언팔로우
<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/112769188/231099686-de2c1d46-d384-48b3-823d-806d9a6756e5.png">
 
 - 팔로우 유저와 팔로잉 유저를 PK 값을 주어 중복되지 않도록 하였습니다.
  

---

#### 2. 팔로우 리스트
---

<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/112769188/231100542-e9574935-0aa5-498a-aa6b-b20416ca4b3e.png">

   - 유저 별 팔로우 리스트를 볼 수 있습니다.
  
---


#### 3. 회원정보 바꾸기
---
<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/102157839/218302511-c2409d06-f8f4-4441-96b5-bbe086f495b1.png">

  - 회원가입때 진행했던 이메일로 아이디를 찾을 수 있습니다.
  
  - 회원가입때 진행했던 이메일로 비밀번호를 변경할 수 있습니다.
  
  - 기존의 비밀번호를 변경할 수 있습니다.
  
  - 기존의 닉네임을 변경할 수 있습니다.

---

#### 4. 메인화면
---
<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/112769188/231101745-61cc93dd-ca33-45ae-ba6e-8e1c995ce704.png">

  - 4개(멘토링, 벙, 경매, 개인페이지) 로부터 받은 서비스정보를 최신 순으로 나열하였습니다.
  
  - 각 게시물 별로 이동하여 자세히 볼 수 있습니다.
  


---

