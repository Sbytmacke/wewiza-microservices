# Utiliza una imagen base con el entorno de compilación de Kotlin/Java
FROM gradle:7-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
# Directorio de trabajo en el contenedor
WORKDIR /home/gradle/src
RUN gradle buildJar --no-daemon

FROM openjdk:17
# Habrá que exponer puertos para la base de datos mas adelante
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/wewiza.jar /app/wewiza.jar
ENTRYPOINT ["java", "-jar", "/app/wewiza.jar"]
