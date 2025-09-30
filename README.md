![header](https://capsule-render.vercel.app/api?type=wave&color=auto&height=300&section=header&text=kotlinboot&fontSize=120)

# Kotlin Boot 프로젝트

Kotlin과 Spring Boot를 활용한 마이크로서비스 애플리케이션입니다. 이 프로젝트는 RESTful API, 데이터베이스 연동, 캐싱, 메시징 등의 기능을 제공합니다.

## 🚀 주요 특징

-   **Kotlin 언어**: 간결하고 표현력이 뛰어난 JVM 언어
-   **Spring Boot 3.2.3**: 최신 스프링 부트 프레임워크
-   **JPA/Hibernate**: 데이터베이스 ORM
-   **Redis**: 인메모리 캐싱 시스템
-   **Apache Kafka**: 메시지 큐잉 시스템
-   **MySQL**: 관계형 데이터베이스
-   **Swagger/OpenAPI**: API 문서화
-   **Docker**: 컨테이너화 지원

## 🛠 기술 스택

### Backend

-   **Language**: Kotlin 1.9.22
-   **Framework**: Spring Boot 3.2.3
-   **JVM**: OpenJDK 17
-   **Build Tool**: Gradle 8.x (Kotlin DSL)

### Database & Cache

-   **Database**: MySQL 8.0
-   **Cache**: Redis 7.2.4
-   **ORM**: Spring Data JPA + Hibernate

### Message Queue

-   **Apache Kafka**: 메시지 스트리밍 플랫폼
-   **Zookeeper**: Kafka 클러스터 관리

### DevOps

-   **Containerization**: Docker & Docker Compose
-   **API Documentation**: Swagger/OpenAPI 3

## 📋 프로젝트 구조

```
src/
├── main/
│   ├── kotlin/com/example/kotlinboot/
│   │   ├── KotlinbootApplication.kt          # 애플리케이션 진입점
│   │   ├── component/                        # 컴포넌트 레이어
│   │   │   ├── KafkaConsumer.kt             # Kafka 메시지 컨슈머
│   │   │   └── KafkaProducer.kt             # Kafka 메시지 프로듀서
│   │   ├── config/                          # 설정 클래스
│   │   │   ├── Constants.kt                 # 상수 정의
│   │   │   ├── CustomAdvice.kt              # 전역 예외 처리
│   │   │   ├── RedisConfig.kt               # Redis 설정
│   │   │   └── SwaggerConfig.kt             # Swagger 설정
│   │   ├── controller/                      # 컨트롤러 레이어
│   │   │   └── ProductController.kt         # 상품 관리 API
│   │   ├── dto/                            # 데이터 전송 객체
│   │   │   ├── ProductDto.kt               # 상품 DTO
│   │   │   └── ResponseDto.kt              # 응답 DTO
│   │   ├── entity/                         # 엔티티 클래스
│   │   │   └── ProductEntity.kt            # 상품 엔티티
│   │   ├── repository/                     # 데이터 접근 레이어
│   │   │   └── ProductRepository.kt        # 상품 리포지토리
│   │   └── service/                        # 비즈니스 로직 레이어
│   │       └── ProductService.kt           # 상품 서비스
│   └── resources/
│       └── application.yml                  # 애플리케이션 설정
├── test/                                    # 테스트 코드
└── mysql-initdb.d/                         # MySQL 초기화 스크립트
    └── create_table.sql
```

## 🔧 설치 및 실행

### 사전 요구사항

-   **JDK 17** 이상
-   **Docker & Docker Compose**
-   **Git**

### 1. 프로젝트 클론

```bash
git clone <repository-url>
cd kotlinboot
```

### 2. Docker 서비스 실행

```bash
# 인프라 서비스 시작 (MySQL, Redis, Kafka, Zookeeper)
docker-compose up -d
```

### 3. 애플리케이션 실행

#### 방법 1: Gradle로 직접 실행

```bash
./gradlew bootRun
```

#### 방법 2: JAR 빌드 후 실행

```bash
# JAR 파일 빌드
./gradlew bootJar

# JAR 파일 실행
java -jar build/libs/kotlinboot-1.0.0-SNAPSHOT.jar
```

#### 방법 3: Docker 컨테이너로 실행

```bash
# 애플리케이션 빌드
./gradlew bootJar

# Docker 이미지 빌드
docker build -t kotlinboot:latest .

# 컨테이너 실행
docker run -p 8080:8080 kotlinboot:latest
```

## 📡 API 엔드포인트

애플리케이션이 실행되면 다음 URL에서 API를 사용할 수 있습니다:

### 기본 URL

-   **애플리케이션**: http://localhost:8080
-   **Swagger UI**: http://localhost:8080/swagger-ui/index.html

### 상품 관리 API

| Method | Endpoint        | 설명           |
| ------ | --------------- | -------------- |
| GET    | `/product`      | 전체 상품 조회 |
| GET    | `/product/{id}` | 특정 상품 조회 |
| POST   | `/product`      | 상품 등록      |
| PUT    | `/product/{id}` | 상품 수정      |
| DELETE | `/product/{id}` | 상품 삭제      |

### 예시 요청

#### 상품 등록

```bash
curl -X POST "http://localhost:8080/product" \
  -H "Content-Type: application/json" \
  -d '{
    "code": "P001",
    "name": "샘플 상품",
    "price": 10000
  }'
```

#### 전체 상품 조회

```bash
curl -X GET "http://localhost:8080/product"
```

## 🏗 인프라 서비스

### MySQL 데이터베이스

-   **포트**: 3306
-   **데이터베이스**: appdb
-   **사용자**: appuser / 123456
-   **루트 비밀번호**: 123456

### Redis 캐시

-   **포트**: 6379
-   **비밀번호**: 없음

### Apache Kafka

-   **Broker 포트**: 29092
-   **Zookeeper 포트**: 2181

## 🔧 설정 정보

### 환경 변수

다음 환경 변수를 통해 설정을 변경할 수 있습니다:

| 변수명         | 기본값                                 | 설명                  |
| -------------- | -------------------------------------- | --------------------- |
| `DB_URL`       | `jdbc:mysql://localhost:3306/appdb...` | MySQL 연결 URL        |
| `DB_USER`      | `appuser`                              | 데이터베이스 사용자명 |
| `DB_PASS`      | `123456`                               | 데이터베이스 비밀번호 |
| `REDIS_HOST`   | `localhost`                            | Redis 호스트          |
| `REDIS_PORT`   | `6379`                                 | Redis 포트            |
| `KAFKA_SERVER` | `localhost:29092`                      | Kafka 서버 주소       |

### 프로파일별 설정

`application.yml`에서 다음 기능들이 설정되어 있습니다:

-   **JPA**: SQL 로깅, 물리적 네이밍 전략
-   **Redis**: 캐싱 설정
-   **Kafka**: 프로듀서/컨슈머 설정
-   **Error Handling**: 스택트레이스 숨김, 커스텀 에러 페이지

## 🧪 테스트

```bash
# 전체 테스트 실행
./gradlew test

# 특정 테스트 클래스 실행
./gradlew test --tests "*KotlinbootApplicationTests*"

# 테스트 리포트 생성
./gradlew test jacocoTestReport
```

## 📖 API 문서

Swagger UI를 통해 상세한 API 문서를 확인할 수 있습니다:

```
http://localhost:8080/swagger-ui/index.html
```

## 🔍 모니터링 및 로깅

-   **로깅**: SLF4J + Logback 사용
-   **SQL 로깅**: JPA 쿼리 로깅 활성화
-   **에러 처리**: 전역 예외 처리 핸들러 구현
