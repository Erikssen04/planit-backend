FROM gradle:8.5.0-jdk21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build -x test --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
