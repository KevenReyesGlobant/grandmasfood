FROM openjdk:21

WORKDIR /app

COPY ./target/clients-0.0.1-SNAPSHOT.jar .

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "clients-0.0.1-SNAPSHOT.jar"]