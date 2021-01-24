FROM openjdk:8-jdk-alpine

WORKDIR /opt/app

ARG JAR_FILE=target/AppointmentSchedulerAPI-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","app.jar"]