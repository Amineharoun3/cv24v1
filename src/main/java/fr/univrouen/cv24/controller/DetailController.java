package fr.univrouen.cv24.controller;

import fr.univrouen.cv24.dao.CV24;
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
public class DetailController {

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
    public String getCVAsHTML(@RequestParam("id") Long id, Model model) {
        CV24 cv = cv24Repository.findCVById(id);
        if (cv != null) {
            model.addAttribute("cv", cv);
            return "cvdetail";
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("id", String.valueOf(id));
            error.put("status", "ERROR");
            model.addAttribute("error", error);
            return "error";
        }
    }
}
