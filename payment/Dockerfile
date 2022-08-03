FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} payment.jar
ENTRYPOINT ["java","-jar","/payment.jar"]

