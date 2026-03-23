# Importing JDK and copying required files
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src src

COPY mvnw .
COPY .mvn .mvn

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:25-jre
VOLUME /tmp

COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080