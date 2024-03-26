package com.Championnat.TP_Gestion_Championnat.controllers;

import com.Championnat.TP_Gestion_Championnat.Service.*;
import com.Championnat.TP_Gestion_Championnat.pojos.*;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;
import java.util.*;

@Controller
public class BlogChampionnatController {

    @Autowired
    private UserService userService;
    @Autowired
    private PaysService paysService;
    @Autowired
    private ChampionnatService championnatService;
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private JourneeService journeeService;
    @Autowired
    private  MatchService matchService;
    @Autowired
    private StadeService stadeService;

    public BlogChampionnatController() {
    }

    public BlogChampionnatController(UserService userService, PaysService paysService, ChampionnatService championnatService, EquipeService equipeService, JourneeService journeeService, MatchService matchService, StadeService stadeService) {
        this.userService = userService;
        this.paysService = paysService;
        this.championnatService = championnatService;
        this.equipeService = equipeService;
        this.journeeService = journeeService;
        this.matchService = matchService;
        this.stadeService = stadeService;
    }

    @GetMapping({ "/","accueil"})
    public String accueil() {
        return "accueil";
    }

    @GetMapping({"/", "login"})
    public String login() {
        return "login";
    }





    @PostMapping({"logUser"})
    public RedirectView logUser(Model model, @RequestParam String uname, @RequestParam String psw) {
        List<User> users = userService.recupererUserAll();
        boolean connected = false;
        for (User userOfBdd : users) {
            if (Objects.equals(uname, userOfBdd.getLogin()) && Objects.equals(psw, userOfBdd.getMdp())) {
                connected = true;
                break;
            }
        }

        if (connected) {
            return new RedirectView("accueil");
        } else {
            return new RedirectView("");
        }
    }

    @GetMapping({"addUser("})
    public String addUser() {
        return "create";
    }

    @PostMapping({"saveUser"})
    public RedirectView saveUser(Model model, @RequestParam String uname, @RequestParam String psw) {
        User user = new User();
        user.setLogin(uname);
        user.setMdp(psw);
        userService.ajouterUser(user);
        return new RedirectView("/");

    }

    @GetMapping({"equipe/{idEquipe}/detail"})
    public String details_equipe(Model model, @PathVariable long idEquipe) {
        Equipe equipe = equipeService.recupererEquipe(idEquipe);
        List<Stade> stades = stadeService.recupererStadeAll();
        model.addAttribute("allStades", stades);

        model.addAttribute("equipe", equipe);

        return "details";
    }

    @PostMapping(value = "equipe/saveEquipe")
    public RedirectView saveEquipe(Model model, @Validated @ModelAttribute Equipe equipe, @RequestParam long stadeId) {
        Stade stade = stadeService.recupererStade(stadeId);
        equipe.setStade(stade);
        equipe = equipeService.ajouterEquipe(equipe);
        model.addAttribute("equipe", equipe);
        return new RedirectView("" + equipe.getId() + "/detail");
    }

    @GetMapping({"stade/{id}/detail"})
    public String detailStade(Model model, @PathVariable long id) {
        List<Stade> stadeList = stadeService.recupererStadeAll();
        Stade stade = stadeService.recupererStade(id);
        model.addAttribute("stade", stade);
        return "stadeDetail";
    }

    @GetMapping({"stade/liste"})
    public String stadeList(Model model) {
        List<Stade> stadeList = stadeService.recupererStadeAll();
        model.addAttribute("allStades", stadeList);
        return "stadeList";
    }

    @GetMapping({"stade/newStade"})
    public String newStade(Model model, @ModelAttribute Stade stade) {
        model.addAttribute("stade", stade);
        return "stadeDetail";
    }

    @PostMapping(value = "stade/saveStade")
    public RedirectView saveStade(Model model, @Validated @ModelAttribute Stade stade) {
        stade = stadeService.ajouterStade(stade);
        model.addAttribute("stade", stade);
        return new RedirectView("" + stade.getId() + "/detail");
    }

