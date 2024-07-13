FROM eclipse-temurin:17
ARG JAR_FILE=targer/*.jar
COPY ./target/pokemon-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]