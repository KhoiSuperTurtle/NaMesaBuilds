package com.example.namesa.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="partner_manager")
public class PartnerManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPartnerManager;
    @NotBlank(message = "Не заполнен логин")
    private String loginPM;
    @NotBlank(message = "Не установлен пароль")
    @Column(columnDefinition="TEXT")
    private String passwordPM;
    public PartnerManager(){}
    public PartnerManager(String loginPM, String passwordPM){
        this.loginPM = loginPM;
        this.passwordPM = passwordPM;
    }

    public String getLoginPM() {
        return loginPM;
    }

    public String getPasswordPM() {
        return passwordPM;
    }

    public void setLoginPM(String loginPM) {
        this.loginPM = loginPM;
    }

    public void setPasswordPM(String passwordPM) {
        this.passwordPM = passwordPM;
    }
}
