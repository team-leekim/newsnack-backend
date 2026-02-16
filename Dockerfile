# --- 1단계: 빌드 스테이지 ---
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# 의존성 캐싱
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
RUN ./gradlew dependencies --no-daemon

# 빌드
COPY src ./src
RUN ./gradlew bootJar -x test --no-daemon

# --- 2단계: 최종 실행 스테이지 ---
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app

# JVM 메모리 제한
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=50.0"

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
