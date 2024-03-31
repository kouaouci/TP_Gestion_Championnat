package com.Championnat.TP_Gestion_Championnat.dao;

import com.Championnat.TP_Gestion_Championnat.pojos.Championnat;
import com.Championnat.TP_Gestion_Championnat.pojos.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionnatDao extends JpaRepository<Championnat, Long> {

    @Query("SELECT c FROM Championnat c JOIN FETCH c.equipes e JOIN FETCH c.journees j WHERE c.id = :id")
    Championnat findByIdWithEquipesAndJournees(@Param("id") Long id);
    List<Championnat> findByPays(Pays pays);
}
