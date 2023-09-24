FROM azul/zulu-openjdk:17

ARG JAR_FILE=./build/libs/*.jar

COPY ${JAR_FILE} batch.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","batch.jar"]