FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/gses2-btc-spring.jar gses2-btc-spring.jar
ENTRYPOINT ["java", "-jar", "/gses2-btc-spring.jar"]