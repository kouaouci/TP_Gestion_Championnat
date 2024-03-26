package com.Championnat.TP_Gestion_Championnat.Service;

import com.Championnat.TP_Gestion_Championnat.pojos.Championnat;
import com.Championnat.TP_Gestion_Championnat.pojos.Pays;

import java.util.List;

public interface PaysService {
    Pays ajouterPays(Pays pays);
    Pays recupererPays(Long idPays);
    List<Pays> recupererPaysAll();
    List<Championnat> recupererChampionnats(Pays pays);


}
