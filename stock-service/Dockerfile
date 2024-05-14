FROM openjdk:21-jdk

WORKDIR /app

#local
# COPY build/libs/stock-service-0.0.1-SNAPSHOT.jar /app/user-0.0.1-SNAPSHOT.jar
# server
COPY stock-service-0.0.1-SNAPSHOT.jar /app/user-0.0.1-SNAPSHOT.jar

CMD ["./wait-for-mysql.sh", "mysql:3306", "--", "java", "-jar", "stock-service-0.0.1-SNAPSHOT.jar"]
