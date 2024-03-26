package com.Championnat.TP_Gestion_Championnat.Service;

import com.Championnat.TP_Gestion_Championnat.pojos.*;

import java.util.List;

public interface MatchService {
    Matchs ajouterMatch(Matchs match);
    Matchs recupererMatch(Long idMatch);
    List<Matchs> recupererMatchAll();


}
