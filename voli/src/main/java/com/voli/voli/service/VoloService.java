package com.voli.voli.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voli.voli.model.Volo;
import com.voli.voli.repository.VoloRepository;

@Service
public class VoloService {

    @Autowired
    private VoloRepository voloRepository;

    public List<Volo> getFutureFlights(LocalDate giorno) {
        return voloRepository.cercaVoliSuccessiviGiorno(giorno);
    }



    public List<Volo> getAvailableFlights(String cittaPartenza, String cittaArrivo, LocalDate giorno, int pesoBagaglio) {

            List<Volo> results = voloRepository.cercaVoliAereo(cittaPartenza, cittaArrivo, giorno);
            return results;

        }

}
