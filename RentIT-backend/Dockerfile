# Use a base image with Java runtime
FROM openjdk:21 as builder


# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml, mvnw, mvnw.cmd, and source code into the container
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY src ./src

# Build the application with Maven
RUN chmod +x mvnw && ./mvnw clean install -DskipTests

# Use a smaller image for the final image (no need for Maven here)
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/target/rentit-backend-0.0.1-SNAPSHOT.jar rentit-backend.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "rentit-backend.jar"]
