# Spring Security 기반 JWT 인증 서버 보일러플레이트

이 프로젝트는 Spring Boot와 Spring Security를 기반으로 JWT 인증 기능을 구현한 보일러플레이트입니다.  
Postman을 통해 직접 인증 흐름을 테스트할 수 있도록 설계되었습니다.  
향후 Redis 연동, 자동 배포(Docker, GitHub Actions, GCP)까지 확장 예정입니다.

---

## 🔧 사용 기술 스택.

- **Language**: Java 21
- **Framework**: Spring Boot 3.5.4, Spring Security
- **Build Tool**: Gradle
- **Database**: MySQL 8.0
- **Authentication**: JWT (Access/Refresh Token), Spring Security Filter
- **Persistence**: Spring Data JPA (Hibernate)
- **Testing Tool**: IntelliJ .http 파일 기반 수동 테스트
- **Cache / Token Store **: Redis
- **CI/CD **: GitHub Actions + Docker + GCP Cloud Run


---

## 📘 API 명세 (작성 중)

- `POST /api/auth/signup` : 회원가입
- `POST /api/auth/login`  : 로그인 + AccessToken 발급
- `POST /api/auth/logout` : 로그아웃 (예정)
- `POST /api/auth/refresh`: 토큰 재발급 (예정)

---

## ✅ 구현 기능 (진행 중)

- [x] 로그인 API
- [x] 회원가입 API
- [ ] 로그아웃 API
- [x] Access  Token 발급
- [ ] Refresh Token 발급
- [x] JWT 기반 인증 처리
- [ ] JWT 기반 인가 처리
- [x] DB 연동 (User저장)
- [ ] Redis 연동 (블랙리스트 처리, RefreshToken 저장)
- [ ] Dockerfile 작성
- [ ] 자동 배포 (GitHub Actions + GCP Cloud Run)

---

## 📬 API 테스트

- .http 파일 기반 수동 테스트

---

