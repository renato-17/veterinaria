package com.example.centromedicoupc.service;

import com.example.centromedicoupc.entity.Appointment;
import com.example.centromedicoupc.entity.AppointmentType;
import com.example.centromedicoupc.entity.Doctor;
import com.example.centromedicoupc.exceptions.ResourceNotFoundException;
import com.example.centromedicoupc.model.Client;
import com.example.centromedicoupc.repository.AppointmentRepository;
import com.example.centromedicoupc.repository.AppointmentTypeRepository;
import com.example.centromedicoupc.repository.ClientRepository;
import com.example.centromedicoupc.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;

    private final ClientRepository clientRepository = new ClientRepository();
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public Appointment save(Appointment appointment, Long clientId, Long doctorId, Long appointmentTypeId) {
        Client client = clientRepository.getById(clientId);
        if(client == null)
            throw new ResourceNotFoundException("Client not found");
        AppointmentType appointmentType = appointmentTypeRepository.findById(appointmentTypeId)
                .orElseThrow(()-> new ResourceNotFoundException("AppointmentType","Id",appointmentTypeId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor","Id",doctorId));

        appointment.setClient(client);
        appointment.setDoctor(doctor);
        appointment.setType(appointmentType);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Page<Appointment> findAll(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public Appointment findById(Long aLong) {
        return appointmentRepository.findById(aLong)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment","Id",aLong));
    }

    @Override
    public Appointment update(Long aLong, Appointment newEntity) {
        Appointment appointment = appointmentRepository.findById(aLong)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment","Id",aLong));

        appointment.setDate(newEntity.getDate());
        appointment.setPetName(newEntity.getPetName());
        return appointmentRepository.save(appointment);
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        Appointment appointment = appointmentRepository.findById(aLong)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment","Id",aLong));

        appointmentRepository.delete(appointment);
        return ResponseEntity.ok().build();
    }
}
