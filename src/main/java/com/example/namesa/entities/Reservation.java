package com.example.namesa.entities;

import com.example.namesa.enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservation;
    @NotNull
    private LocalDateTime timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reserved_table")
    private com.example.namesa.entities.Table table;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="visitor")
    private Visitor visitor;
    private String isActive;

    public Reservation(){this.isActive = String.valueOf(ReservationStatus.Назначена);}
    public Reservation(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
        this.isActive = String.valueOf(ReservationStatus.Назначена);
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public String getIsActive() {
        return isActive;
    }

    public com.example.namesa.entities.Table getTable() {
        return table;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public void setTable(com.example.namesa.entities.Table table) {
        this.table = table;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
