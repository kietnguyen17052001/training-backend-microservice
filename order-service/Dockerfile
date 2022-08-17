FROM openjdk:18
WORKDIR /app
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} order-service.jar
ENTRYPOINT ["java", "-jar", "order-service.jar"]
EXPOSE 8084