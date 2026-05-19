package com.lab.BolatDinmukhamed.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"professors", "enrollments", "grades"})
@EqualsAndHashCode(of = "id")
public class BolatDinmukhamedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(name = "course_code", unique = true, length = 20)
    private String courseCode;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    @Builder.Default
    private Integer credits = 3;

    @Column(name = "max_students", nullable = false)
    @Builder.Default
    private Integer maxStudents = 30;

    @Column(name = "available_seats", nullable = false)
    @Builder.Default
    private Integer availableSeats = 30;

    @Column(length = 50)
    private String semester;

    @Column(name = "syllabus_url", length = 500)
    private String syllabusUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private BolatDinmukhamedDepartment department;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "courses_professors",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    @Builder.Default
    private Set<BolatDinmukhamedProfessor> professors = new HashSet<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<BolatDinmukhamedEnrollment> enrollments = new HashSet<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<BolatDinmukhamedGrade> grades = new HashSet<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
