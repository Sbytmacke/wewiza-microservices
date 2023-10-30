# Utiliza una imagen base con el entorno de compilación de Kotlin/Java
FROM gradle:7-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
# Directorio de trabajo en el contenedor
WORKDIR /home/gradle/src
RUN gradle buildJar --no-daemon

FROM openjdk:17
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/fake-store.jar /app/fakeStore.jar
ENTRYPOINT ["java", "-jar", "/app/fakeStore.jar"]
