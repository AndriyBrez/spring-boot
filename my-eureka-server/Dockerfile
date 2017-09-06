FROM openjdk:8
VOLUME /tmp
ADD ./target/my-eureka-server-0.0.1-SNAPSHOT.jar /eureka-server.jar

ENTRYPOINT [ "java", "-DPORT=8761", "-jar", "/eureka-server.jar" ]