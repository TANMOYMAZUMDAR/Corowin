package com.example.corowin.model;

public class VaccineModel {

    private String vaccineCentre;
    private String vaccinationCharges;
    private int vaccinationAge;
    private String vaccinationTimings;

    public String getVaccineCentre() {
        return vaccineCentre;
    }

    public void setVaccineCentre(String vaccineCentre) {
        this.vaccineCentre = vaccineCentre;
    }

    public String getVaccinationCharges() {
        return vaccinationCharges;
    }

    public void setVaccinationCharges(String vaccinationCharges) {
        this.vaccinationCharges = vaccinationCharges;
    }

    public int getVaccinationAge() {
        return vaccinationAge;
    }

    public void setVaccinationAge(int vaccinationAge) {
        this.vaccinationAge = vaccinationAge;
    }

    public String getVaccinationTimings() {
        return vaccinationTimings;
    }

    public void setVaccinationTimings(String vaccinationTimings) {
        this.vaccinationTimings = vaccinationTimings;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineCenterTime() {
        return vaccineCenterTime;
    }

    public void setVaccineCenterTime(String vaccineCenterTime) {
        this.vaccineCenterTime = vaccineCenterTime;
    }

    public int getVaccineAvailaibility() {
        return vaccineAvailaibility;
    }

    public void setVaccineAvailaibility(int vaccineAvailaibility) {
        this.vaccineAvailaibility = vaccineAvailaibility;
    }

    public String getVaccineCenterAddress() {
        return vaccineCenterAddress;
    }

    public void setVaccineCenterAddress(String vaccineCenterAddress) {
        this.vaccineCenterAddress = vaccineCenterAddress;
    }

    private String vaccineName;
    private String vaccineCenterTime;
    private int vaccineAvailaibility;
    private String vaccineCenterAddress;

public  VaccineModel() {
}

    public VaccineModel(String vaccineCentre, String vaccinationCharges, int vaccinationAge, String vaccinationTimings, String vaccineName, String vaccineCenterTime, String vaccineCenterAddress, int vaccineAvailaibility) {
        this.vaccineCentre = vaccineCentre;
        this.vaccinationCharges = vaccinationCharges;
        this.vaccinationAge = vaccinationAge;
        this.vaccinationTimings = vaccinationTimings;
        this.vaccineName = vaccineName;
        this.vaccineCenterTime = vaccineCenterTime;
        this.vaccineCenterAddress = vaccineCenterAddress;
        this.vaccineAvailaibility = vaccineAvailaibility;
    }
}
