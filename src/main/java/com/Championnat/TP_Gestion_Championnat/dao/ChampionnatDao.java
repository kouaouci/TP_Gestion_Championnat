package com.Championnat.TP_Gestion_Championnat.dao;

import com.Championnat.TP_Gestion_Championnat.pojos.Championnat;
import com.Championnat.TP_Gestion_Championnat.pojos.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionnatDao extends JpaRepository<Championnat, Long> {
//    List<Championnat> findChampionnatsByNom(String Nom);
//    List<Championnat> findChampionnatsByPays(Pays pays);

    List<Championnat> findByPays(Pays pays);
}
