package com.example.namesa.repos;

import com.example.namesa.entities.Reservation;
import com.example.namesa.entities.Table;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    public List<Reservation> findAllByTable(Table table);
    @Query("select r from Reservation  r inner join Table t on r.table = t where timestamp>?1 and timestamp<?2 and t.codeTable=?3 ")
    public List<Reservation> findAllByTimestamps(LocalDateTime timestampBefore, LocalDateTime timestampAfter,String table);
}
