# Use a reliable JDK 17 image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy your jar to the container
COPY target/practicingJava-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on (default Spring Boot 8080)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
