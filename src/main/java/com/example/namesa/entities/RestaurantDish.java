package com.example.namesa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@jakarta.persistence.Table(name="restaurant_dish")
public class RestaurantDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idDish;
    @NotBlank(message = "Не указано краткое описание блюда")
    @Size(message = "Описание должно быть не менее 10 символов", min=10)
    @Column(columnDefinition = "TEXT")
    public String shortDescription;
    @NotBlank(message = "Не указаны ингредиенты")
    @Size(message = "Описание должно быть не менее 10 символов", min=10)
    @Column(columnDefinition = "TEXT")
    public String ingredientList;
    @NotBlank(message = "Не указано название блюда")
    @Size(message = "Название блюда должно содержать от 2 до 50 символов", min=2, max=50)
    public String nameDish;
    @OneToMany(mappedBy = "idBranchDish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BranchDish> dishes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurantCompany")
    public RestaurantCompany restaurantCompany;
    public RestaurantDish(){}
    public RestaurantDish(String shortDescription, String ingredientList, String nameDish)
    {
        this.shortDescription = shortDescription;
        this.ingredientList = ingredientList;
        this.nameDish = nameDish;
    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public RestaurantCompany getRestaurantCompany() {
        return restaurantCompany;
    }

    public String getNameDish() {
        return nameDish;
    }

    public String getIngredientList() {
        return ingredientList;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public void setRestaurantCompany(RestaurantCompany restaurantCompany) {
        this.restaurantCompany = restaurantCompany;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setIngredientList(String ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
