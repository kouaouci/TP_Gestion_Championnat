package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
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

    public int getCapacite() {
        return capacite;

    }

    public void setCapacité(Integer capacite) {
        this.capacite = capacite;

    }

}