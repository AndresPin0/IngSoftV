FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/outcome-curr-mgmt-1.0-SNAPSHOT.jar app.jar
COPY logs /logs

EXPOSE 9092

ENTRYPOINT ["java", "-jar", "app.jar"]
