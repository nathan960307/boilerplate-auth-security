# Spring Security 기반 JWT 인증 서버 보일러플레이트

이 프로젝트는 Spring Boot와 Spring Security를 기반으로 JWT 인증 기능을 구현한 보일러플레이트입니다.  
Postman을 통해 직접 인증 흐름을 테스트할 수 있도록 설계되었습니다.  
향후 Redis 연동, 자동 배포(Docker, GitHub Actions, GCP)까지 확장 예정입니다.

---

## 🔧 사용 기술 스택.

- Java 21
- **Spring Boot 3.5.4**
- Spring Security
- JWT (io.jsonwebtoken)
- Gradle
- (예정) Redis
- (예정) Docker + GCP

---

## ✅ 구현 기능 (진행 중)

- [x] 로그인 & 회원가입 API
- [x] Access / Refresh Token 발급
- [x] JWT 기반 인가 처리
- [ ] DB 연동 (User, RefreshToken 저장)
- [ ] Redis 연동 (블랙리스트 처리)
- [ ] 로그아웃 처리
- [ ] Dockerfile 작성
- [ ] 자동 배포 (GitHub Actions + GCP Cloud Run)

---

## 📬 API 테스트

- Postman으로 테스트 예정 (컬렉션 추가 예정)
- Swagger 문서화는 선택 사항

---

## 🗂 프로젝트 구조

```bash
src/main/java/com/example/authserver/
├── config/               # 시큐리티 설정
├── controller/           # 인증 관련 컨트롤러
├── dto/                  # 요청/응답 DTO
├── jwt/                  # 토큰 생성 및 검증
├── service/              # 인증 로직
└── entity/               # (예정) JPA 엔티티
