FROM openjdk:8
ADD target/subscription-service-0.0.1-SNAPSHOT.jar subscription-service.jar
EXPOSE 8301
ENTRYPOINT ["java", "-jar", "subscription-service.jar"]