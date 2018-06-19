# Adidas Coding Challenge - Subscription service

## How to build / run / use


### Build with Maven
Clone project from GitHub:
```
https://github.com/mantolin/adidas-coding-challenge.git  
```

Directory structure:
* Parent folder: parent pom file, docker-compose.yml and documentation
* eureka-service folder: eureka-service source code, pom file, Dockerfile
* subscription-service folder: subscription-service source code, pom file, Dockerfile
* event-service folder: subscription-service source code, pom file, Dockerfile
* email-service folder: email-service source code, pom file, Dockerfile 
* security-service folder: security-service source code, pom file, Dockerfile
* mysql-database folder: Dockerfile

Navigate to the parent folder and execute Maven:
```
cd <path-to>/adidas-coding-challenge
mvn clean package
``` 

Expected result:
```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] eureka-service ..................................... SUCCESS [ 10.806 s]
[INFO] subscription-service ............................... SUCCESS [ 39.103 s]
[INFO] event-service ...................................... SUCCESS [  3.119 s]
[INFO] email-service ...................................... SUCCESS [  2.474 s]
[INFO] security-service ................................... SUCCESS [  4.436 s]
[INFO] adidas-coding-challenge ............................ SUCCESS [  0.061 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

### Run with Docker ToolBox
*REMARK: This procedure has been tested on Windows 7 OS with Dockers ToolBox installed.*


Start Docker, navigate to the parent folder and build images with docker-compose:
```
cd <path-to>/adidas-coding-challenge
docker-compose build
```

From the same parent folder, run the containers with docker-compose:
```
docker-compose up -d
```

Check all containers are up and running:
```
docker-compose ps
```

Expected result:
```
        Name                      Command               State           Ports
