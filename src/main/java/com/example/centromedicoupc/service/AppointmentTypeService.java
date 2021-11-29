package com.example.centromedicoupc.service;

import com.example.centromedicoupc.entity.Appointment;
import com.example.centromedicoupc.entity.AppointmentType;

public interface AppointmentTypeService extends CrudService<AppointmentType,Long> {
    AppointmentType save(AppointmentType appointment);
}
