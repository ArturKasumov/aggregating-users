FROM openjdk:21-jdk-slim

WORKDIR /aggregating-users-app

COPY target/aggregating-users-1.0-SNAPSHOT.jar aggregating-users.jar

EXPOSE 3020

ENTRYPOINT ["java", "-jar", "aggregating-users.jar"]