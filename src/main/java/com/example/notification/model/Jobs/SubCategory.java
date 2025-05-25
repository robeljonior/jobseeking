package com.example.notification.model.Jobs;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "subcatagory")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "jobs", referencedColumnName = "id")
    private Jobs jobs;
    private String subjobsName;
    private String description;
}
