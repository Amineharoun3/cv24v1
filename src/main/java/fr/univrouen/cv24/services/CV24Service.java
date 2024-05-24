package fr.univrouen.cv24.services;

import fr.univrouen.cv24.repositories.dao;
import fr.univrouen.cv24.repositories.Cv24Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CV24Service {
    private final Cv24Repository cv24Repository;

    public CV24Service(Cv24Repository cv24Repository) {
        this.cv24Repository = cv24Repository;
    }

    public dao addNewCV(dao cv){
        if(cv24Exists(cv)){
            throw new IllegalArgumentException("Valeur invalide");
        }
        return cv24Repository.save(cv);
    }

    public void deleteCVbyId(Integer id){
        cv24Repository.deleteById(id);
    }

    public dao updateCV(Integer id, dao updatedCV) {
        return cv24Repository.findById(id)
                .map(existingCV -> updateExistingCV(existingCV, updatedCV))
                .orElse(null);
    }

    public List<dao> getCVs(){
        return cv24Repository.findAll();
    }

    private boolean cv24Exists(dao cv) {
        return cv24Repository.existsByGenreAndNomAndPrenomAndPhone(cv.getGenre(), cv.getNom(), cv.getPrenom(), cv.getPhone());
    }

    private dao updateExistingCV(dao existingCV, dao updatedCV) {
        existingCV.setGenre(updatedCV.getGenre());
        existingCV.setNom(updatedCV.getNom());
        existingCV.setPrenom(updatedCV.getPrenom());
        return cv24Repository.save(existingCV);
    }
}
