package fr.univrouen.cv24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelpController {

    @GetMapping("/help")
    @ResponseBody
    public String help() {
        return generateHTML();
    }
    private String generateHTML() {
        // Construction du contenu HTML
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html lang=\"fr\">");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<meta charset=\"UTF-8\">");
        htmlBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        htmlBuilder.append("<title>Aide</title>");
        htmlBuilder.append("<style>");
        htmlBuilder.append("body { font-family: Arial, sans-serif; background-color: #f0f0f0; padding: 20px; }");
        htmlBuilder.append(".container { display: flex; flex-wrap: wrap; gap: 20px; }");
        htmlBuilder.append(".card { background-color: #fff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px; width: 300px; }");
        htmlBuilder.append(".card h2 { margin-top: 0; }");
        htmlBuilder.append(".card p { margin: 5px 0; }");
        htmlBuilder.append("</style>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>Aide</h1>");
        htmlBuilder.append("<p>Voici les opérations gérées par le service REST :</p>");
        htmlBuilder.append("<div class=\"container\">");

        // Opération "/help"
        htmlBuilder.append("<div class=\"card\">");
        htmlBuilder.append("<h2>/help</h2>");
        htmlBuilder.append("<p><strong>Méthode:</strong> GET</p>");
        htmlBuilder.append("<p><strong>Opération:</strong> Affiche la page contenant les informations d’aide, format attendu, format de retour, ...</p>");
        htmlBuilder.append("</div>");

        // Opération "/cv24/resume"
        htmlBuilder.append("<div class=\"card\">");
        htmlBuilder.append("<h2>/cv24/resume</h2>");
        htmlBuilder.append("<p><strong>Méthode:</strong> GET</p>");
        htmlBuilder.append("<p><strong>Opération:</strong> Affiche la liste des CV au format HTML</p>");
        htmlBuilder.append("</div>");

        // Opération "/cv24/html?id"
        htmlBuilder.append("<div class=\"card\">");
        htmlBuilder.append("<h2>/cv24/html?id</h2>");
        htmlBuilder.append("<p><strong>Méthode:</strong> GET</p>");
        htmlBuilder.append("<p><strong>Opération:</strong> Affiche la page des CV au format XML</p>");
        htmlBuilder.append("</div>");

        // Opération "/cv24/insert"
        htmlBuilder.append("<div class=\"card\">");
        htmlBuilder.append("<h2>/cv24/insert</h2>");
        htmlBuilder.append("<p><strong>Méthode:</strong> POST</p>");
        htmlBuilder.append("<p><strong>Opération:</strong> Insère un CV donné dans la liste</p>");
        htmlBuilder.append("</div>");

        // Opération "/cv24/delete"
        htmlBuilder.append("<div class=\"card\">");
        htmlBuilder.append("<h2>/cv24/delete</h2>");
        htmlBuilder.append("<p><strong>Méthode:</strong> POST</p>");
        htmlBuilder.append("<p><strong>Opération:</strong> Supprime un CV de la liste des CV</p>");
        htmlBuilder.append("</div>");

        // Autres opérations similaires
        htmlBuilder.append("<div class=\"card\">");
        htmlBuilder.append("<h2>/cv24/xml?id</h2>");
        htmlBuilder.append("<p><strong>Méthode:</strong> GET</p>");
        htmlBuilder.append("<p><strong>Opération:</strong> Affiche un CV selon son identifiant id en HTML</p>");
        htmlBuilder.append("</div>");

        // Terminer le contenu HTML
        htmlBuilder.append("</div>"); // fin de container
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }
}
