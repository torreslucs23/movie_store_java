FROM eclipse-temurin:17-jdk-alpine

WORKDIR "/home"

COPY . .

RUN apk add maven


RUN mvn clean install spring-boot:repackage

EXPOSE 8080

ENTRYPOINT [ "mvn" ,"spring_boot:run"]