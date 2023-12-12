FROM maven:3.9.2-openjdk-17 AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean package



FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY --from=build /app/target/TFG-0.0.1-SNAPSHOT.jar /app/tfg-api.jar
ENTRYPOINT ["java", "-jar", "tfg-api.jar"]
