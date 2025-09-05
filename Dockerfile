FROM maven:3.9.6-eclipse-temurin-21

# Set working directory
WORKDIR /app

# Install dependencies
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Copy project
COPY . .

# Install Maven dependencies
RUN mvn clean install -DskipTests

# Command to run tests
CMD ["mvn", "clean", "test"]