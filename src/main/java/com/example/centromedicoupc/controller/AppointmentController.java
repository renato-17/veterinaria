package com.example.centromedicoupc.controller;

import com.example.centromedicoupc.entity.Appointment;
import com.example.centromedicoupc.resource.AppointmentResource;
import com.example.centromedicoupc.resource.SaveAppointmentResource;
import com.example.centromedicoupc.service.AppointmentService;
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
public class AppointmentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public Page<AppointmentResource> getAllAppointments(Pageable pageable){
        Page<Appointment> appointmentPage = appointmentService.findAll(pageable);

        List<AppointmentResource> resources = appointmentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @GetMapping("/appointments/{id}")
    public AppointmentResource fetchById(@PathVariable(name = "id") Long id){
        return convertToResource(appointmentService.findById(id));
    }
    @PostMapping("/appointments")
    public AppointmentResource postAppointment(@Valid @RequestBody SaveAppointmentResource resource,
                                               @RequestParam("client") Long clientId,
                                               @RequestParam("doctor") Long doctorId,
                                               @RequestParam("type") Long typeId){
        Appointment appointment = convertToEntity(resource);
        return convertToResource(appointmentService.save(appointment,clientId,doctorId,typeId));
    }

    @PutMapping("/appointments/{id}")
    public AppointmentResource updateAppointment(@Valid @RequestBody SaveAppointmentResource resource, @PathVariable(name = "id") Long id){
        Appointment appointment = convertToEntity(resource);
        return convertToResource(appointmentService.update(id,appointment));
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable(name = "id") Long id){
        return appointmentService.delete(id);
    }


    private AppointmentResource convertToResource(Appointment appointment){return mapper.map(appointment,AppointmentResource.class);}
    private Appointment convertToEntity(SaveAppointmentResource resource){return mapper.map(resource,Appointment.class);}
}
