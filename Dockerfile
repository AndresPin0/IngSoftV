FROM amazoncorretto:17-alpine-jdk

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR generado por Maven desde el directorio de salida (target)
COPY outcome-curr-mgmt/target/outcome-curr-mgmt-1.0-SNAPSHOT.jar app.jar

# Exponer el puerto que usará la aplicación
EXPOSE 9092

# Establecer el comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
