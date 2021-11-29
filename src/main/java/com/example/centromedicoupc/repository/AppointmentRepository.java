package com.example.centromedicoupc.repository;

import com.example.centromedicoupc.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
