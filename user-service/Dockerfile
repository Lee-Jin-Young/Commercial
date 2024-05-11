FROM openjdk:21-jdk

WORKDIR /app

#local
# COPY build/libs/user-service-0.0.1-SNAPSHOT.jar /app/user-0.0.1-SNAPSHOT.jar
# server
COPY user-service-0.0.1-SNAPSHOT.jar /app/user-0.0.1-SNAPSHOT.jar

CMD ["./wait-for-mysql.sh", "mysql:3306", "--", "java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]
