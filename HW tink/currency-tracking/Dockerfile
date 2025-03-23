
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY build/libs/Bank-app_T-Bank-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]