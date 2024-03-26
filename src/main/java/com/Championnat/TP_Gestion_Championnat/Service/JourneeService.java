package com.Championnat.TP_Gestion_Championnat.Service;

import com.Championnat.TP_Gestion_Championnat.pojos.*;

import java.util.List;

public interface JourneeService {
  Journee ajouterJournee(Journee journee);
    Journee recupererJournee(Long idJournee);
    List<Journee> recupererJourneeAll();
    List<Matchs> recupererMatchAll(Journee journee);
}
