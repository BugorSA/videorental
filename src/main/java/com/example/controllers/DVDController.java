package com.example.controllers;

import com.example.entities.Client;
import com.example.entities.DVD;
import com.example.repositories.ClientRepository;
import com.example.repositories.DVDRepository;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DVDController {

    @Autowired
    DVDRepository dvdRepository;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/dvds")
    public String getDVDs(Model model){
        Iterable<DVD> dvds = dvdRepository.findAll();
        model.addAttribute("dvds", dvds);
        return "dvds";
    }
    @PostMapping("/dvds")
    public String add(@RequestParam(name = "id_client") String id_client, @RequestParam(name = "russianName") String russianName, @RequestParam(name = "englishName") String englishName, @RequestParam(name = "year") String year, Model model) {
        Optional<Client> clientOptional = clientRepository.findById(Integer.parseInt(id_client));
        DVD dvd = new DVD(russianName,englishName,Integer.parseInt(year));
        clientOptional.ifPresent(client -> client.addDVD(dvd));
        dvdRepository.save(dvd);
        Iterable<DVD> dvds = dvdRepository.findAll();
        model.addAttribute("dvds", dvds);
        return "dvds";
    }
}
