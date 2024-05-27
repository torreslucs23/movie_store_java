FROM eclipse-temurin:17-jdk-alpine

WORKDIR "/home"

COPY . .

RUN apk add maven

COPY pom.xml /home/demo/


WORKDIR "/home/demo"


RUN mvn clean install spring-boot:repackage

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/home/demo/target/demo-0.0.1-SNAPSHOT.jar"]