    //endregion
    //region Championnat
    @GetMapping({"details_championat"})
    public String details_championat(Model model, @RequestParam long idChampionat) {
        Championnat championat = championnatService.recupererChampionnat(idChampionat);
        List<Equipe> equipes = championat.getEquipes();
        List<Journee> journees = championat.getJournees();
        journees.sort(Comparator.comparing(Journee::getNumero).reversed());
        List<Matchs> matches = new ArrayList<>();
        List<EquipeChampionnat> equipeChampionatStats = new ArrayList<>();
        for (Journee journee : journees) {
            for (Matchs match : journee.getMatches()) {
                matches.add(match);
            }
        }
        for (Equipe equipe : equipes) {
            Integer totalPoint = 0;
            Integer matchGagnee = 0;
            Integer matchPerdu = 0;
            Integer matchNul = 0;
            Integer maxJournee = 0;
            for (Matchs match : matches) {
                if (equipe == match.getEquipe1()) {
                    totalPoint += match.getPointEquipe1();
                    if (match.getPointEquipe1() > match.getPointEquipe2()) {
                        matchGagnee++;
                    }
                    if (match.getPointEquipe1() < match.getPointEquipe2()) {
                        matchPerdu++;
                    }
                    if (match.getPointEquipe1() == match.getPointEquipe2()) {
                        matchNul++;
                    }
                    if (maxJournee < match.getJournee().getNumero()) {
                        maxJournee = match.getJournee().getNumero();
                    }
                }
                if (equipe == match.getEquipe2()) {
                    totalPoint += match.getPointEquipe2();
                    if (match.getPointEquipe1() > match.getPointEquipe2()) {
                        matchPerdu++;
                    }
                    if (match.getPointEquipe1() < match.getPointEquipe2()) {
                        matchGagnee++;
                    }
                    if (match.getPointEquipe1() == match.getPointEquipe2()) {
                        matchNul++;
                    }
                    if (maxJournee < match.getJournee().getNumero()) {
                        maxJournee = match.getJournee().getNumero();
                    }
                }
            }
            EquipeChampionnat equipeStats = new EquipeChampionnat(equipe, totalPoint, matchGagnee, matchPerdu, matchNul, maxJournee);
            equipeChampionatStats.add(equipeStats);
        }
        equipeChampionatStats.sort(Comparator.comparing(EquipeChampionnat::getTotalPoint).reversed());

        model.addAttribute("championat", championat);
        model.addAttribute("equipeChampionatStats", equipeChampionatStats);
        return "indexClassement";
    }

    @PostMapping({"championnat/{championnatId}/jours"})
    public RedirectView allMatchOfJourneeIdAndChampionnat(Model model, @RequestParam long championnatId, @RequestParam long journeeId) {
        return new RedirectView("/championnat/" + championnatId + "/jours/" + journeeId + "/resultatsListe");
    }

    @GetMapping({"/championnat/{championnatId}/jours/{journeeId}/resultatsListe"})
    public String listResultatsOfChampionnatAndJournee(Model model, @PathVariable long championnatId, @PathVariable long journeeId) {
        Championnat championat = championnatService.recupererChampionnat(championnatId);
        Journee journee = journeeService.recupererJournee(journeeId);
        HashMap<String, List<Matchs>> allMatchOfChampionnat = new HashMap<>();
        List<Journee> journees = championat.getJournees();

        List<Matchs> allMatchOfJournee = journee.getMatches();

        allMatchOfChampionnat.put(journee.getNumero().toString(), allMatchOfJournee);

        model.addAttribute("allJournees", journees);
        model.addAttribute("championnat", championat);
        model.addAttribute("allMatchForAllJournees", allMatchOfChampionnat);

        return "liste";
    }

    @GetMapping({"/championnat/{id}/resultatsListe"})
    public String listResultatsOfChampionnat(Model model, @PathVariable long id) {
        Equipe equipe = equipeService.recupererEquipe(id);
        Championnat championat = championnatService.recupererChampionnat(id);
        HashMap<String, List<Matchs>> allMatchOfChampionnat = new HashMap<>();

        List<Journee> journees = championat.getJournees();
        for (Journee journee : journees) {
            List<Matchs> allMatchOfJournee = journee.getMatches();

            allMatchOfChampionnat.put(journee.getNumero().toString(), allMatchOfJournee);
        }
        model.addAttribute("allJournees", journees);
        model.addAttribute("championnat", championat);
        model.addAttribute("allMatchForAllJournees", allMatchOfChampionnat);

        return "liste";
    }

    @GetMapping({"championnats"})
    public String championnats(Model model) {
        List<Championnat> championats = championnatService.recupererChampionnatAll();
        model.addAttribute("championnats", championats);
        return "indexListeRes";
    }

    @GetMapping({"championnat/newChampionnat"})
    public String newChampionnat(Model model, @ModelAttribute Championnat championat) {
        List<Pays> pays = paysService.recupererPaysAll();
        model.addAttribute("allPays", pays);
        model.addAttribute("championnat", championat);
        return "championnatDetail";
    }

    @PostMapping(value = "championnat/saveChampionnat")
    public RedirectView saveChampionnat(Model model, @Validated @ModelAttribute Championnat championat, @RequestParam long paysId) {
        Pays pays = paysService.recupererPays(paysId);
        championat.setPays(pays);
        championat = championnatService.ajouterChampionnat(championat);
        model.addAttribute("championnat", championat);
        return new RedirectView("championnat/" + championat.getId() + "/resultatsListe");
    }


