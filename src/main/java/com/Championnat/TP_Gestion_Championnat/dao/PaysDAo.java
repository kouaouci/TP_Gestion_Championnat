package com.Championnat.TP_Gestion_Championnat.dao;

import com.Championnat.TP_Gestion_Championnat.pojos.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysDAo extends JpaRepository<Pays, Long> {

}
