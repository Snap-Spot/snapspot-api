FROM --platform=linux/amd64 openjdk:17-alpine

ARG JAR_FILE=./snapspot-api/build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]