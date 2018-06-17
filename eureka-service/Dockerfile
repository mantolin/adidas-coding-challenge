FROM openjdk:8
ADD target/eureka-service-0.0.1-SNAPSHOT.jar eureka-service.jar
EXPOSE 8300
ENTRYPOINT ["java", "-jar", "eureka-service.jar"]