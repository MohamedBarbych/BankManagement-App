package com.gsb.Services;

import com.gsb.Metier.GroupeMetier;
import com.gsb.dao.entities.Client;
import com.gsb.dao.entities.Employe;
import com.gsb.dao.entities.Groupe;
import com.gsb.dao.entities.User;
import com.gsb.dao.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GroupeRestService {

    @Autowired
    GroupeMetier groupeMetier;
    @Autowired
    GroupeRepository groupeRepository;


    @RequestMapping(value="/add-groupe",method= RequestMethod.POST)
    public Groupe addGroupe(@RequestBody Groupe c) {
        return groupeMetier.addGroupe(c);
    }

    @PostMapping("/create-groupe")
    public String createClient(@ModelAttribute("groupe") Groupe groupe, @RequestParam("nomGroupe") String nomGroupe) {
        groupe.setNomGroupe(nomGroupe);
        groupeRepository.save(groupe);
        return "redirect:/clients-page";
    }
    @GetMapping("/add-groupe")
    public ModelAndView addGroupesPage(Model model) {
        ModelAndView modelAndView= new ModelAndView("Components/AddGroupe");

        Groupe groupe = new Groupe();
        model.addAttribute("groupe",groupe);

        return modelAndView;
    }

}
