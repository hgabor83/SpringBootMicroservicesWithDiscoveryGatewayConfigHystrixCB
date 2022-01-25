# SpringBootMicroservicesWithDiscoveryGatewayConfig

Example project of Spring Boot Microservices with the following services School and Student, additionally there is Eureka Discovery Service, API Gateway and Config Server, 
and Hystrix Circuti Breaker

Circuit breaker: it monitors connections to services and falls back if the connection (service) is down

Additional steps to without Config server:
Git repo config-server with application.properties files containing the following common configuration for services:

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.instance.hostname=localhost

Then bootstrap.properties added to the services (student, school, gateway), as it is called first, before application.properties.
It contains:
spring.cloud.config.enabled=true
spring.cloud.config.uri=http://localhost:8083 (this is the cloud-config-server address)

Try it out:

With Postman for example:
Post: http://localhost:8099/schools/ 
Body: { "id":"1", "name":"School1" }

Post: http://localhost:8099/students/ 
Body: { "id":"1", "name":"Joe", "schoolId":"1" }

Get: http://localhost:8099/students/1 
This will give back the student 1 with the related school { "student": { "id": 1, "name": "Joe", "schoolId": 1 }, "school": { "id": 1, "name": "School1" } }
