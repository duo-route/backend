# 빌드 환경 (Gradle & Java 21)
FROM gradle:8-jdk21 AS builder
WORKDIR /app

# 빌드에 필요한 파일들만 먼저 복사 (도커 레이어 캐시 활용)
COPY build.gradle settings.gradle ./
COPY src ./src

# 애플리케이션 빌드 (테스트 코드는 제외하여 빌드 속도 단축)
RUN gradle clean build -x test

# 실행 환경 (가벼운 JRE 21)
FROM eclipse-temurin:21-jre
WORKDIR /app

# 타임존 설정 (한국 시간)
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 빌드 단계에서 만들어진 jar 파일만 복사
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

# 컨테이너 실행 시 작동할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
