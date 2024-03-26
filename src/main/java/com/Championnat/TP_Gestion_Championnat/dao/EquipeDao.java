package com.Championnat.TP_Gestion_Championnat.dao;

import com.Championnat.TP_Gestion_Championnat.pojos.Championnat;
import com.Championnat.TP_Gestion_Championnat.pojos.Equipe;
import com.Championnat.TP_Gestion_Championnat.pojos.Stade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeDao  extends JpaRepository<Equipe, Long> {

    List<Equipe> findByStade(Stade stade);


}
