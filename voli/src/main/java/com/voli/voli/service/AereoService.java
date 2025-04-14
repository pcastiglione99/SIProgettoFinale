package com.voli.voli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voli.voli.model.Aereo;
import com.voli.voli.repository.AereoRepository;

@Service
public class AereoService {

    @Autowired
    private AereoRepository aereoRepository;

    public List<Aereo> findAll(){
        return aereoRepository.findAll();
    }



}
