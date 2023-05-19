FROM openjdk:17-jdk-slim
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY build/libs/transaction-routine-0.0.1-SNAPSHOT.jar /app/transaction-routine.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /app/transaction-routine.jar