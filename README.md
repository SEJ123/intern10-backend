# intern10-backend
바로인턴 10기 백엔드 개발 과제 - Java
## 프로젝트 설명
- Spring Boot를 이용하여 JWT 인증/인가 로직과 API를 구현
- 사용자 인증 시스템을 구축 (회원가입, 로그인)
- JWT 기반 인증 메커니즘을 구현하여 보안성을 강화
- 역할 기반 접근 제어를 적용하여 관리자 권한이 필요한 API를 보호


## API 명세서
  - 회원가입 POST /signup
    - Request Body </br>
      {  "username": "JIN HO",
        "password": "12341234",
        "nickname": "Mentos"
      }
      
    - ResponseBody : 성공 </br>
      {
        "username": "JIN HO",
        "nickname": "Mentos",
        "roles": [
              {
                "role": "USER"
              }
            ]
        }
    - ResponseBody : 실패 (이미 가입된 사용자)</br>
{
  "error": {
    "code": "USER_ALREADY_EXISTS",
    "message": "이미 가입된 사용자입니다."
  }
}
      
  - 로그인 POST /login
    - Request Body</br>
      {
      "username": "JIN HO",
      "password": "12341234"
      }
      
    - Response Body : 성공</br>
    {
    "token": "eKDIkdfjoakIdkfjpekdkcjdkoIOdjOKJDFOlLDKFJKL"
    }
  
    - Response Body : 실패 (잘못된 계정 정보) </br>
        {
        "error": {
          "code": "INVALID_CREDENTIALS",
          "message": "아이디 또는 비밀번호가 올바르지 않습니다."
        }
      }

  - 관리자 권한 부여 API PATCH admin/users/{userId}/roles
    - Path Variable 예시
      admin/users/15/roles

    - Response Body : 성공</br>
      {
        "username": "JIN HO",
        "nickname": "Mentos",
        "roles": [
          {
            "role": "Admin"
          }
        ]
      }

    - Response Body : 실패 (권한이 부족한 경우 (접근제한))</br>
    {
      "error": {
        "code": "ACCESS_DENIED",
        "message": "관리자 권한이 필요한 요청입니다. 접근 권한이 없습니다."
      }
    }





