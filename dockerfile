FROM maven:4.0.0-openjdk-17 AS build

# Establecer un directorio de trabajo
WORKDIR /tfg

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /tfg

# Ejecutar Maven para construir el proyecto
RUN mvn clean package



FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/TFG-0.0.1-SNAPSHOT.jar java-api.jar
ENTRYPOINT [ "java", "-jar", "java-api.jar" ]
