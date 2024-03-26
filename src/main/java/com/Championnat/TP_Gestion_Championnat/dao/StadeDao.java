package com.Championnat.TP_Gestion_Championnat.dao;

import com.Championnat.TP_Gestion_Championnat.pojos.Equipe;
import com.Championnat.TP_Gestion_Championnat.pojos.Stade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadeDao extends JpaRepository<Stade, Long> {

}
