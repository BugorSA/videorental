package com.example.controllers;

import com.example.entities.Client;
import com.example.entities.DVD;
import com.example.repositories.ClientRepository;
import com.example.repositories.DVDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DVDRepository dvdRepository;

    @GetMapping("/clients")
    public String getClients(Model model){
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }
    @PostMapping("/clients")
    public String add(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "secondName") String secondName, @RequestParam(name = "telephone") String telephone, Model model) {
        clientRepository.save(new Client(firstName,secondName,Integer.parseInt(telephone)));
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @PostMapping("see")
    public String getClient(Model model, @RequestParam String id){
        Optional<Client> clientOptional = clientRepository.findById(Integer.valueOf(id));
        if (clientOptional.isPresent()){
            Client client = clientOptional.get();
            model.addAttribute("client", client);
            Iterable<DVD> dvds = dvdRepository.findAllByClientEquals(client);
            model.addAttribute("dvds",dvds);
            return "client";
        }
        else{
            return getClients(model);
        }
    }
}
