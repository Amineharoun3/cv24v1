package fr.univrouen.cv24.util;

//Création de la classe Fichier dans le package util
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fichier {
 public static String loadFileXML() {
     ResourceLoader resourceLoader = new DefaultResourceLoader();
     Resource resource = resourceLoader.getResource("classpath:xml/smallCV.xml");
     
     StringBuilder content = new StringBuilder();
     
     try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
         String line;
         while ((line = reader.readLine()) != null) {
             content.append(line).append("\n");
         }
     } catch (IOException e) {
         return "Erreur lors de la lecture du fichier : " + e.getMessage();
     }
     
     return content.toString();
 }
}
