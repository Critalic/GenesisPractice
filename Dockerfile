FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/gses2-btc-spring.jar gses2-btc-spring.jar
ENTRYPOINT ["java", "-jar", "/gses2-btc-spring.jar"]