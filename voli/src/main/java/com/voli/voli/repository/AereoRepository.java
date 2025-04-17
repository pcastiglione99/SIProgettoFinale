package com.voli.voli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voli.voli.model.Aereo;

public interface AereoRepository extends JpaRepository<Aereo, String>{

    /**
     * Retrieves a list of all planes sorted by their tipoAereo in ascending order.
     *
     * @return a List containing all aereos ordered by tipoAereo
     */
    public List<Aereo> findAllByOrderByTipoAereoAsc();

}
