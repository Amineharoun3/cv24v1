package fr.univrouen.cv24.controller;

import fr.univrouen.cv24.repositories.CV24;
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
        CV24 cv = cv24Repository.findCVById(id);
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
    CV24 cv = cv24Repository.findCVById(id);
    if (cv != null) {
        return generateHTMLForCV(cv);
    } else {
        return generateErrorHTML(id);
    }
}

private String generateHTMLForCV(CV24 cv) {
    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("<!DOCTYPE html>\n");
    htmlBuilder.append("<html>\n");
    htmlBuilder.append("<head>\n");
    htmlBuilder.append("<meta charset=\"UTF-8\">\n");
    htmlBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
    htmlBuilder.append("<title>Details du CV </title>\n");
    htmlBuilder.append("</head>\n");
    htmlBuilder.append("<body>\n");
    htmlBuilder.append("<h1>CV Details</h1>\n");
    htmlBuilder.append("<div>\n");
    htmlBuilder.append("<p>Id: ").append(cv.getId()).append("</p>\n");
    htmlBuilder.append("<p>Genre: ").append(cv.getGenre()).append("</p>\n");
    htmlBuilder.append("<p>Prénom: ").append(cv.getPrenom()).append("</p>\n");
    htmlBuilder.append("<p>Nom: ").append(cv.getNom()).append("</p>\n");
   htmlBuilder.append("<p>Date naissance : ").append(cv.getDateNaissance()).append("</p>\n");
    htmlBuilder.append("<p>Objectif: ").append(cv.getObjectif()).append("</p>\n");
    htmlBuilder.append("<p>Diplôme: ").append(cv.getDiplome()).append("</p>\n");
htmlBuilder.append("<p>Poste recherhé: ").append(cv.getPosteRecherche()).append("</p>\n");
    htmlBuilder.append("<p>Certifiact: ").append(cv.getCertificats()).append("</p>\n");
    // Ajoutez d'autres champs si nécessaire
    htmlBuilder.append("</div>\n");
    htmlBuilder.append("</body>\n");
    htmlBuilder.append("</html>\n");
    return htmlBuilder.toString();
}

private String generateErrorHTML(Long id) {
    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("<!DOCTYPE html>\n");
    htmlBuilder.append("<html>\n");
    htmlBuilder.append("<head>\n");
    htmlBuilder.append("<title>Error</title>\n");
    htmlBuilder.append("</head>\n");
    htmlBuilder.append("<body>\n");
    htmlBuilder.append("<h1>Error</h1>\n");
    htmlBuilder.append("<p>CV with ID ").append(id).append(" not found.</p>\n");
    htmlBuilder.append("</body>\n");
    htmlBuilder.append("</html>\n");
    return htmlBuilder.toString();
}

}
