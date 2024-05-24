package fr.univrouen.cv24.controller;

import fr.univrouen.cv24.repositories.dao;
import fr.univrouen.cv24.repositories.Cv24Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cv24")
public class ViewController {

    @Autowired
    private Cv24Repository cv24Repository;

    @GetMapping("/xml")
    @ResponseBody
    public ResponseEntity<?> fetchCVInXMLFormat(@RequestParam("id") Long id) {
        dao cv = cv24Repository.findCVById(id);
        if (cv != null) {
            return ResponseEntity.ok(cv);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("id", String.valueOf(id));
            error.put("status", "ERROR");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

    @GetMapping("/html")
    @ResponseBody
    public String getCVAsHTML(@RequestParam("id") Long id, Model model) {
        dao cv = cv24Repository.findCVById(id);
        if (cv != null) {
            return generateHTMLForCV(cv);
        } else {
            return generateErrorHTML(id);
        }
    }

    private String generateHTMLForCV(dao cv) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("<meta charset=\"UTF-8\">\n")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                .append("<title>Détails du CV</title>\n")
                .append("<style>\n")
                .append("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }\n")
                .append(".cv-card { background-color: #fff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); padding: 20px; margin: 20px auto; width: fit-content; }\n")
                .append("h1 { color: #333; }\n")
                .append("p { margin: 10px 0; }\n")
                .append("</style>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<div class='cv-card'>\n")
                .append("<h1>Détails du CV</h1>\n")
                .append("<p><strong>ID:</strong> ").append(cv.getId()).append("</p>\n")
                .append("<p><strong>Genre:</strong> ").append(cv.getGenre()).append("</p>\n")
                .append("<p><strong>Prénom:</strong> ").append(cv.getPrenom()).append("</p>\n")
                .append("<p><strong>Nom:</strong> ").append(cv.getNom()).append("</p>\n")
                .append("<p><strong>Date de naissance:</strong> ").append(cv.getDateNaissance()).append("</p>\n")
                .append("<p><strong>Objectif:</strong> ").append(cv.getObjectif()).append("</p>\n")
                .append("<p><strong>Diplôme:</strong> ").append(cv.getDiplome()).append("</p>\n")
                .append("<p><strong>Poste recherché:</strong> ").append(cv.getPosteRecherche()).append("</p>\n")
                .append("<p><strong>Certificats:</strong> ").append(cv.getCertificats()).append("</p>\n")
                .append("</div>\n")
                .append("</body>\n")
                .append("</html>\n");
        return htmlBuilder.toString();
    }

    private String generateErrorHTML(Long id) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("<title>Error</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h1>Erreur</h1>\n")
                .append("<p>Le CV avec l'ID ").append(id).append(" n'a pas été trouvé.</p>\n")
                .append("</body>\n")
                .append("</html>\n");
        return htmlBuilder.toString();
    }

}
