package com.voli.voli.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VOLI")
@Data
@NoArgsConstructor
public class Volo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VOLO")
    private Integer idVolo;

    @Column(name = "GIORNO")
    private LocalDate giorno;

    @Column(name = "CITTA_PARTENZA", length = 50)
    private String cittaPartenza;

    @Column(name = "ORA_PARTENZA")
    private LocalDateTime oraPartenza;

    @Column(name = "CITTA_ARRIVO", length = 50)
    private String cittaArrivo;

    @Column(name = "ORA_ARRIVO")
    private LocalDateTime oraArrivo;

    @Column(name = "TIPO_AEREO", length = 5)
    private String tipoAereo;

    @ManyToOne(fetch = FetchType.LAZY) // Carica l'aereo solo quando serve
    @JoinColumn(name = "TIPO_AEREO", referencedColumnName = "TIPO_AEREO",  insertable = false, updatable = false)
    private Aereo aereo;

    @Column(name = "PASSEGGERI")
    private Integer passeggeri;

    @Column(name = "MERCI")
    private Integer merci;

    public Volo(LocalDate giorno, String cittaPartenza, LocalDateTime oraPartenza, String cittaArrivo, LocalDateTime oraArrivo, String tipoAereo) {
        this.giorno = giorno;
        this.cittaPartenza = cittaPartenza;
        this.oraPartenza = oraPartenza;
        this.cittaArrivo = cittaArrivo;
        this.oraArrivo = oraArrivo;
        this.tipoAereo = tipoAereo;
        this.passeggeri = 0;
        this.merci = 0;
    }
}