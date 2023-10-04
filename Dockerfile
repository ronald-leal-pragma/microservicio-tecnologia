FROM {JAVA_JDK_VERSION}
EXPOSE {PORT}
ADD ./build/libs/*.jar {EXECUTE_JAR_NAME}.jar
ENV PROFILE = {YOUR_PROFILE}
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}","-jar","/apiapp.jar"]