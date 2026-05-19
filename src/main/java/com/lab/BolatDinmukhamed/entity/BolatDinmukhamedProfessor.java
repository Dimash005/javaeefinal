package com.lab.BolatDinmukhamed.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "courses")
@EqualsAndHashCode(of = "id")
public class BolatDinmukhamedProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(unique = true, length = 100)
    private String email;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(length = 100)
    private String specialization;

    @Column(name = "academic_title", length = 50)
    private String academicTitle;

    @ManyToMany(mappedBy = "professors", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<BolatDinmukhamedCourse> courses = new HashSet<>();
}
