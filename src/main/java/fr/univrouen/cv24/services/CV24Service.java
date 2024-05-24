package fr.univrouen.cv24.services;

import fr.univrouen.cv24.dao.CV24;
import fr.univrouen.cv24.repositories.Cv24Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CV24Service {
    private final Cv24Repository cv24Repository;

    public CV24Service(Cv24Repository cv24Repository) {
        this.cv24Repository = cv24Repository;
    }

    public CV24 addNewCV(CV24 cv){
        if(cv24Exists(cv)){
            throw new IllegalArgumentException("Valeur invalide");
        }
        return cv24Repository.save(cv);
    }

    public void deleteCVbyId(Integer id){
        cv24Repository.deleteById(id);
    }

    public CV24 updateCV(Integer id, CV24 updatedCV) {
        return cv24Repository.findById(id)
                .map(existingCV -> updateExistingCV(existingCV, updatedCV))
                .orElse(null);
    }

    public List<CV24> getCVs(){
        return cv24Repository.findAll();
    }

    private boolean cv24Exists(CV24 cv) {
        return cv24Repository.existsByGenreAndNomAndPrenomAndPhone(cv.getGenre(), cv.getNom(), cv.getPrenom(), cv.getPhone());
    }

    private CV24 updateExistingCV(CV24 existingCV, CV24 updatedCV) {
        existingCV.setGenre(updatedCV.getGenre());
        existingCV.setNom(updatedCV.getNom());
        existingCV.setPrenom(updatedCV.getPrenom());
        return cv24Repository.save(existingCV);
    }
}
