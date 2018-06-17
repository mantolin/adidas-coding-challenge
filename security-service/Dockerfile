FROM openjdk:8
ADD target/security-service-0.0.1-SNAPSHOT.jar security-service.jar
EXPOSE 8304
ENTRYPOINT ["java", "-jar", "security-service.jar"]