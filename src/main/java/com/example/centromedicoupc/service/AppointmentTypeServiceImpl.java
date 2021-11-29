package com.example.centromedicoupc.service;

import com.example.centromedicoupc.entity.AppointmentType;
import com.example.centromedicoupc.exceptions.ResourceNotFoundException;
import com.example.centromedicoupc.repository.AppointmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {
    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public Page<AppointmentType> findAll(Pageable pageable) {
        return appointmentTypeRepository.findAll(pageable);
    }

    @Override
    public AppointmentType findById(Long aLong) {
        return appointmentTypeRepository.findById(aLong).orElseThrow(()-> new ResourceNotFoundException("AppointmentType","Id",aLong));
    }

    @Override
    public AppointmentType update(Long aLong, AppointmentType newEntity) {
        AppointmentType appointmentType = appointmentTypeRepository.findById(aLong)
                .orElseThrow(()-> new ResourceNotFoundException("AppointmentType","Id",aLong));

        appointmentType.setName(newEntity.getName());
        return appointmentTypeRepository.save(appointmentType);
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        AppointmentType appointmentType = appointmentTypeRepository.findById(aLong)
                .orElseThrow(()-> new ResourceNotFoundException("AppointmentType","Id",aLong));

        appointmentTypeRepository.delete(appointmentType);
        return ResponseEntity.ok().build();
    }

    @Override
    public AppointmentType save(AppointmentType appointmentType) {
        return appointmentTypeRepository.save(appointmentType);
    }
}
