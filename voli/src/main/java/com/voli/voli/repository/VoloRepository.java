package com.voli.voli.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.voli.voli.model.Volo;

public interface VoloRepository extends JpaRepository<Volo, Integer> {

    @Query("SELECT v " +
            "FROM Volo v " +
            "WHERE v.giorno >= :giorno " +
            "ORDER BY v.giorno, v.oraPartenza")
    public List<Volo> cercaVoliSuccessiviGiorno(LocalDate giorno);

    @Query("SELECT v " +
            "FROM Volo v JOIN v.aereo " +
            "WHERE v.cittaPartenza = :cittaPartenza AND v.cittaArrivo = :cittaArrivo AND v.giorno >= :giorno " +
            "ORDER BY v.giorno ASC, v.oraPartenza")
    public List<Volo> cercaVoli(String cittaPartenza, String cittaArrivo, LocalDate giorno);

    @Query("SELECT v FROM Volo v WHERE " +
            "(:data IS NULL OR v.giorno = :data) AND " +
            "(:partenza IS NULL OR LOWER(v.cittaPartenza) LIKE LOWER(CONCAT('%', :partenza, '%'))) AND " +
            "(:destinazione IS NULL OR LOWER(v.cittaArrivo) LIKE LOWER(CONCAT('%', :destinazione, '%')))")
    List<Volo> findByFiltri(@Param("data") LocalDate data,
            @Param("partenza") String partenza,
            @Param("destinazione") String destinazione);

}
