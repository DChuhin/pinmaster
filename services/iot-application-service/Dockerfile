FROM alpine/java:21-jdk
COPY ./build/libs/iot-application-service-0.0.1-SNAPSHOT.jar /app/iot-application-service.jar
ENV JAVA_OPTS=""
CMD java ${JAVA_OPTS} -jar /app/iot-application-service.jar
