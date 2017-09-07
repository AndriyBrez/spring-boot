FROM openjdk:8
VOLUME /tmp
ADD ./target/client-load-balance-0.0.1-SNAPSHOT.jar /client-load-balance.jar

ENTRYPOINT [ "java", "-jar", "/client-load-balance.jar"]