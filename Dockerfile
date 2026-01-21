# 1. Use Java 17 JDK
FROM eclipse-temurin:17-jdk

# 2. Set working directory
WORKDIR /app

# 3. Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# 4. Make mvnw executable
RUN chmod +x mvnw

# 5. Build jar inside Docker (Skips tests for faster deployment)
RUN ./mvnw clean package -DskipTests

# 6. Copy the built jar (Using wildcard * so version changes don't break it)
RUN cp target/*.jar app.jar

# 7. Expose port
EXPOSE 8080

# 8. Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
