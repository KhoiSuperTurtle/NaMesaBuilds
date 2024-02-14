package com.example.namesa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@jakarta.persistence.Table(name="app_table")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTable;
    @NotBlank(message = "Не указано число мест")
    private int seatsNumber;
    @NotBlank(message = "Не указан номер стола")
    private String codeTable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branch")
    private Branch branch;
    @OneToMany(mappedBy = "idReservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public Table(){}
    public Table(int seatsNumber){this.seatsNumber = seatsNumber;}

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getCodeTable() {
        return codeTable;
    }
}
