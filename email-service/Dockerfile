FROM openjdk:8
ADD target/email-service-0.0.1-SNAPSHOT.jar email-service.jar
EXPOSE 8303
ENTRYPOINT ["java", "-jar", "email-service.jar"]