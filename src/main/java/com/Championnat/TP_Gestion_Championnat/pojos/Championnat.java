package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

@Entity
public class Championnat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    private String logo;
    private Date dateDebut;
    private Date dateFin;
    private Integer pointGagner;
    private Integer pointPerdu;
    private Integer pointNul;
    private String typeClassement;

    public Championnat() {
    }
    public Championnat(String nom, String logo, Date dateDebut, Date dateFin, Integer pointGagner, Integer pointPerdu, Integer pointNul, String typeClassement) {
        this.nom = nom;
        this.logo = logo;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.pointGagner = pointGagner;
        this.pointPerdu = pointPerdu;
        this.pointNul = pointNul;
        this.typeClassement = typeClassement;
    }
    //relation
    @ManyToOne
    private Pays pays;
    @ManyToMany//(mappedBy = "championnat")

    private List<Equipe> equipes;
    @OneToMany(mappedBy="championnat")
    private List<Journee> journees;


    // Getter et Setter
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
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public Integer getPointGagner() {
        return pointGagner;
    }
    public void setPointGagner(Integer pointGagner) {
        this.pointGagner = pointGagner;
    }
    public Integer getPointPerdu() {
        return pointPerdu;
    }
    public void setPointPerdu(Integer pointPerdu) {
        this.pointPerdu = pointPerdu;
    }
    public Integer getPointNul() {
        return pointNul;
    }
    public void setPointNul(Integer pointNul) {
        this.pointNul = pointNul;
    }
    public String getTypeClassement() {
        return typeClassement;
    }
    public void setTypeClassement(String typeClassement) {
        this.typeClassement = typeClassement;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public void setJournees(List<Journee> journees) {
        this.journees = journees;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;

    }

    public List<Journee> getJournees() {
        return journees;
    }

}
