package com.example.centromedicoupc.resource;

import com.example.centromedicoupc.entity.AppointmentType;
import com.example.centromedicoupc.entity.Doctor;
import com.example.centromedicoupc.model.Client;

import javax.persistence.*;
import java.util.Date;

public class AppointmentResource {
    private Long id;
    private Date date;
    private String petName;
    private Long clientId;
    private Long doctorId;
    private Long typeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
