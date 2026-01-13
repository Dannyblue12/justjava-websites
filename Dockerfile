# Start from a Java 17 JDK
FROM eclipse-temurin:17-jdk

# Set workdir
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the jar inside Docker
RUN ./mvnw clean package -DskipTests

# Copy the built jar to a standard location
RUN cp target/practicingJava-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
