# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory in container
WORKDIR /app

# Copy your built JAR into the container
COPY target/practicingJava-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app uses (default 8080)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
