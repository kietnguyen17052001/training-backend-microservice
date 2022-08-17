FROM openjdk:18
WORKDIR /app
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} config-service.jar
ENTRYPOINT ["java", "-jar", "config-service.jar"]
EXPOSE 8088