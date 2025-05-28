package com.example.notification.model.Jobs;


import com.example.notification.model.Agent.Agents;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship to Job
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private CreateJobs job;

    // Relationship to Agent
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agents agent;

    @Column(columnDefinition = "TEXT")
    private String coverLetter;

    private String applicationStatus;

    private LocalDateTime appliedAt;

    @PrePersist
    protected void onApply() {
        this.appliedAt = LocalDateTime.now();
        this.applicationStatus = "PENDING";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateJobs getJob() {
        return job;
    }

    public void setJob(CreateJobs job) {
        this.job = job;
    }

    public Agents getAgent() {
        return agent;
    }

    public void setAgent(Agents agent) {
        this.agent = agent;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
