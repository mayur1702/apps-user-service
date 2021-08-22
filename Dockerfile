FROM openjdk:16-jdk-alpine
WORKDIR /app
COPY target/apps-user-service.jar .
EXPOSE 8080
CMD ["java", "-jar", "/app/apps-user-service.jar"]