package com.example.centromedicoupc.resource;

import com.example.centromedicoupc.entity.AppointmentType;
import com.example.centromedicoupc.entity.Doctor;
import com.example.centromedicoupc.model.Client;

import javax.persistence.*;
import java.util.Date;

public class SaveAppointmentResource {
    private Date date;
    private String petName;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
