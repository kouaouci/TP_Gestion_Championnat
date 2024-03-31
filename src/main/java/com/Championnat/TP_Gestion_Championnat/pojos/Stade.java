package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
@Transactional
public class Stade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String telephone;
    private Integer capacite;
    //relation exterieur
    @OneToMany(mappedBy = "stade")
    private List<Matchs> matches;
    @OneToMany(mappedBy = "stade")
    private List<Equipe> equipes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public List<Matchs> getMatches() {
        return matches;
    }

    public void setMatches(List<Matchs> matches) {
        this.matches = matches;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    //construccteur
    public Stade() {
    }

    public Stade(String nom, String adresse, Integer capacite, String telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.capacite = capacite;
    }


    //getters and setters

}