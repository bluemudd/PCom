# PolicyCommunnity 프로젝트

## 목적
1. 각 구청별 진행되고 있는 정책, 건의사항, 공약의 진행도를 확인하는 웹
2. 대용량 데이터 처리가 가능한 DB서버 구축 및 관리
3. Rest API 통신과 axios 통신 학습
4. 간단한 Vuetify 학습

## 참여
- 기획 : bluemudd

- frontend : bluemudd

- backend: bluemudd

### => 개인 프로젝트


## 개발 방식 및 도구(backend)

Spring boot, H2, MySQL

### JWT Token 로그인 방식 적용

- 토큰을 발행하고 헤더에 넣어 관리하는 방식
- 그 중 가장 뼈대를 이루고 있는 기능 구현에 초점

### 영속 비영속 상태에 따른 캐시 관리

- EntityManager와 JPArepository 둘을 동시에 사용하여 구현
- DB 접근이 많은 API는 EntityManager를 통해 캐시를 적절히 사용
- 좀더 효율적인 DB접근이 무엇인지 학습

### JSON Return 형식

- JSON request 전달 및 관리가 용이한 ResponseEntity 적용
- Header와 Body의 역할에 대해 학습후 이를 적절히반영


### [로그인구현](docs/login/README.md)

### [아고라](docs/agora/README.md)
