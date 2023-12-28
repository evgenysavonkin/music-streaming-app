FROM maven:3-amazoncorretto-21 AS builder
WORKDIR /app/security
COPY pom.xml .
COPY src ./src
COPY ../pom.xml /app
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-al2-full as final
WORKDIR /app/security
COPY --from=builder /app/security/target/app_security-1.0-SNAPSHOT.jar security.jar

CMD ["java", "-jar", "security.jar"]
