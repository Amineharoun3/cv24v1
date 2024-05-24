package fr.univrouen.cv24.repositories;

import fr.univrouen.cv24.dao.CV24;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Cv24Repository extends JpaRepository<CV24, Integer> {
    CV24 findCVById(Long id);
    boolean existsByGenreAndNomAndPrenomAndPhone(String genre, String nom, String prenom, String phone);
}
