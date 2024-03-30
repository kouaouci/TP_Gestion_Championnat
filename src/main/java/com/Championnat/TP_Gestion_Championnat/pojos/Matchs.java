package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;

@Entity
public class Matchs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer pointEquipe1;
    private Integer pointEquipe2;
    private Long idEquipe1;
    private Long idEquipe2;

    @ManyToOne
    private Journee journee;
    @ManyToOne
    private Stade stade;
    @ManyToOne
    private Equipe equipe1;
    @ManyToOne
    private Equipe equipe2;


    public Matchs() {
    }



    public Matchs(int pointEquipe1, int pointEquipe2, Journee journee, Stade stade, Equipe equipe1, Equipe equipe2) {
        this.pointEquipe1 = pointEquipe1;
        this.pointEquipe2 = pointEquipe2;
        this.journee = journee;
        this.stade = stade;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
    }
   // getter setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPointEquipe1() {
        return pointEquipe1;
    }

    public void setPointEquipe1(Integer pointEquipe1) {
        this.pointEquipe1 = pointEquipe1;
    }

    public Integer getPointEquipe2() {
        return pointEquipe2;
    }

    public void setPointEquipe2(Integer pointEquipe2) {
        this.pointEquipe2 = pointEquipe2;
    }

    public Long getIdEquipe1() {
        return idEquipe1;
    }

    public void setIdEquipe1(Long idEquipe1) {
        this.idEquipe1 = idEquipe1;
    }

    public Long getIdEquipe2() {
        return idEquipe2;
    }

    public void setIdEquipe2(Long idEquipe2) {
        this.idEquipe2 = idEquipe2;
    }

    public Journee getJournee() {
        return journee;
    }

    public void setJournee(Journee journee) {
        this.journee = journee;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }
}
