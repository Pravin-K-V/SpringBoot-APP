FROM openjdk:21-jdk-slim as builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven
RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/retailer-0.0.1-SNAPSHOT.jar /app/retailer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/retailer.jar"]
