package com.voli.voli.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voli.voli.model.Volo;
import com.voli.voli.service.AereoService;
import com.voli.voli.service.AeroportoService;
import com.voli.voli.service.VoloService;


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
            @RequestParam(defaultValue = "2017-01-01", name = "data") LocalDate data) {
        // LocalDate oggi = LocalDate.now();
        List<Volo> voliFuturi = voloService.cercaVoliFuturi(data);
        model.addAttribute("voliFuturi", voliFuturi);
        return "admin/report";
    }

    @GetMapping("/voli/nuovo")
    public String nuovoVoloForm(Model model) {
        model.addAttribute("volo", new Volo());
        model.addAttribute("aeroporti", aeroportoService.getAeroportiOrdinati());
        model.addAttribute("tipiAereo", aereoService.getTipiAereoOrdinati());
        model.addAttribute("isModifica", false);
        return "admin/volo_form";
    }

    @PostMapping("/voli/salva")
    public String salvaVolo(@ModelAttribute("volo") Volo volo) {
        voloService.aggiungiVolo(volo);
        return "redirect:/admin/voli/report";
    }

    @PostMapping("/voli/cancella/{id}")
    public String cancellaVolo(@PathVariable("id") Integer idVolo) {
        System.out.println(idVolo);

        voloService.cancellaVolo(idVolo);

        return "redirect:/admin/voli/report";
    }

    @GetMapping("/voli/modifica/{id}")
    public String modificaVoloForm(@PathVariable("id") Integer idVolo, Model model) {
        Optional<Volo> voloOpt = voloService.findVoloById(idVolo);
        if (voloOpt.isPresent()) {
            model.addAttribute("volo", voloOpt.get());
            model.addAttribute("tipiAereo", aereoService.getTipiAereoOrdinati());
            model.addAttribute("isModifica", true);
            return "/admin/volo_form";
        }
        return "redirect:/admin/voli/report";
    }
    @PostMapping("/voli/modifica")
    public String modificaVolo(@RequestParam("idVolo") Integer idVolo, @RequestParam("tipoAereo") String nuovoTipoAereo) {
        System.out.println(idVolo);
        System.out.println(nuovoTipoAereo);
        boolean esito =voloService.modificaTipoAereo(idVolo, nuovoTipoAereo);
        System.out.println(esito);

        return "redirect:/admin/voli/report";
        
    }
    

}
