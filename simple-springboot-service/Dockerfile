FROM openjdk:8
VOLUME /tmp
ADD ./target/simple-springboot-service-0.0.1-SNAPSHOT.jar /ip-service.jar

ENTRYPOINT [ "java", "-jar", "/ip-service.jar"]