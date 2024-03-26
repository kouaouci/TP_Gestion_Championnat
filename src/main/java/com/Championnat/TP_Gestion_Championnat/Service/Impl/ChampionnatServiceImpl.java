package com.Championnat.TP_Gestion_Championnat.Service.Impl;

import com.Championnat.TP_Gestion_Championnat.Service.ChampionnatService;
import com.Championnat.TP_Gestion_Championnat.dao.ChampionnatDao;
import com.Championnat.TP_Gestion_Championnat.dao.EquipeDao;
import com.Championnat.TP_Gestion_Championnat.dao.JourneeDao;
import com.Championnat.TP_Gestion_Championnat.pojos.Championnat;
import com.Championnat.TP_Gestion_Championnat.pojos.Equipe;
import com.Championnat.TP_Gestion_Championnat.pojos.Journee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChampionnatServiceImpl  implements ChampionnatService {
    @Autowired
    private ChampionnatDao championnatDao;
//    @Autowired
//    private EquipeDao equipeDao;
    @Autowired
    private JourneeDao journeeDao;


    @Override
    public Championnat ajouterChampionnat(Championnat championnat) {
        return championnatDao.save(championnat);

    }

    @Override
    public Championnat recupererChampionnat(Long idChampionnat) {
        return
                championnatDao.findById(idChampionnat).orElse(null);
    }
    @Override
    public List<Championnat> recupererChampionnatAll() {
        return championnatDao.findAll();
    }
    @Override
    public List<Journee> recupererJourneeAll(Championnat championnat) {
        return journeeDao.findByChampionnat(championnat);
    }
}
