package com.voli.voli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voli.voli.repository.UtenteAdminRepository;

@Service
public class UtenteAdminService {

    @Autowired
    private UtenteAdminRepository utenteAdminRepository;

}
