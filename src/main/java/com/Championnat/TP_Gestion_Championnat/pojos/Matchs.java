package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;

@Entity
public class Matchs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int pointEquipe1;
    private int pointEquipe2;
    private int idEquipe1;
    private int idEquipe2;
    //relation exterieur
//    @ManyToMany//(mappedBy = "match")
//    @JoinTable(name = "match_equipe",
//            joinColumns = @JoinColumn(name = "match_id"),
//            inverseJoinColumns = @JoinColumn(name = "equipe_id"))
//    private List<Equipe> equipe;
    @ManyToOne
    private Journee journee;
    @ManyToOne
    private Stade stade;
    @ManyToOne
    private Equipe equipe1;
    @ManyToOne
    private Equipe equipe2;


    public Matchs(int i, int i1, Stade stade1, Long id, Long equipe2Id, Journee journee1, Equipe equipe1, Equipe equipe2) {
    }



    public Matchs(int pointEquipe1, int pointEquipe2, Journee journee, Stade stade, Equipe equipe1, Equipe equipe2) {
        this.pointEquipe1 = pointEquipe1;
        this.pointEquipe2 = pointEquipe2;
        this.journee = journee;
        this.stade = stade;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getPointEquipe1() {
        return pointEquipe1;
    }

    public void setPointEquipe1(int pointEquipe1) {
        this.pointEquipe1 = pointEquipe1;
    }
    public int getPointEquipe2() {
        return pointEquipe2;
    }
    public void setPointEquipe2(int pointEquipe2) {
        this.pointEquipe2 = pointEquipe2;
    }
    public int getIdEquipe1() {
        return idEquipe1;
    }

    public void setIdEquipe1(int idEquipe1) {
        this.idEquipe1 = idEquipe1;
    }

    public int getIdEquipe2() {
        return idEquipe2;
    }

    public void setIdEquipe2(int idEquipe2) {
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
