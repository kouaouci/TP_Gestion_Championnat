package com.Championnat.TP_Gestion_Championnat.Service;

import com.Championnat.TP_Gestion_Championnat.pojos.User;

import java.util.List;

public interface UserService {
    User ajouterUser(User user);
    User recupererUser(Long idUser);
    List<User> recupererUserAll();

}
