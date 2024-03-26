package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity

public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nomEntrainneur;
    private String president;
    private String siege;
    private String telephone;
    private String siteWeb;
    private String nom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation;
    private Long idStade;
    private String status;
    private String logo;

    public Equipe() {
    }

    public Equipe( String logo, String nom, Date dateCreation, String nomEntrainneur, String president, String siege, String telephone, String siteWeb) {

        this.logo = logo;
        this.nom = nom;
        this.dateCreation = dateCreation;
        this.nomEntrainneur = nomEntrainneur;
        this.president = president;
        this.siege = siege;
        this.telephone = telephone;
        this.siteWeb = siteWeb;
    }
    // relation exterieur
   @ManyToMany(mappedBy = "equipes")
    private List<Championnat> championnats;
   @ManyToOne
    private Stade stade;

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
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
    @Override
    public String toString() {
       final StringBuilder sb = new StringBuilder("Equipe{");
         sb.append("id=").append(id);
            sb.append(", nom='").append(nom).append('\'');
            sb.append(", logo='").append(logo).append('\'');
            sb.append(", dateCreation=").append(dateCreation);
            sb.append(", idStade=").append(idStade);
            sb.append(", nomEntrainneur='").append(nomEntrainneur).append('\'');
            sb.append(", president='").append(president).append('\'');
            sb.append(", siege='").append(siege).append('\'');
            sb.append(", telephone='").append(telephone).append('\'');
            sb.append(", siteWeb='").append(siteWeb).append('\'');
            sb.append(", status='").append(status).append('\'');
            sb.append('}');
            return sb.toString();
    }


}
