package com.voli.voli.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voli.voli.model.Aereo;
import com.voli.voli.model.Volo;
import com.voli.voli.service.AereoService;
import com.voli.voli.service.VoloService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {

    @Autowired
    private VoloService voloService;

    @Autowired
    private AereoService aereoService;

    @GetMapping("/aerei")
    public List<Aereo> findAll(){
        return aereoService.findAll();
    }


    @GetMapping("/cerca")
    public List<Volo> cercaVoli(
    @RequestParam("cittaPartenza") String cittaPartenza,
    @RequestParam("cittaArrivo") String cittaArrivo,
    @RequestParam("data") LocalDate data,
    @RequestParam("pesoBagaglio") int pesoBagaglio) {

        List<Volo> results = voloService.getAvailableFlights(cittaPartenza, cittaArrivo, data, pesoBagaglio);

        return results;
    }
    
}
