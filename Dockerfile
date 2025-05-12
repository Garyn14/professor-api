# ---- Fase de construcción con Maven ----
FROM maven:3.9-amazoncorretto-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Fase de ejecución ----
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=builder /app/target/professors-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]