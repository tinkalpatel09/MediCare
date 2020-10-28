package com.example.medicare.model;

import androidx.annotation.NonNull;

public class NewPatient {
    private String fileNumber,date,firstName,lastName,age,gender,streetAddress,
            aptNumber,city,zipCode,phoneNumber,bloodGroup,
            EmergencyContactName,EmergencyContactNumber,Reason;


    public NewPatient() {
    }

    public NewPatient(String fileNumber, String date, String firstName, String lastName, String age, String gender, String streetAddress, String aptNumber, String city, String zipCode, String phoneNumber, String bloodGroup, String emergencyContactName, String emergencyContactNumber, String reason) {
        this.fileNumber = fileNumber;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.streetAddress = streetAddress;
        this.aptNumber = aptNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
        EmergencyContactName = emergencyContactName;
        EmergencyContactNumber = emergencyContactNumber;
        Reason = reason;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(String aptNumber) {
        this.aptNumber = aptNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmergencyContactName() {
        return EmergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        EmergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return EmergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        EmergencyContactNumber = emergencyContactNumber;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    @NonNull
    @Override
    public String toString() {
        return firstName+lastName;
    }
}
