package com.voli.voli.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voli.voli.model.Aeroporto;
import com.voli.voli.model.Volo;
import com.voli.voli.service.AeroportoService;
import com.voli.voli.service.VoloService;



@Controller
public class HomeController {

    @Autowired
    private VoloService voloService;


    @Autowired
    private AeroportoService aeroportoService;


    @GetMapping({"/","/home"})
    public String homePage(Model model) {
        List<Aeroporto> aeroporti = aeroportoService.getAeroportiOrdinati();
        model.addAttribute("aeroporti", aeroporti);
        model.addAttribute("today", LocalDate.now());
        return "home";
    }
    

    @GetMapping("/cerca")
    public String cercaVoli(
    @RequestParam("cittaPartenza") String cittaPartenza,
    @RequestParam("cittaArrivo") String cittaArrivo,
    @RequestParam("data") LocalDate data,
    @RequestParam("pesoBagaglio") int pesoBagaglio,
    Model model
    ) {

        List<Volo> results = voloService.getAvailableFlights(cittaPartenza, cittaArrivo, data, pesoBagaglio);

        if(results.isEmpty()){
            model.addAttribute("cittaPartenza", cittaPartenza);
            model.addAttribute("cittaArrivo", cittaArrivo);
            model.addAttribute("data", data);
            return "no_voli";

        }
        model.addAttribute("voli", results);
        return "risultati_voli";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
}
