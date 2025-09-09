# Imagem base com Java 24
FROM eclipse-temurin:24-jdk-alpine

# Diretório dentro do container
WORKDIR /app

# Copia o JAR da aplicação para o container
COPY target/investment-0.0.1-SNAPSHOT.jar app.jar
# ou, se Maven: COPY target/my-spring-app.jar app.jar

# Expõe a porta do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
