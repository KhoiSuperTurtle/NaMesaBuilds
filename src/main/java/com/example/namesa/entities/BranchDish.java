package com.example.namesa.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="branch_dish")
public class BranchDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBranchDish;
    private boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branch")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dish")
    private RestaurantDish restaurantDish;

    public BranchDish(){isActive=true;}
}
