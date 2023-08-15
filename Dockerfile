# Stage 1: Build the Spring Boot application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Build the final image
FROM openjdk:17.0.1-jdk-slim

# Copy the Spring Boot JAR from the build stage
COPY --from=build /app/target/tigital-0.0.1-SNAPSHOT.jar tigital.jar

# Expose ports
EXPOSE 8080

# Install PostgreSQL client (for database connections)
RUN apt-get update && apt-get install -y postgresql-client

# Set environment variables for database connection
ENV DB_HOST=${DB_HOST}
ENV DB_PORT=${DB_PORT}
ENV DB_NAME=${DB_NAME}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}

# Entrypoint to start the application
ENTRYPOINT [ "java","-jar","tigital.jar" ]
