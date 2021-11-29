package com.example.centromedicoupc.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
