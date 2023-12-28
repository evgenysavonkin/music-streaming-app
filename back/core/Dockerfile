FROM maven:3-amazoncorretto-21 AS builder
WORKDIR /app/core
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-al2-full as final
WORKDIR /app/core
COPY --from=builder /app/core/target/app_core-1.0-SNAPSHOT.jar core.jar

CMD ["java", "-jar", "core.jar"]



