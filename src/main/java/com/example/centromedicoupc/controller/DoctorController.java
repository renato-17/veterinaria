package com.example.centromedicoupc.controller;

import com.example.centromedicoupc.entity.Doctor;
import com.example.centromedicoupc.resource.DoctorResource;
import com.example.centromedicoupc.resource.SaveDoctorResource;
import com.example.centromedicoupc.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public Page<DoctorResource> getAllDoctors(Pageable pageable){
        Page<Doctor> doctorPage = doctorService.findAll(pageable);

        List<DoctorResource> resources = doctorPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @GetMapping("/doctors/{id}")
    public DoctorResource fetchById(@PathVariable(name = "id") Long id){
        return convertToResource(doctorService.findById(id));
    }
    @PostMapping("/doctors")
    public DoctorResource postDoctor(@Valid @RequestBody SaveDoctorResource resource){
        Doctor doctor = convertToEntity(resource);
        return convertToResource(doctorService.save(doctor));
    }

    @PutMapping("/doctors/{id}")
    public DoctorResource updateDoctor(@Valid @RequestBody SaveDoctorResource resource, @PathVariable(name = "id") Long id){
        Doctor doctor = convertToEntity(resource);
        return convertToResource(doctorService.update(id,doctor));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable(name = "id") Long id){
        return doctorService.delete(id);
    }


    private DoctorResource convertToResource(Doctor doctor){return mapper.map(doctor,DoctorResource.class);}
    private Doctor convertToEntity(SaveDoctorResource resource){return mapper.map(resource,Doctor.class);}
}