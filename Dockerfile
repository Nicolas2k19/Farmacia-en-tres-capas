FROM eclipse-temurin:17-jdk AS build

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml al directorio de trabajo
COPY pom.xml .

# Copia todo el contenido del directorio actual al directorio de trabajo
COPY . .

# Da permisos de ejecución al script mvnw
RUN chmod +x mvnw

# Empaqueta la aplicación utilizando Maven
RUN ./mvnw package -DskipTests

# Expone el puerto 8080
EXPOSE 8080

# Define el comando por defecto para ejecutar la aplicación
CMD ["java", "-jar", "target/app-0.0.1-SNAPSHOT.jar"]