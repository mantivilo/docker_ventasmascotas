FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/ventasmascotas-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_BOU90JFANWR0B6WR /app/oracle_wallet
EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]