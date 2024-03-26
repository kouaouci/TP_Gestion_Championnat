package com.Championnat.TP_Gestion_Championnat.Service.Impl;

import com.Championnat.TP_Gestion_Championnat.Service.PaysService;
import com.Championnat.TP_Gestion_Championnat.dao.ChampionnatDao;
import com.Championnat.TP_Gestion_Championnat.dao.PaysDAo;
import com.Championnat.TP_Gestion_Championnat.pojos.Championnat;
import com.Championnat.TP_Gestion_Championnat.pojos.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaysServiceImpl  implements PaysService {
    @Autowired
    private PaysDAo paysDao;
    @Autowired
private ChampionnatDao championnatDao;


    @Override
    public Pays ajouterPays(Pays pays) {
        return paysDao.save(pays);
    }

    @Override
    public Pays recupererPays(Long idPays) {
        return paysDao.findById(idPays).orElse(null);
    }

    @Override
    public List<Pays> recupererPaysAll() {
        return paysDao.findAll();
    }

    @Override
    public List<Championnat> recupererChampionnats(Pays pays) {
        return  championnatDao.findByPays(pays);
    }
}
