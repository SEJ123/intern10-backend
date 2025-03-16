# intern10-backend
바로인턴 10기 백엔드 개발 과제 - Java
## 프로젝트 설명
- Spring Boot를 이용하여 JWT 인증/인가 로직과 API를 구현
## 기능 개발
- 사용자 인증 시스템을 구축 (회원가입, 로그인)
- JWT 기반 인증 메커니즘을 구현하여 보안성을 강화
- 역할 기반 접근 제어를 적용하여 관리자 권한이 필요한 API를 보호
## 요구 사항
- 기본으로 설정된 서버의 주소와 포트는 0.0.0.0:8080
- 모든 API 응답은 적절한 HTTP 상태 코드와 함께 application/json 형식으로 반환
- 모든 데이터는 메모리 내에서 처리


## API 명세서
  - 회원가입 /signup </br>
  ![image](https://github.com/user-attachments/assets/0b21a651-da23-4f2e-b5e8-d8a409a4d485)</br>

  - 로그인 /login </br>
  ![image](https://github.com/user-attachments/assets/f3e0af97-4148-4696-8028-63a6bc7d2802)</br>
  
  - 관리자 권한 부여 admin/users/{userId}/roles </br>
  ![image](https://github.com/user-attachments/assets/749d73b1-5df6-489d-8802-e1aab4c92509)</br>


  ## 실행 예시
  - 회원가입 성공</br>
  ![image](https://github.com/user-attachments/assets/ecd2af7f-dc86-4cff-a83a-5d1aaa0852ff)</br>

  - 회원가입 실패 : username, nickname이 중복인 경우</br>
  ![image](https://github.com/user-attachments/assets/646345dc-74cc-4b76-9ebe-bb46923e8741)</br>

  - 로그인 성공</br>
  ![image](https://github.com/user-attachments/assets/ad07e218-2a57-4380-980a-bd3e3f662572)</br>

  - 로그인 실패 : username, password가 일치하지 않는 경우</br>
  ![image](https://github.com/user-attachments/assets/6ec111d8-5430-4097-a94c-8bb751e34d6d)</br>

  - 관리자 권한 부여 성공</br>
  ![image](https://github.com/user-attachments/assets/9acf279b-0c85-471a-88df-d6d7d96995ef)</br>

  - 관리자 권한 부여 실패 : 관리자 권한이 아닌 경우(role=USER인 경우)</br>
  ![image](https://github.com/user-attachments/assets/9840ff00-f191-47a5-92d5-a0bb75f316fa)</br>





