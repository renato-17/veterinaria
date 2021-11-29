package com.example.centromedicoupc.service;

import com.example.centromedicoupc.entity.Appointment;

public interface AppointmentService extends CrudService<Appointment,Long> {
    Appointment save(Appointment appointment, Long clientId, Long doctorId, Long appointmentTypeId);
}
