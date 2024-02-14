package com.example.namesa.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="restaurant_company")
public class RestaurantCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRestaurant;
    @NotBlank(message = "Не указано имя ресторана")
    @Column(unique = true)
    @Size(message = "Название должно содержать от 5 до 50 символов", min = 5, max = 50)
    private String nameRestaurant;

    @OneToMany(mappedBy = "idBranch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Branch> branches;
    @OneToMany(mappedBy = "idDish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantDish> restaurantDishes;

    public RestaurantCompany(){}
    public RestaurantCompany(String nameRestaurant){this.nameRestaurant = nameRestaurant;}

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        idRestaurant = idRestaurant;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }
}
