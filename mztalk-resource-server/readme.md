# MZTALK-ResourceAPIServer

## Description

- 🔊 프로젝트 소개
  - MZTALK의 각 서비스와 AWS S3의 중간다리를 해주는 저장 API 서버
  - MZTALK 프로젝트 : https://github.com/bmm522/mztalk
 
- 🏗️ 개발 기간 : 2022.12.10 ~ 2022.12.23

- 🛠️ 사용 기술
  - Java
  - Spring Boot, Spring Cloud Eureka, Spring Data JPA
  - Swagger, AWS S3
  - MySQL

## Project Structure

### 1. Flow

---

- 모든 서비스의 첨부파일, 이미지를 하나의 저장서버를 둠으로써 작업효율을 올렸습니다.

![image](https://user-images.githubusercontent.com/102157839/218325752-8a7aa543-14cb-49de-aad6-fe81f95d29af.png)

- 저장 Flow 입니다.

![image](https://user-images.githubusercontent.com/102157839/218325867-4f4d1dd4-a20b-4aa8-9d24-dde34b75a46c.png)

- 첨부파일 관련 처리 Flow 입니다.

![image](https://user-images.githubusercontent.com/102157839/218325923-00a994e8-2844-4438-9d5c-74976bf50fef.png)

---

### 2. Swagger

---

- 팀원들이 테스트와 보고 사용할 수 있도록 swagger를 통해 api문서로 작성했습니다.

<img width="700" alt="스웨거3" src="https://user-images.githubusercontent.com/102157839/218326596-ae1632b2-5e09-4430-b4af-5f580ce86a38.jpg">


---

