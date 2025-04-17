package com.voli.voli.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.voli.voli.model.UtenteAdmin;
import com.voli.voli.repository.UtenteAdminRepository;

@Service
public class UtenteAdminService implements UserDetailsService {

    @Autowired
    private UtenteAdminRepository utenteAdminRepository;

    public boolean checkLogin(String username, String password)  throws UsernameNotFoundException {
        UtenteAdmin utente = utenteAdminRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        if (utente != null) {
            if (utente.getPasswordHash().equals(password)) {
                return true;
            }

        }
        return false;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtenteAdmin utente = utenteAdminRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        return new User(
                utente.getNome(),
                utente.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

}
