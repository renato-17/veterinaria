package com.example.centromedicoupc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CrudService <T,ID>{
    Page<T> findAll(Pageable pageable);
    T findById(ID id);
    T update(ID id,T newEntity);
    ResponseEntity<?> delete(ID id) ;
}
