# Use the official OpenJDK base image
FROM openjdk:18.0.1

# Set the working directory in the container
WORKDIR /parcelflow

# Copy the compiled Spring Boot JAR file into the container
COPY target/ParcelFlow.jar ParcelFlow.jar

# Expose the port that the Spring Boot app runs on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "ParcelFlow.jar"]