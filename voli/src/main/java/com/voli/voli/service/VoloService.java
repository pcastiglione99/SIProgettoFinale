package com.voli.voli.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voli.voli.model.Aereo;
import com.voli.voli.model.Volo;
import com.voli.voli.repository.AereoRepository;
import com.voli.voli.repository.VoloRepository;

import jakarta.transaction.Transactional;

@Service
public class VoloService {

    @Autowired
    private VoloRepository voloRepository;

    @Autowired
    private AereoRepository aereoRepository;

    public Optional<Volo> findVoloById(Integer idVolo) {
        return voloRepository.findById(idVolo);
    }

    public List<Volo> cercaVoliFuturi(LocalDate giorno) {
        return voloRepository.cercaVoliSuccessiviGiorno(giorno);
    }

    public List<Volo> filtraVoli(LocalDate data, String partenza, String destinazione) {
        return voloRepository.findByFiltri(data, partenza, destinazione);
    }

    /**
     * Cerca i voli disponibili dalla città di partenza alla città di arrivo
     * nel giorno specificato con un peso di bagaglio compreso.
     *
     * @param cittaPartenza la città di partenza del volo
     * @param cittaArrivo   la città di arrivo del volo
     * @param giorno        il giorno del volo
     * @param pesoBagaglio  il peso del bagaglio
     * @return una lista di voli disponibili che soddisfano i criteri specificati
     */
    public List<Volo> cercaVoliDisponibili(String cittaPartenza, String cittaArrivo, LocalDate giorno,
            int pesoBagaglio) {

        List<Volo> results = voloRepository.cercaVoli(cittaPartenza, cittaArrivo, giorno);

        return results.stream()
                .filter(volo -> {
                    Aereo aereo = volo.getAereo();
                    if (aereo == null)
                        return false; // Sicurezza: se aereo non trovato, scarta

                    boolean postiOk = volo.getPasseggeri() < aereo.getNumPass();
                    boolean bagaglioOk = (volo.getMerci() + pesoBagaglio) <= aereo.getQtaMerci();
                    return postiOk && bagaglioOk;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void aggiungiVolo(Volo nuovoVolo) {

        nuovoVolo.setPasseggeri(0);
        nuovoVolo.setMerci(0);

        voloRepository.save(nuovoVolo);

    }

    @Transactional
    public boolean modificaTipoAereo(int idAereo, String nuovoTipoAereo) {
        Optional<Volo> voloOpt = voloRepository.findById(idAereo);
        Optional<Aereo> aereoOpt = aereoRepository.findById(nuovoTipoAereo);

        if (voloOpt.isPresent() && aereoOpt.isPresent()) {

            Volo volo = voloOpt.get();
            Aereo aereo = aereoOpt.get();

            if (volo.getPasseggeri() > aereo.getNumPass()) {
                System.err.println("Errore: Il nuovo aereo non ha capacità passeggeri sufficiente");
                return false;
            }
            if (volo.getMerci() > aereo.getQtaMerci()) {
                System.err.println("Errore: Il nuovo aereo non ha capacità merci sufficiente");
                return false;
            }

            volo.setTipoAereo(nuovoTipoAereo);
            voloRepository.save(volo);
            return true;

        }

        return false;

    }

    public void cancellaVolo(Integer idVolo) {
        Volo volo = voloRepository.getReferenceById(idVolo);
        voloRepository.delete(volo);
    }

}
