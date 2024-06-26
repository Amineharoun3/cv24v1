package fr.univrouen.cv24.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Cv24Repository extends JpaRepository<dao, Integer> {
    dao findCVById(Long id);
    boolean existsByGenreAndNomAndPrenomAndPhone(String genre, String nom, String prenom, String phone);
}
