FROM docker.io/eclipse-temurin:21-ubi9-minimal
WORKDIR /app
COPY target/*.jar /app/application.jar
CMD ["java", "-jar", "application.jar"]
