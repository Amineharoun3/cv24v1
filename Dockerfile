# Utiliser l'image de base OpenJDK 21
FROM openjdk:21

# Copier le fichier JAR généré dans l'image
COPY target/cv24database.war /cv24database.jar

ENTRYPOINT ["java", "-jar", "cv24database.jar"]

# Exposer le port sur lequel l'application sera accessible
EXPOSE 8080
