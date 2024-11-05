package com.gsb.Services;

import com.gsb.Metier.ClientMetier;
import com.gsb.Metier.CompteMetier;
import com.gsb.Metier.EmployeMetier;
import com.gsb.dao.entities.*;
import com.gsb.dao.repository.CompteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CompteRestService {

    @Autowired
    private CompteMetier compteMetier;

    @Autowired
    private ClientMetier clientMetier;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private EmployeMetier employeMetier;

    @RequestMapping(value="/add-compte",method= RequestMethod.POST)
    public Compte createCompte(@RequestBody Map<String, Object> requestData) {
        String typeOfCompte = (String) requestData.get("typeOfCompte");
        Integer codeClient = (Integer) requestData.get("codeClient");
        Integer codeEmploye = (Integer) requestData.get("codeEmploye");
        String codeCompte = (String) requestData.get("codeCompte");

        if (typeOfCompte.equals("E")) {
            return compteMetier.addCompte(new CompteEpargne(), codeClient.longValue(), codeEmploye.longValue(), codeCompte);
        }
        return compteMetier.addCompte(new CompteCourant(), codeClient.longValue(), codeEmploye.longValue(), codeCompte);
    }

    @RequestMapping(value="/get-compte/{codeCompte}",method= RequestMethod.GET)
    public Compte getCompte(@PathVariable String codeCompte) {
//        String codeCompte = (String) requestData.get("codeCompte");

        System.out.println("codeCompte, " + codeCompte );
        return compteMetier.getCompte(codeCompte);
    }

    @RequestMapping(value="/delete-compte/{codeCompte}",method=RequestMethod.DELETE)
    public void deleteCompte(@PathVariable String codeCompte) {
        compteMetier.deleteCompte(codeCompte);
    }

    @RequestMapping(value="/comptes",method=RequestMethod.GET)
    public List<Compte> listCompte() {
        return compteMetier.allComptes();
    }

    @RequestMapping(value="/comptes-client/{codeClient}",method=RequestMethod.GET)
    public List<Compte> listCompteClient(@PathVariable Long codeClient) {
        return compteMetier.comptesClient(codeClient);
    }

    @RequestMapping(value="/comptes-employe/{codeEmploye}",method=RequestMethod.GET)
    public List<Compte> listCompteEmploye(@PathVariable Long codeEmploye) {
        Map<String, Object> requestData = new HashMap<>();
        return compteMetier.comptesEmployees(codeEmploye);
    }


    //TODO : TEMPLATES
    @GetMapping("/comptes-page")
    public ModelAndView getComptesPage(Model model, HttpSession httpSession) {
//        System.out.println("userRoleTset, " + httpSession.getAttribute("userRole"));
        String userRole = (String) httpSession.getAttribute("userRole");
        if(userRole.equals("Other")) return new ModelAndView("authentifierClient");

        else if (userRole.equals("Client")) {
            return new ModelAndView("UnauthorizedPage");
        }

        ModelAndView modelAndView = new ModelAndView("Views/Comptes");


        List<Compte> comptes = compteMetier.allComptes();

        comptes.forEach(compte -> {
            if (compte instanceof CompteCourant) {
                ((CompteCourant) compte).setTypeCompte("Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                ((CompteEpargne) compte).setTypeCompte("Compte Epargne");
            }
        });

        model.addAttribute("comptes", comptes);

        return modelAndView;
    }

    @GetMapping("/add-compte")
    public ModelAndView addClientsPage(Model model, HttpSession httpSession) {

        String userRole = (String) httpSession.getAttribute("userRole");
        if(userRole.equals("Other")) return new ModelAndView("authentifierClient");

        else if (userRole.equals("Client")) {
            return new ModelAndView("UnauthorizedPage");
        }

        ModelAndView modelAndView= new ModelAndView("Components/AddCompte");

        Compte compte = new Compte();

        model.addAttribute("compte", compte);
        model.addAttribute("clients", clientMetier.listClient());

        return modelAndView;
    }

    @PostMapping("/create-compte")
    public String createCompte(@ModelAttribute("compte") Compte compte) {

        Long codeClient = compte.getClient().getCodeClient();
        String typeCpte = compte.getTypeCompte();

        String codeCompte = !Objects.equals(compte.getCodeCompte(), "") ? compte.getCodeCompte() : generateUniqueCodeCompte(typeCpte, codeClient);

        try {
            saveCompte(typeCpte, codeClient, 1L, codeCompte);
        } catch (Exception e) {
            throw new RuntimeException("There is an error!");
        }

        return "redirect:/comptes-page";
    }

    private String generateUniqueCodeCompte(String typeCpte, Long codeClient) {
        int maxClientComptes = compteMetier.comptesClient(codeClient).size();
        return typeCpte.split(" ")[1].substring(0, 2).toUpperCase() + codeClient.toString() + maxClientComptes;
    }

    private void saveCompte(String typeCpte, Long codeClient, Long codeEmploye, String codeCompte) {
        if (typeCpte.equals("Compte Courant")) {
            compteMetier.addCompte(new CompteCourant(), codeClient, codeEmploye, codeCompte);
        } else {
            compteMetier.addCompte(new CompteEpargne(), codeClient, codeEmploye, codeCompte);
        }
    }


    @GetMapping("/operations-page")
    public ModelAndView getOpsPage(Model model, HttpSession httpSession) {
        String userRole = (String) httpSession.getAttribute("userRole");
        if(userRole.equals("Other")) return new ModelAndView("authentifierClient");

        else if (userRole.equals("Client")) {
            return new ModelAndView("UnauthorizedPage");
        }

        ModelAndView modelAndView = new ModelAndView("Views/Transactions");

        List<Compte> comptes = compteMetier.allComptes();

        comptes.forEach(compte -> {
            if (compte instanceof CompteCourant) {
                ((CompteCourant) compte).setTypeCompte("Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                ((CompteEpargne) compte).setTypeCompte("Compte Epargne");
            }
        });

        model.addAttribute("comptes", comptes);

        return modelAndView;
    }

    @PostMapping("/operations-page")
    public ModelAndView displayCompteOperations(@RequestParam("selectedCompte") String selectedCompteCode, Model model) {
        Compte selectedCompte = compteRepository.findByCodeCompte(selectedCompteCode);
        model.addAttribute("selectedCompte", selectedCompte);
        List<Compte> comptes = compteMetier.allComptes();

        Operation operation = new Operation();

        comptes.forEach(compte -> {
            compte.getOperations().forEach(op -> {
                if (op instanceof Retrait) {
                    ((Retrait) op).setTypeOperation("Retrait");
                } else if (op instanceof Versment) {
                    ((Versment) op).setTypeOperation("Versment");
                } else if (op instanceof Virement) {
                    ((Virement) op).setTypeOperation("Virement");
                }
            });
            if (compte instanceof CompteCourant) {
                ((CompteCourant) compte).setTypeCompte("Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                ((CompteEpargne) compte).setTypeCompte("Compte Epargne");
            }
        });


        model.addAttribute("comptes", comptes);
        model.addAttribute("operation", operation);

        ModelAndView modelAndView = new ModelAndView("Views/Transactions");
        modelAndView.addObject("selectedCompte", selectedCompte);

        return modelAndView;
    }

    @PostMapping("/add-operation*")
    public ModelAndView addOperation(@ModelAttribute("operation") Operation operation, Model model, @RequestParam("operationType") String operationType, @RequestParam("codeCompte") String codeCompte, @RequestParam(value = "selectedOtherCompte", required = false) String selectedOtherCompte) {

//        System.out.println("operationType, " + operationType);
        System.out.println("selectedCompteCode, " + selectedOtherCompte);
//        System.out.println("monatnt, " + operation.getMontant());

        if (operationType.equals("Retrait")){
            compteMetier.retrait(codeCompte, operation.getMontant());
            employeMetier.addOperation(new Retrait(), codeCompte, 1L, operation.getMontant());
        } else if (operationType.equals("Versement")) {
            compteMetier.verser(codeCompte, operation.getMontant());
            employeMetier.addOperation(new Versment(), codeCompte, 1L, operation.getMontant());
        }
        else if (operationType.equals("Virement")) {
            compteMetier.virement(codeCompte, selectedOtherCompte, operation.getMontant());
            employeMetier.addOperation(new Virement(), codeCompte, 1L, operation.getMontant());
        }

        Compte selectedCompte = compteRepository.findByCodeCompte(codeCompte);
        model.addAttribute("selectedCompte", selectedCompte);
        List<Compte> comptes = compteMetier.allComptes();

        Operation operation2 = new Operation();

        comptes.forEach(compte -> {
            compte.getOperations().forEach(op -> {
                if (op instanceof Retrait) {
                    ((Retrait) op).setTypeOperation("Retrait");
                } else if (op instanceof Versment) {
                    ((Versment) op).setTypeOperation("Versment");
                } else if (op instanceof Virement) {
                    ((Virement) op).setTypeOperation("Virement");
                }
            });
            if (compte instanceof CompteCourant) {
                ((CompteCourant) compte).setTypeCompte("Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                ((CompteEpargne) compte).setTypeCompte("Compte Epargne");
            }
        });


        model.addAttribute("comptes", comptes);
        model.addAttribute("operation", operation2);

        ModelAndView modelAndView = new ModelAndView("Views/Transactions");
        modelAndView.addObject("selectedCompte", selectedCompte);

        return modelAndView;

    }

    // TODO : GET Compte operations based on the (@PathVariable String codeCompte)

//    @GetMapping("/home")
//    public ModelAndView homeEmp(Model model) {
//        ModelAndView modelAndView= new ModelAndView("HomeEmploye");
//
////        Compte compte = new Compte();
//
////        model.addAttribute("compte", compte);
////        model.addAttribute("clients", clientMetier.listClient());
//
//        return modelAndView;
//    }

}