    @PostConstruct
    private void init() {
        if (userService.recupererUserAll().isEmpty()) {
            User user1 = new User();
            user1.setNom("Delond");
            user1.setPrenom("Allin");
            user1.setLogin("allin.delond1");
            user1.setMdp("123456");
            user1.setPseudo("AllinD");
            user1.setEmail("allin.delong@gmail.com");
            userService.ajouterUser(user1);

            User user2 = new User();
            user2.setNom("Delond");
            user2.setPrenom("Allin");
            user2.setLogin("allin.delond2");
            user2.setMdp("123456");
            user2.setPseudo("AllinD");
            user2.setEmail("allin.delong@gmail.com");
            userService.ajouterUser(user2);

            Pays pays1 = new Pays();
            pays1.setNom("France");
            pays1.setLogo("logo_france.png");
            paysService.ajouterPays(pays1);

            Pays pays2 = new Pays();
            pays2.setNom("Allemagne");
            pays2.setLogo("logo_allemagne.png");
            paysService.ajouterPays(pays2);

            Pays pays3 = new Pays();
            pays3.setNom("Espagne");
            pays3.setLogo("logo_espagne.png");
            paysService.ajouterPays(pays3);

            Pays pays4 = new Pays();
            pays4.setNom("Italie");
            pays4.setLogo("logo_italie.png");
            paysService.ajouterPays(pays4);

            Championnat championat1 = new Championnat(
                    "Ligue 1",
                    "logo_ligue1.png",
                    Date.valueOf("2021-08-01"),
                    Date.valueOf("2022-05-30"),
                    3,
                    0,
                    1,
                    "Classement"
            );
            championat1.setPays(pays1);
            championnatService.ajouterChampionnat(championat1);

            Championnat championat2 = new Championnat(
                    "Bundesliga",
                    "logo_bundesliga.png",
                    Date.valueOf("2021-08-01"),
                    Date.valueOf("2022-05-30"),
                    3,
                    0,
                    1,
                    "pool"
            );
            championat2.setPays(pays2);
            championnatService.ajouterChampionnat(championat2);

            Championnat championat3 = new Championnat(
                    "La Liga",
                    "logo_laliga.png",
                    Date.valueOf("2021-08-01"),
                    Date.valueOf("2022-05-30"),
                    3,
                    0,
                    1,
                    "pool"
            );
            championat3.setPays(pays3);
            championnatService.ajouterChampionnat(championat3);

            Championnat championat4 = new Championnat(
                    "Serie A",
                    "logo_seriea.png",
                    Date.valueOf("2021-08-01"),
                    Date.valueOf("2022-05-30"),
                    3,
                    0,
                    1,
                    "Classement"
            );
            championat4.setPays(pays4);
            championnatService.ajouterChampionnat(championat4);

            Stade stade1 = new Stade(
                    "Groupama Stadium",
                    "5 rue perdu",
                    1500,
                    "0411223344"
            );
            stadeService.ajouterStade(stade1);

            Stade stade2 = new Stade(
                    "Italia Stadium",
                    "10 rue d'italie",
                    1200,
                    "0888888888"
            );
            stadeService.ajouterStade(stade2);

            Stade stade3 = new Stade(
                    "Allianz Arena",
                    "15 rue d'allemagne",
                    2000,
                    "0999999999"
            );
            stadeService.ajouterStade(stade3);

            Stade stade4 = new Stade(
                    "Parc des Princes",
                    "20 rue de paris",
                    1000,
                    "0666666666"
            );
            stadeService.ajouterStade(stade4);

            Equipe equipe1 = new Equipe(
                    "logo_psg.png",
                    "Paris Saint Germain",
                    Date.valueOf("1970-08-12"),
                    "Mauricio Pochettino",
                    "Nasser Al-Khelaïfi",
                    "24 Rue du Commandant Guilbaud, 75016 Paris",
                    "01 47 43 71 71",
                    "https://www.psg.fr/"
            );
            equipe1.setStade(stade1);
            equipeService.ajouterEquipe(equipe1);

            Equipe equipe2 = new Equipe(
                    "logo_om.png",
                    "Olympique de Marseille",
                    Date.valueOf("1899-08-31"),
                    "Jorge Sampaoli",
                    "Pablo Longoria",
                    "3 Boulevard Michelet, 13008 Marseille",
                    "04 91 76 56 00",
                    "https://www.om.fr/"
            );
            equipe2.setStade(stade2);
            equipeService.ajouterEquipe(equipe2);

            Equipe equipe3 = new Equipe(
                    "logo_ol.png",
                    "Olympique Lyonnais",
                    Date.valueOf("1950-08-01"),
                    "Peter Bosz",
                    "Jean-Michel Aulas",
                    "Parc Olympique Lyonnais, 10 Avenue Simone Veil, 69150 Décines-Charpieu",
                    "04 81 07 55 00",
                    "https://www.ol.fr/"
            );
            equipe3.setStade(stade3);
            equipeService.ajouterEquipe(equipe3);

            Equipe equipe4 = new Equipe(
                    "logo_asse.png",
                    "Association Sportive de Saint-Étienne",
                    Date.valueOf("1919-08-01"),
                    "Claude Puel",
                    "Roland Romeyer",
                    "14 Rue de la Montat, 42000 Saint-Étienne",
                    "04 77 74 48 00",
                    "https://www.asse.fr/"
            );

            equipe4.setStade(stade4);
            equipeService.ajouterEquipe(equipe4);



            

            System.out.println("Initialisation des données terminée.");
        }
    }

}









