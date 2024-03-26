package com.Championnat.TP_Gestion_Championnat.Service.Impl;

import com.Championnat.TP_Gestion_Championnat.Service.JourneeService;
import com.Championnat.TP_Gestion_Championnat.dao.JourneeDao;
import com.Championnat.TP_Gestion_Championnat.dao.MatchDao;
import com.Championnat.TP_Gestion_Championnat.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Service annotation

public class JourneeServiceImpl  implements JourneeService {
    // JourneeServiceImpl class
    @Autowired
    private JourneeDao journeeDao;
    @Autowired
    private MatchDao matchDao;


    @Override
    public Journee ajouterJournee(Journee journee) {
        return journeeDao.save(journee);
    }

    @Override
    public Journee recupererJournee(Long idJournee) {
        return journeeDao.findById(idJournee).orElse(null);
    }

    @Override
    public List<Journee> recupererJourneeAll() {
        return journeeDao.findAll();
    }

    @Override
    public List<Matchs> recupererMatchAll(Journee journee) {
        return null;
    }
}
