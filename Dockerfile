FROM openjdk:19-jdk-slim
EXPOSE 8080
COPY ./build/libs/*.jar app.jar
ENV PROFILE=local
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}","-jar","/app.jar"]