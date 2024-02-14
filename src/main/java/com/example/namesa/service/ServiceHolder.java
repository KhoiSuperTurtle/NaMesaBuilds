package com.example.namesa.service;

import com.example.namesa.entities.*;
import com.example.namesa.repos.*;
//import org.springframework.security.core.Authentication;
import org.jboss.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ServiceHolder {
    private final AdministratorsRepo administratorsRepo;
    private final BranchDishesRepo branchDishesRepo;
    private final BranchesRepo branchesRepo;
    private final PartnerManagerRepo partnerManagerRepo;
    private final ReservationRepo reservationRepo;
    private final RestaurantCompanyRepo restaurantCompanyRepo;
    private final RestaurantDishRepo restaurantDishRepo;
    private final TableRepo tableRepo;
    private final VisitorRepo visitorRepo;
    public ServiceHolder(AdministratorsRepo administratorsRepo, BranchDishesRepo branchDishesRepo
    ,BranchesRepo branchesRepo, PartnerManagerRepo partnerManagerRepo,ReservationRepo reservationRepo
            ,RestaurantCompanyRepo restaurantCompanyRepo, RestaurantDishRepo restaurantDishRepo
    ,TableRepo tableRepo, VisitorRepo visitorRepo)
    {
        this.administratorsRepo = administratorsRepo;
        this.branchDishesRepo = branchDishesRepo;
        this.branchesRepo = branchesRepo;
        this.reservationRepo = reservationRepo;
        this.tableRepo = tableRepo;
        this.partnerManagerRepo = partnerManagerRepo;
        this.restaurantCompanyRepo = restaurantCompanyRepo;
        this.restaurantDishRepo = restaurantDishRepo;
        this.visitorRepo = visitorRepo;
    }
    public boolean register(Visitor user)
    {
        if (visitorRepo.findVisitorByVisitorLogin(user.getVisitorLogin())!=null)
            return false; //Проверка на существование пользователя с таким логинов в базе
        visitorRepo.saveAndFlush(user); //Сохранение, если нет
        return true;
    }
    public List<Reservation> getRowsByTables(List<Table> tables, LocalDateTime timestamp){
        List<Reservation> reservations = new ArrayList<>();
        for (Table table: tables
             ) {
              List<Reservation> tableReservations = reservationRepo.findAllByTable(table);
              for (Reservation res: tableReservations)
              {
                  if (res.getTimestamp().getYear()==timestamp.getYear() &&
                  res.getTimestamp().getMonth()==timestamp.getMonth() &&
                  res.getTimestamp().getDayOfMonth()==timestamp.getDayOfMonth())
                  {
                      reservations.add(res);
                  }
              }
        }
        return reservations;
    }
    public List<Branch> getRowsBranches()
    {
        return branchesRepo.findAll();
    }
    public Branch getBranchById(int id){return branchesRepo.findBranchByIdBranch(id);}
    public List<Table> getTablesByBranch(Branch branch){return tableRepo.findAllByBranch(branch);}

    public boolean book(LocalDateTime timestamp, Table table){
        Reservation reservation = new Reservation(timestamp);
        try {
            reservation.setTable(table);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        }
        catch(AuthenticationException authenticationException)
        {
            Logger.getLogger("Не удалось получить данные об аутентификации. Проверьте подключение.")
                    .debug(authenticationException.getMessage());
        }
        finally {

        }
        String currentPrincipalName = authentication.getName();
        reservation.setVisitor(visitorRepo.findVisitorByVisitorLogin(currentPrincipalName));
        LocalDateTime before = reservation.getTimestamp().plusHours(-2);
        LocalDateTime after = reservation.getTimestamp().plusHours(2);
        List<Reservation>reserves = reservationRepo.findAllByTimestamps(before,after,table.getCodeTable());
        if(!reserves.isEmpty() && (
                reservation.getTimestamp().getYear()>= LocalDateTime.now().getYear() &&
                        reservation.getTimestamp().getDayOfYear() > LocalDateTime.now().getDayOfYear()
                ))
            return false;
        else reservationRepo.saveAndFlush(reservation);
        return true;
    }
    public Table getTable(String codeTable, Branch branch)
    { return tableRepo.findByCodeTableAndBranch(codeTable, branch);}
}
