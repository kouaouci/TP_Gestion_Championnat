package com.Championnat.TP_Gestion_Championnat.Service;

import com.Championnat.TP_Gestion_Championnat.pojos.*;

import java.util.List;

public interface EquipeService {

    Equipe ajouterEquipe(Equipe equipe);
    Equipe recupererEquipe(Long idEquipe);
    void updateEquipe(Equipe equipe);

    Equipe recupererEquipe(String nom);

    List<Equipe> recupererEquipeAll();
}
