package com.voli.voli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voli.voli.model.Aereo;

public interface AereoRepository extends JpaRepository<Aereo, String>{

    public List<Aereo> findAllByOrderByTipoAereoAsc();

}
