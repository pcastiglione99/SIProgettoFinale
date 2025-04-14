package com.voli.voli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voli.voli.model.Aeroporto;
import com.voli.voli.repository.AeroportoRepository;

@Service
public class AeroportoService {

    @Autowired
    private AeroportoRepository aeroportoRepository;

    public List<Aeroporto> getAeroportiOrdinati() {
        return aeroportoRepository.findAllByOrderByCittaAsc();
    }


}
