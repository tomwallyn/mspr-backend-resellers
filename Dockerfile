FROM openjdk:17-jdk-slim-buster

COPY build/libs/mspr-backend-resellers-*.jar /app/mspr-backend-resellers.jar

ENTRYPOINT ["/bin/sh", "-c", "java $JAVA_OPTS -jar /app/mspr-backend-resellers.jar"]
