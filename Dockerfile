# STEP 1: Build stage
FROM eclipse-temurin:25 AS build
WORKDIR /app

# Copy gradle wrapper and configuration files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Fix line endings for Windows users (CRLF to LF) and make it executable
RUN tr -d '\r' < gradlew > gradlew_unix && mv gradlew_unix gradlew
RUN chmod +x gradlew

# Download dependencies (this layer is cached if build.gradle doesn't change)
RUN ./gradlew dependencies --no-daemon

# Copy source code and build the jar
COPY src ./src
RUN ./gradlew bootJar --no-daemon

# STEP 2: Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Create a non-root user for security
RUN adduser --system --group spring
USER spring:spring

# Copy the built jar from the build stage
# Gradle puts the jar in build/libs/
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]