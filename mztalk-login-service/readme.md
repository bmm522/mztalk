# MZTALK-LoginAPIServer

## Description

- 🔊프로젝트 소개
  - MZTALK의 서비스 요청 인증과 인가 절차를 위한 Spring Security + JWT 기반의 로그인 API 서버
  - MZTALK 프로젝트 : https://github.com/bmm522/mztalk

- 🏗️개발 기간 : 2022.08 ~ 2022.10

- 🛠️사용기술
  - Java
  - Spring Boot, Spring Security, Spring Data JPA, Spring Cloud Eureka
  - JWT, OAuth2
  - MySQL

## Views
#### 1. 로그인
---

<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/102157839/218302071-cf875866-aea5-4aca-a032-f6cd4216a957.png">


   - 로그인은 Spring Security + jwt와 REST API 방식으로 구현되어있습니다.
   
   - 백에서 로그인의 상태를 반환해주고 프론트에서 해당 상태값에 따라서 처리해 주었습니다.
<img width="684" alt="image" src="https://user-images.githubusercontent.com/102157839/218302214-567fd54f-f466-44ee-810e-95590020dc12.png">

 
  - 기본 로그인 이외에도 구글, 카카오, 네이버 로그인을 할 수 있습니다.
  
  - 로그인의 경로는 총 3가지로 나누었으며, jwt 토큰과 리프레시 토큰을 로컬로그인에서는 헤더에, 기존 소셜로그인은 쿠키에, 첫 소셜로그인은 바디에 담아서 프론트에 전송했고,
프론트에서는 토큰을 로컬스토리지에 담아서 처리했습니다.
 <img width="699" alt="image" src="https://user-images.githubusercontent.com/102157839/218302026-e7b334b5-7774-484f-a4ce-cc1153d9a775.png">
 
 
  - JWT 토큰의 유효시간이 끝나면 각 유저의 리프레시 토큰을 통해 JWT을 재발급 받아 재 요청을 보내서 새로운 JWT토큰을 발급받을 수 있게 했습니다.
<img width="851" alt="image" src="https://user-images.githubusercontent.com/102157839/218302308-aded27e6-73d9-4f3c-bac3-00aa2537a081.png">

---

#### 2. 회원가입
---
<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/102157839/218302392-3848556a-8b9d-4f01-a220-08fa1d160b48.png">

  - 아이디, 닉네임의 중복 검사를 비동기 처리했습니다.
  
  - 이메일 인증을 통해 유효한 사용자인지 확인합니다.
  
  - 비밀번호의 유효성, 비밀번호 확인을 입력 시에 체크합니다.
  
  - 모든 유효성 통과 후에 회원가입이 가능하도록 했습니다.

---

#### 3. 회원정보 바꾸기
---
<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/102157839/218302511-c2409d06-f8f4-4441-96b5-bbe086f495b1.png">

  - 회원가입때 진행했던 이메일로 아이디를 찾을 수 있습니다.
  
  - 회원가입때 진행했던 이메일로 비밀번호를 변경할 수 있습니다.
  
  - 기존의 비밀번호를 변경할 수 있습니다.
  
  - 기존의 닉네임을 변경할 수 있습니다.

---

#### 4. 관리자
---
<img width="400" height="300" alt="image" src="https://user-images.githubusercontent.com/102157839/218302579-670c6dfc-6c7b-4b7b-8011-a0fff7c25062.png">

  - 최근 1주일 간의 트래픽을 볼 수 있습니다.
  
  - 각 서비스 별로 원하는 날짜의 트래픽을 볼 수 있습니다.
  
  - 멘토 서비스에서 멘토를 신청한 사람의 서류와 리스트를 볼 수 있고, 신청을 수락하거나 거절할 수 있습니다.
  
  - 각 유저들이 신고한 글을 볼 수 있고, 신고를 수락하거나 거절할 수 있습니다.
  
  - 누적 신고가 많은 유저의 리스트를 볼 수 있고, 해당 유저를 퇴출할 수 있습니다.

---


