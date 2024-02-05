FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/movies.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]