package com.example.centromedicoupc.service;

import com.example.centromedicoupc.entity.Doctor;
import com.example.centromedicoupc.exceptions.ResourceNotFoundException;
import com.example.centromedicoupc.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Page<Doctor> findAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Doctor findById(Long aLong) {
        return doctorRepository.findById(aLong).orElseThrow(()-> new ResourceNotFoundException("Doctor","Id",aLong));
    }

    @Override
    public Doctor update(Long aLong, Doctor newEntity) {
        Doctor doctor = doctorRepository.findById(aLong)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor","Id",aLong));

        doctor.setName(newEntity.getName());
        doctor.setAvailable(newEntity.getAvailable());
        return doctorRepository.save(doctor);
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        Doctor doctor = doctorRepository.findById(aLong)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor","Id",aLong));

        doctorRepository.delete(doctor);
        return ResponseEntity.ok().build();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
