# Etapa 1: Construcción (Build)
FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

# Copiamos el wrapper de Gradle y los archivos de configuración
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN apt-get update && apt-get install -y dos2unix && dos2unix gradlew && chmod +x gradlew

RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew bootJar --no-daemon -x test

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "app.jar"]
