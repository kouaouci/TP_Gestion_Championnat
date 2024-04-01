package com.Championnat.TP_Gestion_Championnat.Service.Impl;

import com.Championnat.TP_Gestion_Championnat.Service.EquipeService;
import com.Championnat.TP_Gestion_Championnat.dao.EquipeDao;
import com.Championnat.TP_Gestion_Championnat.dao.MatchDao;
import com.Championnat.TP_Gestion_Championnat.pojos.Equipe;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service

public class EquipeServiceImpl implements EquipeService {
    @Autowired
    private EquipeDao equipeDao;


    @Autowired
    private MatchDao matchDao;
    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return equipeDao.save(equipe);
    }

    @Override
    public Equipe recupererEquipe(Long idEquipe) {
        return equipeDao.findById(idEquipe).orElse(null);
    }

    @Override
    public Equipe recupererEquipe(String nom) {
        return null;
    }

    @Override
    public void updateEquipe(Equipe equipeModifiee) {
        Equipe equipeExistante = equipeDao.findById(equipeModifiee.getId()).orElse(null);
        equipeExistante.setNom(equipeModifiee.getNom());
        equipeExistante.setNomEntrainneur(equipeModifiee.getNomEntrainneur());
        equipeExistante.setPresident(equipeModifiee.getPresident());
        equipeExistante.setSiege(equipeModifiee.getSiege());
        equipeExistante.setTelephone(equipeModifiee.getTelephone());
        equipeExistante.setSiteWeb(equipeModifiee.getSiteWeb());
        equipeExistante.setDateCreation(equipeModifiee.getDateCreation());
        equipeExistante.setStade(equipeModifiee.getStade());
        equipeExistante.setStatus(equipeModifiee.getStatus());
        equipeExistante.setLogo(equipeModifiee.getLogo());

        equipeDao.save(equipeExistante);
    }
    @Override
    public List<Equipe> recupererEquipeAll() {
        return equipeDao.findAll();
    }
}
