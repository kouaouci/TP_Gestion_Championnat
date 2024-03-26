package com.Championnat.TP_Gestion_Championnat.dao;

import com.Championnat.TP_Gestion_Championnat.pojos.Equipe;
import com.Championnat.TP_Gestion_Championnat.pojos.Journee;
import com.Championnat.TP_Gestion_Championnat.pojos.Matchs;
import com.Championnat.TP_Gestion_Championnat.pojos.Stade;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchDao  extends JpaRepository<Matchs, Long> {
    List<Matchs> findByJournee(Journee journee);
    List<Matchs> findByStade(Stade stade);
}
