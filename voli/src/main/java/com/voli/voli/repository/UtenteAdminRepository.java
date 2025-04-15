package com.voli.voli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voli.voli.model.UtenteAdmin;


public interface UtenteAdminRepository extends JpaRepository<UtenteAdmin, Integer>{

    UtenteAdmin findByUsername(String username);

}
