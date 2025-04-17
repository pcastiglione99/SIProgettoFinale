package com.voli.voli.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Finds a volo by its ID.
     *
     * @param idVolo the ID of the volo to find
     * @return an Optional containing the found volo, or empty if not found
     */
    public Optional<Volo> findVoloById(Integer idVolo) {
        return voloRepository.findById(idVolo);
    }

    public Page<Volo> filtraVoli(LocalDate data, String partenza, String destinazione, Pageable pageable) {
        return voloRepository.findByFiltri(data, partenza, destinazione, pageable);
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

        return voloRepository.cercaVoli(cittaPartenza, cittaArrivo, giorno).stream()
                .filter(volo -> {
                    Aereo aereo = volo.getAereo();
                    return aereo != null &&
                    volo.getPasseggeri() < aereo.getNumPass() &&
                    (volo.getMerci() + pesoBagaglio) <= aereo.getQtaMerci();
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
    public boolean modificaTipoAereo(int idVolo, String nuovoTipoAereo) {
        Optional<Volo> voloOpt = voloRepository.findById(idVolo);
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