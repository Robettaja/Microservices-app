FROM maven:3.9-eclipse-temurin-25-alpine AS build
WORKDIR /build

COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src
RUN mvn -B -q package -DskipTests \
    dependency:copy-dependencies -DincludeScope=runtime -DoutputDirectory=target/lib

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

RUN addgroup -S app && adduser -S app -G app

COPY --from=build /build/target/lib ./lib
COPY --from=build /build/target/url-redirect-1.0-SNAPSHOT.jar ./app.jar

USER app
EXPOSE 7777

ENTRYPOINT ["java", "-cp", "app.jar:lib/*", "me.hideri.redirect.Main"]
