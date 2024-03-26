package com.Championnat.TP_Gestion_Championnat.Service;

import com.Championnat.TP_Gestion_Championnat.pojos.Championnat;
import com.Championnat.TP_Gestion_Championnat.pojos.Journee;


import java.util.List;

public interface ChampionnatService {
    Championnat ajouterChampionnat(Championnat championnat);
    Championnat recupererChampionnat(Long idChampionnat);
 List<Championnat> recupererChampionnatAll();
 List<Journee> recupererJourneeAll(Championnat championnat);
}
