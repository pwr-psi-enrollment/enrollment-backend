FROM openjdk:11

COPY enrollment-service.jar /opt/enrollment-service/enrollment-service-1.0.0.jar

CMD ["java", "-jar", "/opt/enrollment-service/enrollment-service-1.0.0.jar"]
