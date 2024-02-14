package com.example.namesa.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="administrator")
public class Administrator {
    public Administrator(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdministrator;
    @NotBlank(message = "Не заполнен логин")
    @Column(columnDefinition="varchar(35)")
    private String loginAdmin;
    @NotBlank(message = "Не установлен пароль")
    @Column(columnDefinition="TEXT")
    private String passwordAdmin;

    public Administrator(String loginAdmin, String passwordAdmin)
    {
        this.loginAdmin = loginAdmin;
        this.passwordAdmin = passwordAdmin;
    }

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
            this.loginAdmin = loginAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }
}
