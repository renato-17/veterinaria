package com.example.centromedicoupc.controller;

import com.example.centromedicoupc.entity.AppointmentType;
import com.example.centromedicoupc.resource.AppointmentTypeResource;
import com.example.centromedicoupc.resource.SaveAppointmentTypeResource;
import com.example.centromedicoupc.service.AppointmentTypeService;
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
public class AppointmentTypeController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AppointmentTypeService appointmentTypeService;

    @GetMapping("/appointmentTypes")
    public Page<AppointmentTypeResource> getAllAppointmentTypes(Pageable pageable){
        Page<AppointmentType> appointmentTypePage = appointmentTypeService.findAll(pageable);

        List<AppointmentTypeResource> resources = appointmentTypePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @GetMapping("/appointmentTypes/{id}")
    public AppointmentTypeResource fetchById(@PathVariable(name = "id") Long id){
        return convertToResource(appointmentTypeService.findById(id));
    }
    @PostMapping("/appointmentTypes")
    public AppointmentTypeResource postAppointmentType(@Valid @RequestBody SaveAppointmentTypeResource resource){
        AppointmentType appointmentType = convertToEntity(resource);
        return convertToResource(appointmentTypeService.save(appointmentType));
    }

    @PutMapping("/appointmentTypes/{id}")
    public AppointmentTypeResource updateAppointmentType(@Valid @RequestBody SaveAppointmentTypeResource resource, @PathVariable(name = "id") Long id){
        AppointmentType appointmentType = convertToEntity(resource);
        return convertToResource(appointmentTypeService.update(id,appointmentType));
    }

    @DeleteMapping("/appointmentTypes/{id}")
    public ResponseEntity<?> deleteAppointmentType(@PathVariable(name = "id") Long id){
        return appointmentTypeService.delete(id);
    }


    private AppointmentTypeResource convertToResource(AppointmentType appointmentType){return mapper.map(appointmentType,AppointmentTypeResource.class);}
    private AppointmentType convertToEntity(SaveAppointmentTypeResource resource){return mapper.map(resource,AppointmentType.class);}
}
