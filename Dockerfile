#FROM maven
#WORKDIR /app
#COPY . .
#RUN mvn package -Dmaven.test.skip=true
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]

FROM openjdk:16
WORKDIR /app
ADD target/apps-user-service.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","apps-user-service.jar"]