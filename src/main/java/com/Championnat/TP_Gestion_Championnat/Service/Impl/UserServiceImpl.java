package com.Championnat.TP_Gestion_Championnat.Service.Impl;

import com.Championnat.TP_Gestion_Championnat.Service.UserService;
import com.Championnat.TP_Gestion_Championnat.dao.UserDao;
import com.Championnat.TP_Gestion_Championnat.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User ajouterUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User recupererUser(Long idUser) {
        return userDao.findById(idUser).orElse(null);
    }

    @Override
    public List<User> recupererUserAll() {
        return userDao.findAll();
    }
}
