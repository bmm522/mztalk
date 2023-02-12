# MZTALK-GatewayAPIServer

## Description

- 🔊프로젝트 소개
  - MZTALK의 모듈화 된 각 서비스의 로드밸런싱, JWT 유효성 검증, CORS 처리를 위해 만든 API 서버
  - MZTALK 프로젝트 : https://github.com/bmm522/mztalk
  
- 🏗️개발 기간 : 2022.10 ~ 2022.11

- 🛠️사용기술
   - Java
   - Spring Boot, Spring Cloud Eureka, Spring Cloud Gateway, Spring Data JPA
   - MySQL

## Project Structure


### 1. 로드밸런싱

---

  - 각 API 서버의 로드밸런싱을 처리해주었습니다. 이를 통해 서비스 요청 시에 트래픽을 쌓을 수 있었습니다.

  <img src="https://user-images.githubusercontent.com/102157839/218321431-fb899aed-a7ed-453e-a20b-d7f2b7e33561.png" width="730" height="400">
  

---

### 2. JWT 검증

---

  - 서비스 요청이 들어올 때 마다 토큰 검증을 하고, 만약 유효시간이 끝나면 상태코드 401을 반환하여 프론트 단에서 리프레시 토큰으로 재요청을 보내 새로운 토큰을 발급 받을 수 있게 하였습니다.


 ![image](https://user-images.githubusercontent.com/102157839/218319193-c6535ed3-dd9e-48c9-be0c-1a03909807be.png)

---

### 3. CORS 처리

---

  - 모든 서비스의 CORS와 관련된 처리를 대신 함으로써 각 서비스는 CORS 처리를 안해줘도 통신이 가능합니다.
  
  ![image](https://user-images.githubusercontent.com/102157839/218319145-795d219b-21fb-4ad1-9803-56cc0ce94e3e.png)


