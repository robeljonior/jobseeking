package com.example.notification.dto.request.jobs;

import com.example.notification.model.Agent.Agents;
import com.example.notification.model.Jobs.CreateJobs;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class AplicationDto {


    private Long id;

    // Relationship to Job
    private long job_Id;

    private String agent_email;

    private String coverLetter;

    private String applicationStatus;

    private LocalDateTime appliedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getJob_Id() {
        return job_Id;
    }

    public void setJob_Id(long job_Id) {
        this.job_Id = job_Id;
    }

    public String getAgent_email() {
        return agent_email;
    }

    public void setAgent_email(String agent_email) {
        this.agent_email = agent_email;
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
