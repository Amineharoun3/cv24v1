package fr.univrouen.cv24.controller;

import fr.univrouen.cv24.repositories.CV24;
import fr.univrouen.cv24.services.CV24Service;
import fr.univrouen.cv24.repositories.Cv24Repository;
import fr.univrouen.cv24.services.XmlValidationService;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cv24")
public class CV24Controller{

    private final CV24Service service;
    private final Cv24Repository repository;
    private final XmlValidationService xmlValidationService;

    // Constructor
    public CV24Controller(CV24Service service, Cv24Repository repository,XmlValidationService xmlValidationService) {
        this.xmlValidationService = xmlValidationService;
        this.service = service;
        this.repository = repository;
    }

    // Method from CV24Controller to add a new CV
    @PostMapping("/insert_normal")
    public ResponseEntity<String> addNewCV(@RequestBody CV24 cv) {
        try {
            CV24 newCV = service.addNewCV(cv);
            String response = buildResponse(newCV);
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("<status>ERROR</status><detail>CV ALREADY EXISTS</detail>");
        }
    }
    @PostMapping("/insert")
    public ResponseEntity<String> addNewCV(@RequestParam("file") MultipartFile file) {
        try {
            if (!xmlValidationService.validateXml(new StreamSource(file.getInputStream()))) {
                return ResponseEntity.badRequest().body("Invalid XML according to XSD");
            }

            CV24 cv = convertXmlToCv24(file.getInputStream());

            if (service.addNewCV(cv) != null) {
                return ResponseEntity.ok("CV added successfully with ID: " + cv.getId());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add CV");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error reading XML file: " + e.getMessage());
        }
    }

    private CV24 convertXmlToCv24(InputStream xmlStream) {
        try {
            JAXBContext context = JAXBContext.newInstance(CV24.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<CV24> jaxbElement = unmarshaller.unmarshal(new StreamSource(xmlStream), CV24.class);
            return jaxbElement.getValue();
        } catch (JAXBException e) {
            throw new RuntimeException("Error during XML deserialization", e);
        }
    }



    // Method from CV24Controller to delete a CV by ID
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, Object> payload) {
        try {
            Integer id = ((Number) payload.get("id")).intValue();
            service.deleteCVbyId(id);
            String response = "<id>" + id + "</id><status>DELETED</status>";
            return ResponseEntity.ok().body(response);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("<status>ERROR</status><detail>THE ID PROVIDED IS INVALID</detail>");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("<status>ERROR</status>");
        }
    }

    // Method from CV24Controller to update an existing CV
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody CV24 updatedCV) {
        try {
            CV24 updated = service.updateCV(id, updatedCV);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<status>ERROR</status><detail>CV not found</detail>");
            }
            String response = "<id>" + id + "</id><status>UPDATED</status>";
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("<status>ERROR</status><detail>" + e.getMessage() + "</detail>");
        }
    }

    // Method from GetCVController to retrieve CVs as XML
    @GetMapping("/resume/xml")
    @ResponseBody
    public CVList getCVsAsXML() {
        List<CV24> cvs = repository.findAll();
        return new CVList(cvs);
    }

    // Method from GetCVController to retrieve CVs as HTML
    @GetMapping("/resume")
    @ResponseBody
    public String getCVsAsHTML() {
        List<CV24> cvs = repository.findAll();
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<!DOCTYPE html>")
                .append("<html><head><meta charset=\"UTF-8\">")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
                .append("<title>Visualisation des CV</title>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; background-color: #f0f0f0; padding: 20px; }")
                .append("h1 { text-align: center; }")
                .append("table { width: 100%; border-collapse: collapse; background-color: #fff; margin: 20px 0; }")
                .append("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }")
                .append("th { background-color: #f2f2f2; color: #333; }")
                .append("tr:nth-child(even) { background-color: #f9f9f9; }")
                .append("tr:hover { background-color: #f1f1f1; }")
                .append("</style></head><body>")
                .append("<h1>Liste des CV</h1>")
                .append("<table><thead><tr>")
                .append("<th>ID</th><th>Genre</th><th>Prénom</th><th>Nom</th>")
                .append("<th>Objectif</th><th>Diplôme</th>")
                .append("</tr></thead><tbody>");

        for (CV24 cv : cvs) {
            htmlBuilder.append("<tr>")
                    .append("<td>").append(cv.getId()).append("</td>")
                    .append("<td>").append(cv.getGenre()).append("</td>")
                    .append("<td>").append(cv.getPrenom()).append("</td>")
                    .append("<td>").append(cv.getNom()).append("</td>")
                    .append("<td>").append(cv.getObjectif()).append("</td>")
                    .append("<td>").append(cv.getDiplome()).append("</td>")
                    .append("</tr>");
        }

        htmlBuilder.append("</tbody></table></body></html>");
        return htmlBuilder.toString();
    }

    // Helper method to build response for a new CV
    private String buildResponse(CV24 newCV) {
        return "<id>" + newCV.getId() + "</id>"
                + "<genre>" + newCV.getGenre() + "</genre>"
                + "<firstName>" + newCV.getNom() + "</firstName>"
                + "<lastName>" + newCV.getPrenom() + "</lastName>"
                + "<status>CV_ADDED_SUCCESSFULLY</status>";
    }

    // Nested class to handle a list of CVs
    public static class CVList {
        private List<CV24> cvs;

        public CVList(List<CV24> cvs) {
            this.cvs = cvs;
        }

        public List<CV24> getCvs() {
            return cvs;
        }

        public void setCvs(List<CV24> cvs) {
            this.cvs = cvs;
        }
    }
}
