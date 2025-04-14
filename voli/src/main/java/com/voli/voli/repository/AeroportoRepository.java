package com.voli.voli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voli.voli.model.Aeroporto;

public interface AeroportoRepository extends JpaRepository<Aeroporto, Integer>{

    public List<Aeroporto> findAllByOrderByCittaAsc();

}
