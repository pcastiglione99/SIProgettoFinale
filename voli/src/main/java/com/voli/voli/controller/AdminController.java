package com.voli.voli.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voli.voli.model.Volo;
import com.voli.voli.service.VoloService;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private VoloService voloService;


    @GetMapping("/voli/report")
    public List<Volo> getFutureFlights(
    @RequestParam("data") LocalDate data
    ) {
        //LocalDate oggi = LocalDate.now();
        return voloService.getFutureFlights(data);
    }


}
