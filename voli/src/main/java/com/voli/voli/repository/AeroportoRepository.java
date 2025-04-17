package com.voli.voli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voli.voli.model.Aeroporto;

public interface AeroportoRepository extends JpaRepository<Aeroporto, Integer>{

    /**
     * Retrieves all airports sorted by city in ascending order.
     *
     * @return A list of all airports sorted by city in ascending order.
     */
    public List<Aeroporto> findAllByOrderByCittaAsc();

}
