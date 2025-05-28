package com.example.notification.dto.request.jobs;

import com.example.notification.model.Jobs.SubCategory;
public class CreateJobsDto {


    private long id;

    private long jobId;

    private String jobtitle;


    private String description;

    private String status;

    private String forCompany;

    public String getForCompany() {
        return forCompany;
    }

    public void setForCompany(String forCompany) {
        this.forCompany = forCompany;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
