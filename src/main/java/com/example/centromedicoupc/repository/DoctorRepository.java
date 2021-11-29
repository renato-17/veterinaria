package com.example.centromedicoupc.repository;

import com.example.centromedicoupc.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
