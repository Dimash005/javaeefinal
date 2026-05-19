package com.lab.BolatDinmukhamed.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BolatDinmukhamedProfessorRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100)
    private String lastName;

    @Email(message = "Email must be valid")
    @Size(max = 100)
    private String email;

    private LocalDate hireDate;

    @Size(max = 100)
    private String specialization;

    @Size(max = 50)
    private String academicTitle;
}
