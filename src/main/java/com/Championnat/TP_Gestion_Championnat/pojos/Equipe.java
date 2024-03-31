package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Entity
@Transactional

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

    public Equipe( String logo, String nom, Date dateCreation, String status,Long idStade,String nomEntrainneur, String president, String siege, String telephone, String siteWeb) {

        this.logo = logo;
        this.nom = nom;
        this.dateCreation = dateCreation;
        this.nomEntrainneur = nomEntrainneur;
        this.president = president;
        this.siege = siege;
        this.telephone = telephone;
        this.siteWeb = siteWeb;
        this.idStade = idStade;
        this.status = status;
    }
    // relation exterieur
   @ManyToMany(mappedBy = "equipes",fetch = FetchType.EAGER)
    private List<Championnat> championnats;
   @ManyToOne
    private Stade stade;
   // getter et setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEntrainneur() {
        return nomEntrainneur;
    }

    public void setNomEntrainneur(String nomEntrainneur) {
        this.nomEntrainneur = nomEntrainneur;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getIdStade() {
        return idStade;
    }

    public void setIdStade(Long idStade) {
        this.idStade = idStade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Championnat> getChampionnats() {
        return championnats;
    }

    public void setChampionnats(List<Championnat> championnats) {
        this.championnats = championnats;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
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
