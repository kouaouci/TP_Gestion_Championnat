package com.Championnat.TP_Gestion_Championnat.Service;

import com.Championnat.TP_Gestion_Championnat.pojos.*;

import java.util.List;

public interface EquipeService {
//    Equipe ajouterEquipe(Equipe equipe);
//    Equipe recupererEquipe(Long idEquipe);
//    Equipe recupererEquipe(String nom);
//    List<Equipe> recupererEquipes();
//    List<Matchs> recupererMatchs(Equipe equipe);
//    List<Stade> recupererStades(Equipe equipe);
//    List<Championnat> recupererChampionnats(Equipe equipe);
//
    Equipe ajouterEquipe(Equipe equipe);
    Equipe recupererEquipe(Long idEquipe);
    Equipe recupererEquipe(String nom);
    List<Equipe> recupererEquipeAll();
}
