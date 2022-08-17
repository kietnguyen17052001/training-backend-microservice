FROM openjdk:18
WORKDIR /app
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} customer-service.jar
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
EXPOSE 8081