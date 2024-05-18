FROM openjdk:21-jdk

WORKDIR /app

#local
# COPY build/libs/order-service-0.0.1-SNAPSHOT.jar /app/order-0.0.1-SNAPSHOT.jar
# server
COPY order-service-0.0.1-SNAPSHOT.jar /app/order-0.0.1-SNAPSHOT.jar

CMD ["./wait-for-mysql.sh", "mysql:3306", "--", "java", "-jar", "order-0.0.1-SNAPSHOT.jar"]

