package com.Championnat.TP_Gestion_Championnat.pojos;

import jakarta.validation.constraints.NotBlank;

public class Connexion {
    @NotBlank(message = "Le champ login est obligatoire")
    private String Pseudo;
    @NotBlank(message = "Le champ mot de passe est obligatoire")
    private String mdp;
    public Connexion() {
    }
    public Connexion(String pseudo, String mdp) {
        this.Pseudo = pseudo;
        this.mdp = mdp;
    }
}
