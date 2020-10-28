package com.example.medicare.model;

import androidx.annotation.NonNull;

public class Register {
    String fullName,email,phone,Register;

    public Register() {
    }

    public Register(String fullName, String email, String phone, String register) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        Register = register;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegister() {
        return Register;
    }

    public void setRegister(String register) {
        Register = register;
    }

    @NonNull
    @Override
    public String toString() {
        return fullName;
    }
}
