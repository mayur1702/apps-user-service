FROM maven
WORKDIR /app
COPY . .
RUN mvn package -Dmaven.test.skip=true
EXPOSE 8080
CMD ["java","-jar","/app/target/apps-user-service.jar"]