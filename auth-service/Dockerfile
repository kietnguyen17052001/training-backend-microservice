FROM openjdk:18
WORKDIR /app
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} auth-service.jar
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
EXPOSE 8085