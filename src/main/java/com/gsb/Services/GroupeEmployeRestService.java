package com.gsb.Services;

import com.gsb.dao.entities.Employe;
import com.gsb.dao.entities.Groupe;
import com.gsb.dao.repository.EmployeRepository;
import com.gsb.dao.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/groupe-employe")
public class GroupeEmployeRestService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @GetMapping("/view")
    public ModelAndView viewGroupeAndEmploye(Model model) {
        // Fetch all groups and employees
        List<Groupe> groupes = groupeRepository.findAll();
        List<Employe> employes = employeRepository.findAll();

        // Add both groups and employees to the model
        model.addAttribute("groupes", groupes);
        model.addAttribute("employes", employes);

        // Return the ModelAndView with the name of the Thymeleaf template
        return new ModelAndView("Components/GroupeEmployeView");
    }
}
