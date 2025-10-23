#imagen de maven para compilar el proyecto
FROM maven:3.9-eclipse-temurin AS build

WORKDIR /app

COPY . .

#conpilar el proyecto a un jar
RUN mvn clean package -DskipTests

#segunda etapa
FROM eclipse-temurin:17

COPY --from=build /app/target/**.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
