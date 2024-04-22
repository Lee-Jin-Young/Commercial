FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/commercial-0.0.1-SNAPSHOT.jar /app/commercial-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "commercial-0.0.1-SNAPSHOT.jar"]
