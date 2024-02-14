package com.example.namesa.controllers;

import com.example.namesa.entities.Reservation;
import com.example.namesa.service.ServiceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class VisitorController {
    private final ServiceHolder serviceHolder;
    @Autowired
    public VisitorController(ServiceHolder serviceHolder) {
        this.serviceHolder = serviceHolder;
    }
    @GetMapping("/book")
    public String book(Model model, @RequestParam(name="branch")int branch)
    {
        model.addAttribute("branch", serviceHolder.getBranchById(branch));
        return "book";
    }
    @GetMapping("/book/time")
    public String bookWithTime(Model model, @RequestParam(name="branch")int branch,
                               @RequestParam(name="stamp") LocalDateTime timestamp)
    {
        model.addAttribute("reservations", serviceHolder.getRowsByTables(serviceHolder.getTablesByBranch(serviceHolder.getBranchById(branch)),timestamp));
        model.addAttribute("stamp",timestamp);
        model.addAttribute("tables",serviceHolder.getTablesByBranch(serviceHolder.getBranchById(branch)));
        model.addAttribute("branch", serviceHolder.getBranchById(branch));
        return "book/time";
    }
    @PostMapping("/book/time")
    public String book(Model model, @RequestParam(name="table")String table,
                       @RequestParam(name="stamp")LocalDateTime timestamp,
                        @RequestParam(name="branch")int branch) {
        serviceHolder.book(timestamp,serviceHolder.getTable(table,serviceHolder.getBranchById(branch) ));
        model.addAttribute("reservations", serviceHolder.getRowsByTables(serviceHolder.getTablesByBranch(serviceHolder.getBranchById(branch)),timestamp));
        model.addAttribute("stamp",timestamp);
        model.addAttribute("tables",serviceHolder.getTablesByBranch(serviceHolder.getBranchById(branch)));
        model.addAttribute("branch", serviceHolder.getBranchById(branch));
        return "book/time";
    }
    //@GetMapping("profile")

}
