package com.voli.voli.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voli.voli.model.Volo;
import com.voli.voli.service.AereoService;
import com.voli.voli.service.AeroportoService;
import com.voli.voli.service.VoloService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private VoloService voloService;

    @Autowired
    private AereoService aereoService;
    
    @Autowired
    private AeroportoService aeroportoService;




    @GetMapping("/voli/report")
    public String getFutureFlights(
    Model model,
    @RequestParam("data") LocalDate data
    ) {
        //LocalDate oggi = LocalDate.now();
        List<Volo> voliFuturi = voloService.cercaVoliFuturi(data);
        model.addAttribute("voli_futuri", voliFuturi);
        return "admin/report";
    }


    @GetMapping("/voli/nuovo")
    public String nuovoVoloForm(Model model) {
        model.addAttribute("nuovo_volo", new Volo());
        model.addAttribute("aeroporti", aeroportoService.getAeroportiOrdinati());
        model.addAttribute("tipi_aereo", aereoService.getTipiAereoOrdinati());
        return "admin/nuovo_volo_form";
    }

    @PostMapping("/voli/salva")
    public String salvaVolo(@ModelAttribute("volo") Volo volo) {


        
        return "admin/report";
    }
    
    


}
