FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

COPY --from=build /app/target/entrenador-ms-0.0.1-SNAPSHOT.jar entrenador-ms.jar
RUN chown spring:spring entrenador-ms.jar

USER spring

EXPOSE 8080
ENTRYPOINT ["java","-jar","/entrenador-ms.jar"]
