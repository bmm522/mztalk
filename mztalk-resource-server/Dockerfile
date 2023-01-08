FROM adoptopenjdk/openjdk11
CMD ["mvn", "clean", "package", "-DskipTests"]
ARG JAR_FILE_PATH=target/*.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
