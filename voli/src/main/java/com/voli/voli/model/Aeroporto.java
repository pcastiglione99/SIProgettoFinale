package com.voli.voli.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AEROPORTI")
@Data
@NoArgsConstructor
public class Aeroporto {

    @Id
    @Column(name = "ID_AEROPORTO")
    private Integer idAeroporto;

    @Column(name = "CITTA", length = 30)
    private String citta;

    @Column(name = "NAZIONE", length = 30)
    private String nazione;

    @Column(name = "NUM_PISTE")
    private Integer numPiste;
}