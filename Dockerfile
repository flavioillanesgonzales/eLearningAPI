FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY target/eLearningAPI-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Duser.timezone=GMT-5", "-jar", "app.jar"]