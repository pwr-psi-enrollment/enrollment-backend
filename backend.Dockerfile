FROM maven:3.6.3-jdk-11

COPY ./ ./

RUN mvn package

CMD ["java", "-jar", "target/enrollment-backend-0.0.1-SNAPSHOT.jar"]