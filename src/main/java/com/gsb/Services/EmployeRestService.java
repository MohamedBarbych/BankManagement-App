package com.gsb.Services;

import com.gsb.Metier.CompteMetier;
import com.gsb.Metier.EmployeMetier;
import com.gsb.dao.entities.*;
import com.gsb.dao.repository.ClientRepository;
import com.gsb.dao.repository.EmployeRepository;
import com.gsb.dao.repository.GroupeRepository;
import com.gsb.dao.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeRestService {
    @Autowired
    private EmployeMetier employeMetier;

    @Autowired
    private CompteMetier compteMetier;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @RequestMapping(value="/add-employe",method=RequestMethod.POST)
    public Employe saveEmploye(@RequestBody Employe e) {

        Employe employe = employeMetier.saveEmploye(e);

        userRepository.save(new User(employe.getNomEmploye(), employe.getCodeEmploye(), "Employe"));

        return employe;
    }



    @RequestMapping(value="/authentifierEmploye", method=RequestMethod.POST)
    public Employe authentifierClient(@RequestParam("codeEmploye") Long codeEmploye, @RequestParam("nomEmploye") String nomEmploye, HttpSession httpSession) {
        Employe employe = new Employe();
        employe.setCodeEmploye(codeEmploye);
        employe.setNomEmploye(nomEmploye);

        httpSession.setAttribute("Employe", employe);
        Employe employe2 = (Employe) httpSession.getAttribute("Employe");
        System.out.println(employe2.getNomEmploye());

        return employeMetier.authentifierEmploye(employe);
    }

    @RequestMapping(value="/delete-employe/{codeEmploye}",method=RequestMethod.DELETE)
    public void deleteEmploye(@PathVariable Long codeEmploye) {
        employeMetier.deleteEmployee(codeEmploye);
    }

    @RequestMapping(value = "/add-emp-groupe", method = RequestMethod.POST)
    public void ajouterEmpGroup(@RequestBody Map<String, Long> request) {
        Long codeGroupe = request.get("codeGroupe");
        Long codeEmploye = request.get("codeEmploye");

        System.out.println(codeGroupe + ", " + codeEmploye);

        employeMetier.addEmployeToGroupe(codeGroupe, codeEmploye);
    }

    @RequestMapping(value="/employes-per-groupe/{codeGroupe}",method=RequestMethod.GET)
    public List<Employe> employesPerGroupe(@PathVariable Long codeGroupe) {
        return employeMetier.getEmployeesByGroup(codeGroupe);
    }

    @RequestMapping(value="/versement",method=RequestMethod.PUT)
    public void verser(@RequestBody Map<String, Object> request) {
        String code_cmpt = (String) request.get("codeCompte");
        Integer codeEmploye = (Integer) request.get("codeEmploye");
        Integer montant = (Integer) request.get("montant");

        compteMetier.verser(code_cmpt, montant);
        employeMetier.addOperation(new Versment(), code_cmpt, codeEmploye.longValue(), montant.doubleValue());
    }

    @RequestMapping(value="/retrait",method=RequestMethod.PUT)
    public void retirer(@RequestBody Map<String, Object> request) {
        String code_cmpt = (String) request.get("codeCompte");
        Integer codeEmploye = (Integer) request.get("codeEmploye");
        Integer montant = (Integer) request.get("montant");

        compteMetier.retrait(code_cmpt, montant);
        employeMetier.addOperation(new Retrait(), code_cmpt, codeEmploye.longValue(), montant.doubleValue());
    }

    @RequestMapping(value="/compte-operations/{codeCompte}",method=RequestMethod.GET)
    public List<Operation> listOperationsOfCompte(@PathVariable String codeCompte){
        Compte compte = compteMetier.getCompte(codeCompte);
        return employeMetier.listOperations(compte);
    }
    @PostMapping("/create-employe")
    public ModelAndView createEmploye(@ModelAttribute("employe") Employe employe,
                                @RequestParam("codeGroupe") Long codeGroupe,
                                @RequestParam("codeEmpSup") Long codeEmpSup) {

        ModelAndView modelAndView = new ModelAndView("HomeAdmin");

        Employe employe2 = employeRepository.findByCodeEmploye(codeEmpSup);
        employe.setEmployeSup(employe2);

        Employe employe1 = employeMetier.saveEmploye(employe);

        Groupe groupe = groupeRepository.findByCodeGroupe(codeGroupe);
        System.out.println(groupe.getNomGroupe());

        // Initialize the list if it's null
        Collection<Groupe> list = employe1.getGroupes();
        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(groupe);
        employe1.setGroupes(list);
        employeRepository.save(employe1);

        userRepository.save(new User(employe1.getNomEmploye(), employe1.getCodeEmploye(), "Employe"));

        return modelAndView;
    }

    @GetMapping("/add-employe")
    public ModelAndView addEmployesPage(Model model) {
        ModelAndView modelAndView= new ModelAndView("Components/AddEmploye");

        Employe employe = new Employe();
        model.addAttribute("employes",employeMetier.listEmployes());
        model.addAttribute("employe",employe);
        model.addAttribute("groupes", groupeRepository.findAll());

        return modelAndView;
    }



}