FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/discoveryservice.jar /usr/src/discoveryservice.jar
ENTRYPOINT ["java", "-jar", "/usr/src/discoveryservice.jar"]