--------------------------------------------------------------------------------------
email-service          java -jar email-service.jar      Up      0.0.0.0:8303->8303/tcp
eureka-service         java -jar eureka-service.jar     Up      0.0.0.0:8300->8300/tcp
event-service          java -jar event-service.jar      Up      0.0.0.0:8302->8302/tcp
mysql-database         docker-entrypoint.sh mysqld      Up      0.0.0.0:3306->3306/tcp
security-service       java -jar security-service.jar   Up      0.0.0.0:8304->8304/tcp
subscription-service   java -jar subscription-ser ...   Up      0.0.0.0:8301->8301/tcp
```

Wait until all services are started withing its container. Check log files to identify when the services are started or if there is any issue:
```
docker-compose logs --follow 
```

### Use with Postman
*REMARKS:*
* Subscription service is protected. Before calling it, you will have to obtain an access token. Check Security section for instructions on how to obtain an access token.
* This procedure has been tested on Windows 7 OS with Dockers ToolBox installed. In order to access the services, the Docker Virtual Box IP must be use. It can be obtained by executing the following command on Docker Terminal:
    ```
    docker-machine ls 
    ```
    
    Expected result:
    ```
    NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER        ERRORS
    default   *        virtualbox   Running   tcp://192.168.99.100:2376           v18.05.0-ce
    ```
    
    Where in this case the IP that we should use to call the services is 192.168.99.100

* Use Swagger UI to obtain full Subscription service API documentation:
    ```
    http://192.168.99.100:8301/swagger-ui.html 
    ```

Open Postman (or similar tool) and configure the service call as follows:
* Action: POST
* URL: http://192.168.99.100:8301/rest/subscription
* Authorization:
    * Type: Bearer Token
    * Token: the access token obtained previously (see Security section)
* Body:
    * Type: raw (JSON)
    * Payload:
        ```
        {
            "email":"mikel@gmail.com",
            "dateOfBirth":"1981-03-15",
            "gender":"M",
            "consent":true,
            "newsletterId":1
        }
        ```

Send the request. Expected result:
* Status: 200
* Body:
    ```
    {
        "subscriptionId": 1
    }
    ```


## Security
Subscription service has been protected with an access token. An Authentication Server has been implemented to procure access tokens and validate them. *password* grand type has been enabled in the Authentication Server and the following users have been created using an in memory authentication:
```
NAME                PASSWORD                    ROLES
------------------- --------------------------- -----------
adidasAnonym        adidasAnonymPassword        NONE
adidasUser          adidasUserPassword          USER
adidasAdmin         adidasAdminPassword         USER, ADMIN
```

In order to access the Subscription service, the user must have *USER* role.


### How to obtain an access token
Open Postman (or similar tool) and configure the service call as follows:
* Action: POST
* URL: http://192.168.99.100:8304/oauth/token
* Authorization:
    * Type: Basic Auth
    * Username: adidas_subscription
    * Password: adidascodingchallenge
* Body:
    * Type: x-www-form-urlencoded
    * Keys:
        ```
        Key                 Value                       Description
        ------------------- --------------------------- -----------
        grant_type          password        
        client_id           adidas_subscription          
        username            <user_name>         
        password            <user_password>
        ```

Send the request. Expected result:
* Status: 200
* Body:
    ```
    {
        "access_token": "3ad6fab9-5093-4b0a-a968-e6326a2760f5",
        "token_type": "bearer",
        "expires_in": 42465,
        "scope": "read write"
    }
    ```
Where *access_token* value is the one that should be used.


## Rest API documentation
Swagger has been enabled to generate specifications for Rest APIs. You can access to the generated specifications using Swagger UI in the following URLs:
* subscription-service
    ```
    http://192.168.99.100:8301/swagger-ui.html
    ```

* event-service
    ```
    http://192.168.99.100:8302/swagger-ui.html
    ```

* email-service
    ```
    http://192.168.99.100:8303/swagger-ui.html
    ```
    
* security-service
    ```
    http://192.168.99.100:8304/swagger-ui.html
    ```


## Used libraries

### org.springframework.boot:spring-boot-starter-parent:2.0.2.RELEASE
It provides place for common Spring Boot configuration and dependencies. It is a great way to use Spring Boot.


### org.springframework.boot:spring-boot-starter-test
It provides some libraries for Unit Testing such as JUnit and Mockito.


### org.springframework.boot:spring-boot-starter-web
It enables building web applications using Spring MVC. Uses Tomcat as the default embedded container.


### org.springframework.cloud:spring-cloud-starter-netflix-eureka-server
It enables the Spring Boot application to act as an Eureka Server where clients can be registered.


### org.springframework.cloud:spring-cloud-starter-netflix-eureka-client
It enables the Spring Boot application to act as an Eureka Client which is registered to an Eureka Server.


### org.springframework.boot:spring-boot-starter-data-jpa
It enables a JPA based repository with Hibernate.


### org.springframework.cloud:spring-cloud-starter-openfeign
It provides a dynamic implementation of an interface decorated with Spring MVC annotations to perform declarative REST client calls. It provides Client Side Load Balancing (Ribbon) and Circuit Breaker (Hystrix) functionality.


### org.springframework.cloud:spring-cloud-starter-security
It enables security on the application. It is used to build an Authentication Manager.


### org.springframework.cloud:spring-cloud-starter-oauth2
It enables OAuth2 on the application. It is used to build an Authentication Server and a Resource Server.


### org.springframework.cloud:spring-cloud-starter-sleuth
It enables a trace and span values in the logs that can be used to track the calls accross different services.

### mysql:mysql-connector-java
It provides JDBC drivers for MySQL database.


### io.springfox:springfox-swagger2
It generates specifications for Rest APIs. It examines an application at runtime to infer API semantics based on spring configurations, class structure and various compile time java Annotations.


### io.springfox:springfox-swagger-ui
It provides User Interface to display the specifications generated by Swagger.


### com.h2database:h2
It provides a Java in-memory database. Used with test scope for Unit Testing.
