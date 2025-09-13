# Etapa de build (usa Maven + JDK 24)
FROM maven:3.9-eclipse-temurin-24-alpine AS builder
WORKDIR /build

# Copia os arquivos do módulo (contexto do build é app/investment)
COPY app/investment/pom.xml .
RUN mvn -B -DskipTests dependency:go-offline
COPY app/investment/src ./src

# Compile/empacote (removi -q para ver logs se falhar)
RUN mvn -B -DskipTests clean package

# Etapa de runtime
FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY --from=builder build/target/*-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]