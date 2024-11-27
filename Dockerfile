# Usa la imagen oficial de Maven para construir la aplicación
FROM maven:3-openjdk-17-slim AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos necesarios para construir el proyecto
COPY pom.xml /app/
COPY src/ /app/src/

# Ejecuta el comando Maven para compilar y empaquetar la aplicación
RUN mvn clean install

# Expón el puerto en el que se va a ejecutar la aplicación
EXPOSE 8081

# Comando para ejecutar la aplicación Java
CMD mvn compile exec:java -Dexec.mainClass="com.h2.h2_api.H2ApiApplication"
