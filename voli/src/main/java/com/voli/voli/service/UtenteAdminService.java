package com.voli.voli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voli.voli.model.UtenteAdmin;
import com.voli.voli.repository.UtenteAdminRepository;

@Service
public class UtenteAdminService {

    @Autowired
    private UtenteAdminRepository utenteAdminRepository;

    public boolean checkLogin(String username, String password) {
        UtenteAdmin utente = utenteAdminRepository.findByUsername(username);
        if (utente != null) {
            if (utente.getPassword().equals(password)) {
                return true;
            }

        }
        return false;
    }

}
