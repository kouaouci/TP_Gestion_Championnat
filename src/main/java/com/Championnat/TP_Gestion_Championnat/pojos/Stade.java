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
    private int capacité;

    //construccteur
    public Stade() {
    }
    public Stade(String nom, String adresse,  int capacité, String telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.capacité = capacité;
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
    public int getCapacité() {
        return capacité;

    }
    public void setCapacité(int capacité) {
        this.capacité = capacité;

    }
        //relation exterieur
    @OneToMany(mappedBy = "stade", cascade = CascadeType.ALL)
    private List<Matchs> matches;
    @OneToMany(mappedBy = "stade")
    private List<Equipe> equipes;
}
