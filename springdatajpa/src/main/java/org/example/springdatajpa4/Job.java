package org.example.springdatajpa4;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @Column(name = "job_id")
    private String id;

    @Column(name = "job_title")
    private String title;

    @Column(name = "min_salary")
    private Double minSalary;

    @Column(name = "max_salary")
    private Double maxSalary;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Set<JobHistory> jobHistories;
}
