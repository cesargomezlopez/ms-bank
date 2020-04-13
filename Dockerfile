FROM openjdk:8-jdk-slim
COPY "./target/ms-bank-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8004
ENTRYPOINT ["java", "-jar", "app.jar"]