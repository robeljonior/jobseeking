package com.example.notification.model.Jobs;

import com.example.notification.model.Jobs.SubCategory;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "create_jobs")
public class CreateJobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory jobtitle;

    @Column(name = "description", nullable = false, length = 10000)
    private String description;

    private String status;

    private String forCompany;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SubCategory getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(SubCategory jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}


