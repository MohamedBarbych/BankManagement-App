package com.gsb.Services;

import com.gsb.Metier.ClientMetier;
import com.gsb.Metier.CompteMetier;
import com.gsb.Metier.EmployeMetier;
import com.gsb.dao.entities.*;

import com.gsb.dao.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ClientRestService {
    @Autowired
    private ClientMetier clientMetier;

    @Autowired
    private CompteMetier compteMetier;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeMetier employeMetier;

//    @RequestMapping(value="/create-client",method=RequestMethod.POST)
//    public Client saveClient(@RequestBody Client c) {
//        return clientMetier.saveClient(c);
//    }

    @GetMapping("/client/{codeClient}")
    public Client getClient(@PathVariable Long codeClient) {

        return clientMetier.consulterClient(codeClient);
    }

    @RequestMapping(value="/clients",method=RequestMethod.GET)
    public List<Client> listClient() {
        return clientMetier.listClient();
    }

    @RequestMapping(value="/delete-client/{codeClient}",method=RequestMethod.DELETE)
    public void deleteClient(@PathVariable Long codeClient) {
        clientMetier.deleteClient(codeClient);
    }


    // TODO : Templates
    @PostMapping("/authentifierUser")
    public ModelAndView authentifierClient(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        ModelAndView modelAndView;

        User userX = new User();
        userX.setUserId((Long) user.getUserId());
        userX.setUsername((String) user.getUsername());

        User user1 = userRepository.findByUserIdAndUsername(userX.getUserId(), userX.getUsername());

        System.out.println("userRole, " + user1.getUserRole());


        if (user1.getUserRole().equals("Client")){

            Client clientX = new Client();
            clientX.setCodeClient(userX.getUserId());
            clientX.setNomClient(userX.getUsername());

            Client client1 = clientMetier.authentifierClient(clientX);

            if (client1==null){
                modelAndView= new ModelAndView("authentifierClient");
                return modelAndView;
            }
            else {
                System.out.println("client name, " + client1.getNomClient());

                modelAndView= new ModelAndView("HomeClient");
                Object o = "Client";
                httpSession.setAttribute("userRole", o);
                httpSession.setAttribute("Client", client1);
//                model.addAttribute("Client", client1);

                return clientHome(model,httpSession);
            }
        }
        else if (user1.getUserRole().equals("Employe")) {
            Employe employeX = new Employe();
            employeX.setCodeEmploye(userX.getUserId());
            employeX.setNomEmploye(userX.getUsername());

            Employe employe = employeMetier.authentifierEmploye(employeX);

            if (employe == null){
                modelAndView = new ModelAndView("authentifierClient");
                model.addAttribute("employe", employe);
                return modelAndView;
            }
            else {

//                List<Client> clients = clientMetier.listClient();

                model.addAttribute("clients", clientMetier.listClient());

                modelAndView = new ModelAndView("HomeEmploye");

                httpSession.setAttribute("Employe", employe);
                Object o = "Employe";
                httpSession.setAttribute("userRole", o);

                return modelAndView;
            }
        } else if (user1.getUserRole().equals("Admin")) {

            System.out.println("Admin, " + user1.getUsername());
            model.addAttribute("admin", user1);
            modelAndView = new ModelAndView("HomeAdmin");
//<<<<<<< Updated upstream
//            return getEmployePage(model);
//=======
            Object o = "Admin";
            httpSession.setAttribute("userRole", o);
            return modelAndView;
        }

        Object o = "Other";
        httpSession.setAttribute("userRole", o);
        modelAndView = new ModelAndView("authentifierClient");

        return modelAndView;
    }


    @GetMapping("/auth")
    public ModelAndView authentifierClient(Model model, HttpSession httpSession) {

        String userRole = (String) httpSession.getAttribute("userRole");

        if (userRole.equals("Employe")) {
            return employeHome(model, httpSession);
        }

        else if (userRole.equals("Client")) {
            return clientHome(model, httpSession);
        }

        else if (userRole.equals("Admin")) {
            ModelAndView modelAndView = new ModelAndView("HomeAdmin");
            return modelAndView;
        }

        ModelAndView modelAndView= new ModelAndView("authentifierClient");
        User user = new User();
        model.addAttribute("user", user);

        return modelAndView;
    }
    @GetMapping("/employes-page")
    public ModelAndView getEmployePage(Model model) {
        ModelAndView modelAndView= new ModelAndView("HomeAdmin");

        employeMetier.listEmployes().forEach(emp -> {
            System.out.println("nomEmploye, " + emp.getNomEmploye() + ", emp Sup" + emp.getEmployeSup().getNomEmploye());
        });

        model.addAttribute("employes", employeMetier.listEmployes());
        System.out.println(listEmployes());

        return modelAndView;
    }
    @RequestMapping(value="/employes",method=RequestMethod.GET)
    public List<Employe> listEmployes() {
        return employeMetier.listEmployes();
    }

    @GetMapping("/clients-page")
    public ModelAndView getClientsPage(Model model, HttpSession httpSession) {
        String userRole = (String) httpSession.getAttribute("userRole");
        if(userRole.equals("Other")) return new ModelAndView("authentifierClient");

        else if (userRole.equals("Client")) {
            return new ModelAndView("UnauthorizedPage");
        }



        ModelAndView modelAndView= new ModelAndView("Views/Clients");
        model.addAttribute("clients", clientMetier.listClient());

        return modelAndView;
    }

    @GetMapping("/add-client")
    public ModelAndView addClientsPage(Model model, HttpSession httpSession) {

        String userRole = (String) httpSession.getAttribute("userRole");

        if(userRole.equals("Other")) return new ModelAndView("authentifierClient");

        else if (userRole.equals("Client")) {
            return new ModelAndView("UnauthorizedPage");
        }

        ModelAndView modelAndView= new ModelAndView("Components/AddClient");

        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("typeCpte", "");


        return modelAndView;
    }

    @PostMapping("/create-client")
    public ModelAndView createClient(@ModelAttribute("client") Client client, @RequestParam("typeCpte") String typeCpte, Model model, HttpSession httpSession) {
        Client client1 = clientMetier.saveClient(client);

        String codeCompte = generateUniqueCodeCompte(typeCpte, client1.getCodeClient());

        userRepository.save(new User(client1.getNomClient(), client1.getCodeClient(), "Client"));
        ModelAndView modelAndView= new ModelAndView("Views/Clients");

        try {
            saveCompte(typeCpte, client.getCodeClient(), 1L, codeCompte);

        } catch (Exception e) {
            throw new RuntimeException("There is an error!");
        }

        return getClientsPage(model,httpSession);
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
    @GetMapping("/clientHome")
    public  ModelAndView clientHome(Model model, HttpSession httpSession) {
//        String userRole = (String) httpSession.getAttribute("userRole");
//        if(userRole.equals("Other")) return new ModelAndView("authentifierClient");
//
//        else if (userRole.equals("Employe")) {
//            return new ModelAndView("UnauthorizedPage");
//        }

        Client client = (Client) httpSession.getAttribute("Client");

        client.getComptes().forEach(compte -> {
            compte.getOperations().forEach(op -> {
                if (op instanceof Retrait) {
                    ((Retrait) op).setTypeOperation("Retrait");
                } else if (op instanceof Versment) {
                    ((Versment) op).setTypeOperation("Versment");
                }
                else if (op instanceof Virement) {
                    ((Virement) op).setTypeOperation("Virement");
                }
            });
            if (compte instanceof CompteCourant) {
                ((CompteCourant) compte).setTypeCompte("Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                ((CompteEpargne) compte).setTypeCompte("Compte Epargne");
            }
        });

        model.addAttribute("client", client);


        ModelAndView modelAndView = new ModelAndView("HomeClient");
        return modelAndView;
    }


    @GetMapping("/home")
    public  ModelAndView employeHome(Model model, HttpSession httpSession) {
        String userRole = (String) httpSession.getAttribute("userRole");
        if(userRole.equals("Other")) return authentifierClient(model, httpSession);

        else if (userRole.equals("Client")) {
            return new ModelAndView("UnauthorizedPage");
        }

        List<Client> clients = clientMetier.listClient();

        model.addAttribute("clients", clients);

        ModelAndView modelAndView = new ModelAndView("HomeEmploye");
        return modelAndView;
    }

    @PostMapping("/home")
    public  ModelAndView employeStateHome(@RequestParam("selectedClient") Long selectedClientCode, Model model) {

        Client selectedClient = clientMetier.consulterClient(selectedClientCode);

        selectedClient.getComptes().forEach(compte -> {
            compte.getOperations().forEach(op -> {
                if (op instanceof Retrait) {
                    ((Retrait) op).setTypeOperation("Retrait");
                } else if (op instanceof Versment) {
                    ((Versment) op).setTypeOperation("Versment");
                }
                else if (op instanceof Virement) {
                    ((Virement) op).setTypeOperation("Virement");
                }
            });
            if (compte instanceof CompteCourant) {
                ((CompteCourant) compte).setTypeCompte("Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                ((CompteEpargne) compte).setTypeCompte("Compte Epargne");
            }
        });

        List<Client> clients = clientMetier.listClient();

        model.addAttribute("clients", clients);

        model.addAttribute("selectedClient", selectedClient);



        ModelAndView modelAndView = new ModelAndView("HomeEmploye");

        return modelAndView;
    }


    // TODO : Templates
    @GetMapping("/operationsClient-page")
    public ModelAndView getOpsPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("Views/CompteClient");

        List<Client> clients = clientMetier.listClient();

        model.addAttribute("clients", clients);

        return modelAndView;
    }

    @PostMapping("/operationsClient-page")
    public ModelAndView displayComptes(@RequestParam("selectedClient") Long selectedClientCode, Model model) {
        Client selectedClient = clientMetier.consulterClient(selectedClientCode);

        model.addAttribute("selectedClient", selectedClient);
        List<Compte> comptes = compteMetier.allComptes();

        model.addAttribute("comptes", comptes);

        ModelAndView modelAndView = new ModelAndView("Views/CompteClient");
        modelAndView.addObject("selectedClient", selectedClient);

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView displayComptes(Model model, HttpSession httpSession) {

        Object o = "Other";
        httpSession.setAttribute("userRole", o);


        return authentifierClient(model, httpSession);
    }




}