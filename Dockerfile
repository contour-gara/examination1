FROM amazoncorretto:17 AS build
WORKDIR /build
COPY ./ ./
RUN ./mvnw clean package -Dmaven.test.skip=true

FROM amazoncorretto:17-alpine
COPY --from=build /build/examination1-app-server/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
