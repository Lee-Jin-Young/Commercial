FROM openjdk:21-jdk

WORKDIR /app

# local
COPY build/libs/product-service-0.0.1-SNAPSHOT.jar /app/product-0.0.1-SNAPSHOT.jar
# server
# COPY product-service-0.0.1-SNAPSHOT.jar /app/product-0.0.1-SNAPSHOT.jar

CMD ["./wait-for-mysql.sh", "mysql:3306", "--", "java", "-jar", "product-0.0.1-SNAPSHOT.jar"]

