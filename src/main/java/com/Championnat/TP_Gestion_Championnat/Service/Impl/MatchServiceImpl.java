package com.Championnat.TP_Gestion_Championnat.Service.Impl;

import com.Championnat.TP_Gestion_Championnat.Service.MatchService;
import com.Championnat.TP_Gestion_Championnat.dao.MatchDao;
import com.Championnat.TP_Gestion_Championnat.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl  implements MatchService {
    @Autowired
    private MatchDao matchDao;


    @Override
    public Matchs ajouterMatch(Matchs match) {
        return  matchDao.save(match);
    }

    @Override
    public Matchs recupererMatch(Long idMatch) {
        return matchDao.findById(idMatch).orElse(null);
    }

    @Override
    public List<Matchs> recupererMatchAll() {
        return matchDao.findAll();
    }
}
