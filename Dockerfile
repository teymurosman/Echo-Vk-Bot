FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests
ENV VK_API_TOKEN=""
ENV VK_API_VERSION=""
ENV VK_API_SECRET=""
ENV VK_API_CONFIRMATION=""
COPY target/vk-echo-bot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080