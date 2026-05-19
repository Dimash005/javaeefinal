package com.lab.BolatDinmukhamed.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BolatDinmukhamedProfessorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate hireDate;
    private String specialization;
    private String academicTitle;
}
