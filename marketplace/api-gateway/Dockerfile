FROM eclipse-temurin:17-jdk-alpine

# direktori
WORKDIR /app

# copy jar file
COPY target/*.jar app.jar

# expose
EXPOSE 9000

# run
CMD ["java", "-jar", "app.jar"]