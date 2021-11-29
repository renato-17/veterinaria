package com.example.centromedicoupc.entity;

import com.example.centromedicoupc.model.Client;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "type_id")
    private Long typeId;

    @Transient
    private AppointmentType type;

    @Transient
    private Doctor doctor;

    @Transient
    private Client client;

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

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
