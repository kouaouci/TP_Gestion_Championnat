package com.Championnat.TP_Gestion_Championnat.controllers;

import com.Championnat.TP_Gestion_Championnat.Service.*;
import com.Championnat.TP_Gestion_Championnat.pojos.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
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
    private MatchService matchService;
    @Autowired
    private StadeService stadeService;

    public BlogChampionnatController() {
    }

    public BlogChampionnatController(UserService userService, PaysService paysService, ChampionnatService championnatService, EquipeService equipeService, JourneeService journeeService, MatchService matchService, StadeService stadeService) {
        super();
        this.userService = userService;
        this.paysService = paysService;
        this.championnatService = championnatService;
        this.equipeService = equipeService;
        this.journeeService = journeeService;
        this.matchService = matchService;
        this.stadeService = stadeService;
    }

    //User
    @GetMapping({ "login"})
    public String login() {
        return "login";
    }

    @GetMapping({"/","accueil"})
    public String accueil(Model model) {
        List<Championnat> championnats = championnatService.recupererChampionnatAll();
        model.addAttribute("championnats", championnats);
        return "accueil";
    }


    @PostMapping("/logUser")
    public RedirectView logUser(Model model, HttpSession session, @RequestParam String uname, @RequestParam String psw) {
        List<User> users = userService.recupererUserAll();
        boolean connected = false;
        String pseudo = null;
        for (User userOfBdd : users) {
            if (Objects.equals(uname, userOfBdd.getLogin()) && Objects.equals(psw, userOfBdd.getMdp())) {
                connected = true;
                pseudo = userOfBdd.getPseudo(); // Get the username
                break;
            }
        }

        if (connected) {
            // Store username in session
            session.setAttribute("pseudo", pseudo);
            return new RedirectView("accueil");
        } else {
            return new RedirectView("");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Récupérer la session actuelle et l'invalider
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Rediriger vers la page d'accueil ou toute autre page après la déconnexion
        return "redirect:/";
    }


    @GetMapping({"addUser"})
    public String addUser() {
        return "create";
    }

    @PostMapping({"saveUser"})
    public RedirectView saveUser(Model model, @RequestParam String uname, @RequestParam String psw) {
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        try {

            User user = new User();
            user.setLogin(uname);
            user.setMdp(psw);
            userService.ajouterUser(user);

            attributes.addFlashAttribute("success", "User created successfully!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "An error occurred while creating the user.");
            e.printStackTrace(); // Log the exception for debugging
        }
        return new RedirectView("/");
    }

    @GetMapping({"equipes"})
    public String listeEquipes(Model model, @ModelAttribute Stade stade) {
        List<Equipe> equipes = equipeService.recupererEquipeAll();
        model.addAttribute("equipes", equipes);
        return "listeEquipes";
    }


    @GetMapping({"equipe/{idEquipe}/detail"})
    public String details_equipe(Model model, @PathVariable long idEquipe) {
        Equipe equipe = equipeService.recupererEquipe(idEquipe);
        List<Stade> stades = stadeService.recupererStadeAll();
        model.addAttribute("allStades", stades);

        model.addAttribute("equipe", equipe);

        return "detailsEquipe";
    }

    @GetMapping({"equipe/newEquipe"})
    public String newEquipe(Model model, @ModelAttribute Equipe equipe) {
        List<Stade> stades = stadeService.recupererStadeAll();
        model.addAttribute("allStades", stades);
        model.addAttribute("equipe", equipe);
        return "detailsEquipe";
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
    @GetMapping({"details_championnat"})
    public String details_championnat(Model model, @RequestParam long idChampionnat) {
        Championnat championnat = championnatService.recupererChampionnat(idChampionnat);
            List<Equipe> equipes = championnat.getEquipes();
            List<Journee> journees = championnat.getJournees();
            journees.sort(Comparator.comparing(Journee::getNumero).reversed());
            List<Matchs> matches = new ArrayList<>();
            List<EquipeChampionnat> equipeChampionnat = new ArrayList<>();
            for (Journee journee : journees) {
                for (Matchs match : journee.getMatches()) {
                    matches.add(match);
                }
            }
            for (Equipe equipe : equipes) {
                int totalPoint = 0;
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
                equipeChampionnat.add(equipeStats);
            }
            equipeChampionnat.sort(Comparator.comparing(EquipeChampionnat::getTotalPoint).reversed());
            model.addAttribute("championnat", championnat);
            model.addAttribute("equipeChampionnat", equipeChampionnat);
            return "indexClassement";
        }

    @PostMapping({"championnat/{championnatId}/jours"})
    public RedirectView allMatchOfJourneeIdAndChampionnat(Model model, @RequestParam long championnatId, @RequestParam long journeeId) {
        return new RedirectView("/championnat/" + championnatId + "/jours/" + journeeId + "/resultatsListe");
    }

    @GetMapping({"/championnat/{championnatId}/jours/{journeeId}/resultatsListe"})
    public String listResultatsOfChampionnatAndJournee(Model model, @PathVariable long championnatId, @PathVariable long journeeId) {
        Championnat championnat = championnatService.recupererChampionnat(championnatId);
        Journee journee = journeeService.recupererJournee(journeeId);
        HashMap<String, List<Matchs>> allMatchOfChampionnat = new HashMap<>();
        List<Journee> journees = championnat.getJournees();

        List<Matchs> allMatchOfJournee = journee.getMatches();

        allMatchOfChampionnat.put(journee.getNumero().toString(), allMatchOfJournee);

        model.addAttribute("allJournees", journees);
        model.addAttribute("championnat", championnat);
        model.addAttribute("allMatchForAllJournees", allMatchOfChampionnat);

        return "liste";
    }

    @GetMapping({"/championnat/{id}/resultatsListe"})
    public String listResultatsOfChampionnat(Model model, @PathVariable long id) {
        Equipe equipe = equipeService.recupererEquipe(id);
        Championnat championnat = championnatService.recupererChampionnat(id);
        HashMap<String, List<Matchs>> allMatchOfChampionnat = new HashMap<>();

        List<Journee> journees = championnat.getJournees();
        for (Journee journee : journees) {
            List<Matchs> allMatchOfJournee = journee.getMatches();

            allMatchOfChampionnat.put(journee.getNumero().toString(), allMatchOfJournee);
        }
        model.addAttribute("allJournees", journees);
        model.addAttribute("championnat", championnat);
        model.addAttribute("allMatchForAllJournees", allMatchOfChampionnat);

        return "liste";
    }

    @GetMapping({"championnats"})
    public String championnats(Model model) {
        List<Championnat> championnats = championnatService.recupererChampionnatAll();
        model.addAttribute("championnats", championnats);
        return "indexListRes" ;

    }

    @GetMapping({"championnat/newChampionnat"})
    public String newChampionnat(Model model, @ModelAttribute Championnat championnat) {
        List<Pays> pays = paysService.recupererPaysAll();
        model.addAttribute("allPays", pays);
        model.addAttribute("championnat", championnat);
        return "championnatForm";
    }

    @PostMapping(value = "championnat/saveChampionnat")
    public RedirectView saveChampionnat(Model model, @Validated @ModelAttribute Championnat championnat, @RequestParam long paysId) {
        Pays pays = paysService.recupererPays(paysId);
        championnat.setPays(pays);
        championnat = championnatService.ajouterChampionnat(championnat);
        model.addAttribute("championnat", championnat);
        return new RedirectView("/championnats" );
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

            User user3 = new User();
            user3.setLogin("root");
            user3.setMdp("root");
            user3.setPseudo("Admin");
            userService.ajouterUser(user3);

            Pays pays1 = new Pays();
            pays1.setNom("France");
            pays1.setLogo("logo_france.png");
            paysService.ajouterPays(pays1);

            Pays pays2 = new Pays();
            pays2.setNom("Allemagne");
            pays2.setLogo("logo_germany.png");
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
                    "Coupe du monde",
                    "logo_coupe_du_monde.png",
                    Date.valueOf("2021-08-01"),
                    Date.valueOf("2022-05-30"),
                    3,
                    0,
                    1,
                    "pool"
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
                    "logo_liga.png",
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

            Stade stade6 = new Stade(
                    "Allianz Arena",
                    "Munich",
                    75000,
                    "www.allianz-arena.de"
            );
            stadeService.ajouterStade(stade6);

            Stade stade7 = new Stade(
                    "Estádio do Maracanã",
                    "Rio de Janeiro",
                    78838,
                    "www.maracana.com"
            );
            stadeService.ajouterStade(stade7);



            Equipe equipe1 = new Equipe(
                    "logo_france.png", // Nom du logo de l'équipe
                    "France", // Nom de l'équipe
                    Date.valueOf("1904-04-06"), // Date de fondation de l'équipe
                    "Actif", // Statut de l'équipe
                    stade1.getId(), // ID du stade de l'équipe
                    "Didier Deschamps", // Nom de l'entraîneur
                    "Noël Le Graët", // Nom du président
                    "France", // Pays d'origine de l'équipe
                    "0123456789", // Numéro de téléphone de l'équipe
                    "www.fff.fr" // Site web de l'équipe
            );
            equipe1.setStade(stade4);
            equipeService.ajouterEquipe(equipe1);

            Equipe equipe2 = new Equipe(
                    "logo_brazil.png",
                    "Brésil",
                    Date.valueOf("1914-07-21"),
                    "Actif",
                    stade1.getId(),
                    "Tite",
                    "Rogerio Caboclo",
                    "Brésil",
                    "0123456789",
                    "www.cbf.com.br"
            );
            equipe2.setStade(stade7);
            equipeService.ajouterEquipe(equipe2);

            Equipe equipe4 = new Equipe(
                    "logo_germany.png",
                    "Allemagne",
                    Date.valueOf("1900-01-28"),
                    "Actif",
                    stade1.getId(),
                    "Hans-Dieter Flick",
                    "Fritz Keller",
                    "Allemagne",
                    "0123456789",
                    "www.dfb.de"
            );
            equipe4.setStade(stade6);
            equipeService.ajouterEquipe(equipe4);



            System.out.println("Initialisation des données terminée.");
        }
    }

}









