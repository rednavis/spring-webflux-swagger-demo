FROM openjdk:17-jdk-alpine
ARG JAR_FILE=rest-api/build/libs/rest-api-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]