package com.example.namesa.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBranch;
    @NotBlank(message = "Не заполнен адрес")
    @Column(columnDefinition="TEXT")
    private String addressBranch;
    @NotBlank(message = "Не дано название филиала")
    @Size(message = "Название должно быть от 5 до 50 символов", min=5, max=50)
    private String nameBranch;
    @Column(columnDefinition = "TEXT")
    private String mapBranch;
    @OneToMany(mappedBy = "idTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.example.namesa.entities.Table> tables;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurantCompany")
    private RestaurantCompany restaurantCompany;
    @OneToMany(mappedBy = "idBranchDish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BranchDish> dishes;
    private int branchReservationsCount;
    public Branch(){}
    public Branch(String addressBranch, String nameBranch, String mapBranch)
    {
        this.addressBranch = addressBranch;
        this.nameBranch = nameBranch;
        this.mapBranch = mapBranch;
        this.branchReservationsCount = 0;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public int getBranchReservationsCount() {
        return branchReservationsCount;
    }

    public String getAddressBranch() {
        return addressBranch;
    }

    public String getMapBranch() {
        return mapBranch;
    }

    public void setIdBranch(int idBranch) {
        idBranch = idBranch;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public void setAddressBranch(String addressBranch) {
        this.addressBranch = addressBranch;
    }

    public void setBranchReservationsCount(int branchReservationsCount) {
        this.branchReservationsCount = branchReservationsCount;
    }

    public void setMapBranch(String mapBranch) {
        this.mapBranch = mapBranch;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public RestaurantCompany getRestaurantCompany() {
        return restaurantCompany;
    }
}
