package com.Championnat.TP_Gestion_Championnat.Service.Impl;

import com.Championnat.TP_Gestion_Championnat.Service.StadeService;
import com.Championnat.TP_Gestion_Championnat.dao.EquipeDao;
import com.Championnat.TP_Gestion_Championnat.dao.MatchDao;
import com.Championnat.TP_Gestion_Championnat.dao.StadeDao;
import com.Championnat.TP_Gestion_Championnat.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadeServiceImpl implements StadeService {
    @Autowired
    private StadeDao stadeDao;
    @Autowired
    private EquipeDao equipeDao;
    @Autowired
    private MatchDao matchDao;
    @Override
    public Stade ajouterStade(Stade stade) {
        return stadeDao.save(stade);
    }

    @Override
    public Stade recupererStade(Long idStade) {
        return stadeDao.findById(idStade).orElse(null);
    }

    @Override
    public List<Stade> recupererStadeAll() {
        return stadeDao.findAll();
    }

    @Override
    public List<Equipe> recupererEquipeAll(Stade stade) {
        return equipeDao.findByStade(stade);
    }

    @Override
    public List<Matchs> recupererMatchAll(Stade stade) {
        return matchDao.findByStade(stade);
    }
}
