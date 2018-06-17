FROM openjdk:8
ADD target/event-service-0.0.1-SNAPSHOT.jar event-service.jar
EXPOSE 8302
ENTRYPOINT ["java", "-jar", "event-service.jar"]