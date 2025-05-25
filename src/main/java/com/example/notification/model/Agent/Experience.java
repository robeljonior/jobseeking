package com.example.notification.model.Agent;

import com.example.notification.model.Agent.Agents;
import com.example.notification.model.Jobs.SubCategory;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "experience") // Optional, but good practice to control table naming
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne // Many experiences can be associated with one subcategory
    @JoinColumn(name = "subcatagory_id", nullable = false)
    private SubCategory subCategory;

    private String numberOfYears;
    @ManyToOne
    @JoinColumn(name = "resime_id") // This will create the FK column in Experience table
    private Resime resime;


}


