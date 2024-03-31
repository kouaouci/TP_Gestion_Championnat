package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Entity
@Transactional
public class Journee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;



    public Journee() {
    }

    public Championnat getChampionnat() {
        return championnat;
    }

    public void setChampionnat(Championnat championnat) {
        this.championnat = championnat;
    }

    public Journee(Integer numero) {
        this.numero = numero;
    }
    @ManyToOne
    private Championnat championnat;
    @OneToMany(mappedBy="journee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Matchs> matches;

    public Long getId() {
        return id;
    }

    public List<Matchs> getMatches() {
        return matches;
    }

    public void setMatches(List<Matchs> matches) {
        this.matches = matches;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Journee{");
        sb.append("id=").append(id);
        sb.append(", numero=").append(numero);
        sb.append(", championnat=").append(championnat);
        sb.append(", matches=").append(matches);
        sb.append('}');
        return  sb.toString();
    }

}
