package com.voli.voli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voli.voli.model.Aereo;

public interface AereoRepository extends JpaRepository<Aereo, String>{

    // Method to find all aeroplanes ordered by type in ascending order
    public List<Aereo> findAllByOrderByTipoAereoAsc();

}
