package com.Championnat.TP_Gestion_Championnat.Service;

import com.Championnat.TP_Gestion_Championnat.pojos.*;

import java.util.List;

public interface StadeService {
    Stade ajouterStade(Stade stade);
    Stade recupererStade(Long idStade);
    List<Stade> recupererStadeAll();

    List<Equipe> recupererEquipeAll(Stade stade);
    List<Matchs> recupererMatchAll(Stade stade);
}
