package com.example.centromedicoupc.service;

import com.example.centromedicoupc.entity.AppointmentType;
import com.example.centromedicoupc.entity.Doctor;

public interface DoctorService extends CrudService<Doctor,Long> {
    Doctor save(Doctor doctor);
}
