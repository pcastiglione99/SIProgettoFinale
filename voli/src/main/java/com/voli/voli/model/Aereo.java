package com.voli.voli.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AEREO")
@Data
@NoArgsConstructor
public class Aereo {

    @Id
    @Column(name = "TIPO_AEREO", length = 5)
    private String tipoAereo;

    @Column(name = "NUM_PASS")
    private Integer numPass;

    @Column(name = "QTA_MERCI")
    private Integer qtaMerci;
}