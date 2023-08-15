FROM openjdk:17-oracle

ARG JAR_FILE=./snapspot-api/build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]