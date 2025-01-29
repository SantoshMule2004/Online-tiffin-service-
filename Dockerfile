FROM maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/online-tiffin-service-0.0.1-SNAPSHOT.jar online-tiffin-service.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "online-tiffin-service.jar" ]