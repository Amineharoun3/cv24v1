package fr.univrouen.cv24.repositories;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "cv24")
@Table(name = "cv24")
public class dao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String genre;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String posteRecherche;
    private String certificats;
    private String phone;
    private String mail;
    private String objectif;
    private String diplome;


    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "dateNaissance")
    public String getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    @XmlElement(name = "posteRecherche")
    public String getPosteRecherche() {
        return posteRecherche;
    }

    public void setPosteRecherche(String posteRecherche) {
        this.posteRecherche = posteRecherche;
    }

    @XmlElement(name = "certificats")
    public String getCertificats() {
        return certificats;
    }

    public void setCertificats(String certificats) {
        this.certificats = certificats;
    }
    @XmlElement(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement(name = "prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @XmlElement(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @XmlElement(name = "objectif")
    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    @XmlElement(name = "diplome")
    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }
}
