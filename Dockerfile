#Maven Build
FROM maven:3.8.3-openjdk-11-slim AS builder
#COPY pom.xml /app/
COPY . .
#RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package -DskipTests

#Run
FROM eclipse-temurin:17-jdk-alpine as build
COPY /target/service-cartes-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9591
ENTRYPOINT ["java", "-jar", "app.jar"]