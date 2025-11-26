# ============================
# 1) Build Stage
# ============================
FROM maven:3.9.11-eclipse-temurin-11-alpine AS build

WORKDIR /app

# Copy pom + src
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# ============================
# 2) Runtime Stage
# ============================
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

