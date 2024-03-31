package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Entity
@Transactional
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String logo;
    @OneToMany(mappedBy = "pays")
   private List<Championnat> championnats;

    public Pays() {
    }
    public Pays(String nom, String logo) {
        this.nom = nom;
        this.logo = logo;
    }
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

    public List<Championnat> getChampionats() {
        return championnats;
    }

    public void setChampionats(List<Championnat> championnats) {
        this.championnats = championnats;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pays{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", logo='").append(logo).append('\'');
        sb.append(", championats=").append(championnats);
        sb.append('}');
        return sb.toString();
    }

}
