FROM amazoncorretto:11-alpine-jdk
COPY target/cloud-config-server-0.0.1-SNAPSHOT.jar cloud-config-server-0.0.1.jar
ENTRYPOINT ["java","-jar","/cloud-config-server-0.0.1.jar"]