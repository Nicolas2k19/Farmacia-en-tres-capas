FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el resto del código fuente y construir el JAR
COPY src ./src
RUN mvn package -DskipTests

# Segunda etapa: imagen final
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]