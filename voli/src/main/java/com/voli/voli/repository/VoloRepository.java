package com.voli.voli.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.voli.voli.model.Volo;

public interface VoloRepository extends JpaRepository<Volo, Integer> {

        /*
         * Method to find all future flights for a given day and specific city
         * 
         * @param giorno the date of the flight
         * 
         * @param cittaPartenza the departure city of the flight
         * 
         * @param cittaArrivo the arrival city of the flight
         * 
         * @return a list of Volo objects representing the future flights for the given
         * day and specific cities
         */
        @Query("""
                        SELECT v
                        FROM Volo v JOIN v.aereo a
                        WHERE v.cittaPartenza = :cittaPartenza AND v.cittaArrivo = :cittaArrivo AND v.giorno >= :giorno
                        ORDER BY v.giorno ASC, v.oraPartenza
                        """)
        public List<Volo> cercaVoli(String cittaPartenza, String cittaArrivo, LocalDate giorno);

        /**
         * Method to find all flights that match the given filters
         *
         * @param data         the date of the flight (null if not specified)
         * @param partenza     the departure city of the flight (null if not specified)
         * @param destinazione the arrival city of the flight (null if not specified)
         * @param pageable     the pagination settings
         * @return a list of Volo objects representing the flights that match the given
         *         filters, ordered by date and departure time
         */
        @Query("""
                        SELECT v FROM Volo v
                        WHERE (:data IS NULL OR v.giorno = :data)
                        AND   (:partenza IS NULL OR LOWER(v.cittaPartenza)   LIKE LOWER(CONCAT('%', :partenza, '%')))
                        AND   (:destinazione IS NULL OR LOWER(v.cittaArrivo) LIKE LOWER(CONCAT('%', :destinazione, '%')))
                        ORDER BY v.giorno ASC, v.oraPartenza
                        """)
        Page<Volo> findByFiltri(LocalDate data, String partenza, String destinazione, Pageable pageable);

}