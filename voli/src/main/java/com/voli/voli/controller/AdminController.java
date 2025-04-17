package com.voli.voli.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/dashboard")
    public String dashBoard() {
        return "admin/dashboard";
    }

    @GetMapping("/voli/report")
    public String getFutureFlights(
            Model model,
            @RequestParam(name = "data", required = false) LocalDate data,
            @RequestParam(name = "cittaPartenza", required = false) String cittaPartenza,
            @RequestParam(name = "cittaArrivo", required = false) String cittaArrivo,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Volo> voliFuturi = voloService.filtraVoli(data, cittaPartenza, cittaArrivo, pageable);
        model.addAttribute("data", data);
        model.addAttribute("cittaPartenza", cittaPartenza);
        model.addAttribute("cittaArrivo", cittaArrivo);

        model.addAttribute("aeroporti", aeroportoService.getAeroportiOrdinati());
        model.addAttribute("voliFuturi", voliFuturi);
        model.addAttribute("paginaCorrente", page);
        model.addAttribute("totalePagine", voliFuturi.getTotalPages());
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

    @PostMapping("/voli/cancella/{id:\\d+}")
    public String cancellaVolo(@PathVariable("id") Integer idVolo) {

        voloService.cancellaVolo(idVolo);

        return "redirect:/admin/voli/report";
    }

    @GetMapping("/voli/modifica/{id:\\d+}")
    public String modificaVoloForm(@PathVariable("id") Integer idVolo, Model model) {
        
        return voloService.findVoloById(idVolo).
            map(volo -> {
                model.addAttribute("volo", volo);
                model.addAttribute("tipiAereo", aereoService.getTipiAereoOrdinati());
                model.addAttribute("isModifica", true);
                return "/admin/volo_form";
            })
            .orElse("redirect:/admin/voli/report");
    }

    @PostMapping("/voli/modifica")
    public String modificaVolo(@RequestParam("idVolo") Integer idVolo, @RequestParam("tipoAereo") String nuovoTipoAereo) {

        if(voloService.modificaTipoAereo(idVolo, nuovoTipoAereo));       
        return "redirect:/admin/voli/report";

    }

}
