# SpringBootMicroservicesWithDiscoveryGatewayConfig

Example project of Spring Boot Microservices with the following services School and Student, additionally there is Eureka Discovery Service, API Gateway and Config Server, Hystrix Circuti Breaker, AOP logging for Student service

- Updated with AOP logging for Student service
- Updated with some exception handling mechanism for Student service
- Updated with some unit test example for Student service

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

Postman collection updated, so you can load it into Postman and run.

Manually in browser:

With Postman for example:
Post: http://localhost:8099/schools/ 
Body: { "id":"1", "name":"School1" }

Get: http://localhost:8099/schools/1
This will give back the school 1  { id: 1, name: "School1"}

Post: http://localhost:8099/students/ 
Body: { "id":"1", "name":"Joe", "schoolId":"1" }

Get: http://localhost:8099/students/1 
This will give back the student 1 with the related school { "student": { "id": 1, "name": "Joe", "schoolId": 1 }, "school": { "id": 1, "name": "School1" } }


SpringDoc:
http://localhost:8081/v3/api-docs
http://localhost:8082/v3/api-docs
http://localhost:8081/swagger-ui/index.html
http://localhost:8082/swagger-ui/index.html
