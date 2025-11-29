# Portfolio-back

Kotlin + Spring Boot 기반으로 개발한 개인 포트폴리오 백엔드 서비스입니다.

Vue 기반의 프론트엔드와 연동되어 **실제 서비스 아키텍처와 동일한 형태로 동작**하도록 구축했습니다.

이 프로젝트는 다음을 목표로 합니다:

- **백엔드 개발자로서의 역량을 객관적으로 드러낼 수 있는 구조화된 API 제공**
- 엔티티/도메인 중심 설계(Domain-Driven Structure)와 확장 가능한 구조 적용
- dev/prod 환경 분리, 설정 파일 분리 등 **실무형 Spring Boot 프로젝트 구성 연습**
- 실제 서비스 아키텍처와 유사한 형태의 프론트(Vue) ↔ 백엔드(Spring Boot) 통신 구현
- 테이블 설계부터 API 응답 포맷까지 **일관성과 유지보수성**을 고려하여 제작

---

## 📌 Git Commit Convention

이 프로젝트는 유지보수성과 가독성을 위해 **Conventional Commits 기반의 커밋 규칙**을 사용합니다.

### ✔ Commit Message Format

| Type | 설명 |
|------|-------|
| **feat** | 새로운 기능 추가 |
| **fix** | 버그 수정 |
| **chore** | 설정/환경/빌드 변경 |
| **refactor** | 기능 변화 없는 코드 구조 개선 |
| **docs** | 문서/README 수정 |
| **style** | 포맷팅/스타일 변경 (기능 영향 없음) |
| **test** | 테스트 추가/수정 |
| **assets** | 이미지/폰트/정적 리소스 추가/변경 |