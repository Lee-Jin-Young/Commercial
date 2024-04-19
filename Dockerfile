FROM openjdk:11-jdk

WORKDIR /app

COPY target/my-spring-boot-app.jar /app/commercial-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "commercial-0.0.1-SNAPSHOT.jar"]
