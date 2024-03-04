FROM amazoncorretto:17.0.10-al2023 AS build
WORKDIR /build
COPY ./ ./
RUN ./mvnw clean package -Dmaven.test.skip=true
RUN dnf install -y unzip
RUN unzip -q examination1-app-server/target/examination1-app-server-0.0.1-SNAPSHOT.jar
RUN jdeps \
    --ignore-missing-deps \
    --multi-release 17 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    examination1-app-server/target/examination1-app-server-0.0.1-SNAPSHOT.jar > jre-deps.info

RUN jlink \
    --verbose \
    --compress=2 \
    --strip-java-debug-attributes \
    --no-header-files \
    --no-man-pages \
    --add-modules $(cat jre-deps.info) \
    --output jre-min

FROM amazonlinux:2023.3.20240219.0
COPY --from=build /build/examination1-app-server/target/*.jar app.jar
COPY --from=build /build/jre-min /opt/jre-min
ENV PATH /opt/jre-min/bin:$PATH
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=render", "app.jar"]
