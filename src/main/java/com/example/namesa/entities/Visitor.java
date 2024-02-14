package com.example.namesa.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="visitor")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVisitor;
    @NotBlank(message = "Не указано Имя пользователя")
    @Size(message = "Имя пользователя должно быть от 5 до 20 символов", min=5, max=20)
    private String visitorUsername;
    @Column(unique = true)
    @NotBlank(message = "Не указан логин")
    private String visitorLogin;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Введите пароль")
    private String visitorPassword;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Введите адрес почты")
    private String eMail;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Введите номер телефона")
    private String phoneNumber;
    private boolean isDeleted;
    private boolean isBlocked;

    public Visitor(){this.isDeleted = false;
        this.isBlocked = false;}
    public Visitor(String visitorUsername, String visitorLogin,
                   String visitorPassword, String eMail,
                   String phoneNumber)
    {
        this.visitorUsername = visitorUsername;
        this.visitorLogin = visitorLogin;
        this.visitorPassword = visitorPassword;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.isDeleted = false;
        this.isBlocked = false;
    }
    @OneToMany(mappedBy = "idReservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public String getEMail() {
        return eMail;
    }

    public int getIdVisitor() {
        return idVisitor;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getVisitorLogin() {
        return visitorLogin;
    }

    public String getVisitorPassword() {
        return visitorPassword;
    }

    public String getVisitorUsername() {
        return visitorUsername;
    }

    public void setIdVisitor(int idVisitor) {
        idVisitor = idVisitor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setVisitorLogin(String visitorLogin) {
        this.visitorLogin = visitorLogin;
    }

    public void setVisitorPassword(String visitorPassword) {
        this.visitorPassword = visitorPassword;
    }

    public void setVisitorUsername(String visitorUsername) {
        this.visitorUsername = visitorUsername;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
