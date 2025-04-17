package com.voli.voli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voli.voli.model.UtenteAdmin;


public interface UtenteAdminRepository extends JpaRepository<UtenteAdmin, Integer>{

    /*
     * Method to find a user by their username in the database
     * @param username the username of the user to be found
     * @return an instance of UtenteAdmin representing the user with the given username, or null if no such user exists
     */
    UtenteAdmin findByUsername(String username);

}
