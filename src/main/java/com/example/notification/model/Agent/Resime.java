package com.example.notification.model.Agent;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "resime")
public class Resime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "resime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> employeeExpriense;

    @ManyToOne
    @JoinColumn(name = "agents_id", nullable = false)
    private Agents agents;

    private String imagePath;
    private String cvPath;
    private String idPath;
}


