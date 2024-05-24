package fr.univrouen.cv24.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String index() {
        // Construction du contenu HTML
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<meta charset=\"UTF-8\">");
        htmlBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        htmlBuilder.append("<title>Page d'accueil htlk</title>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>Bienvenue sur Projet XML</h1>");
        htmlBuilder.append("<p>Version 1</p>");
        htmlBuilder.append("<h2>Membres de l'équipe :</h2>");
        htmlBuilder.append("<ul>");
        htmlBuilder.append("<li>Mohamed EL habib Diarra </li>");
        htmlBuilder.append("<li>Amine Haroun Bachar</li>");
        htmlBuilder.append("</ul>");
        htmlBuilder.append("<img src=\"/universite-rouen.jpg\" alt=\"Logo de l'Université de Rouen\">");
        htmlBuilder.append("<p>Université de Rouen - UFR Sciences et Techniques</p>");
        htmlBuilder.append("<p>Adresse : 123 Rue du Campus, 76000 Rouen, France</p>");
        htmlBuilder.append("<p>Téléphone : +33 60 5 98 90 87</p>");
        htmlBuilder.append("<p>Email : bachar.amine-haroun@univ-rouen.fr mohamed.diarra@univ-rouen.fr");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }
}
