package com.example.notification.service.jobsAndAplication;

import com.example.notification.Repository.CreateJobsRepository;
import com.example.notification.Repository.SubJobsRepository;
import com.example.notification.dto.request.jobs.CreateJobsDto;
import com.example.notification.model.Jobs.CreateJobs;
import com.example.notification.model.Jobs.SubCategory;
import com.example.notification.response.BaseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateJobsService {
    @Autowired
    CreateJobsRepository createJobs;
    @Autowired
    SubJobsRepository subJobsRepository;

    public BaseResponseDto create(CreateJobsDto request) {
        BaseResponseDto responseDto = new BaseResponseDto();

        // Check if sub-category exists
        Optional<SubCategory> subCategoryOptional = subJobsRepository.findById(request.getJobId());
        if (!subCategoryOptional.isPresent()) {
            responseDto.setStatus(false);
            responseDto.setStatusDesc("There is no sub-job. Please create a sub-job first.");
            return responseDto;
        }

        // Create and populate the job entity
        SubCategory subCategory = subCategoryOptional.get();
        CreateJobs entity = new CreateJobs();
        entity.setStatus("OPEN");
        entity.setDescription(request.getDescription());
        entity.setForCompany(request.getForCompany());
        entity.setJobtitle(subCategory);

        // Save job
        CreateJobs savedJob = createJobs.save(entity);

        // Prepare response
        responseDto.setStatus(true);
        responseDto.setStatusDesc("Job created successfully.");
        responseDto.setData(savedJob);

        return responseDto;
    }
    public BaseResponseDto update(Long id, CreateJobsDto request) {
        BaseResponseDto response = new BaseResponseDto();

        Optional<CreateJobs> jobOptional = createJobs.findById(id);
        if (!jobOptional.isPresent()) {
            response.setStatus(false);
            response.setStatusDesc("Job not found with ID: " + id);
            return response;
        }

        Optional<SubCategory> subCategoryOptional = subJobsRepository.findById(request.getJobId());
        if (!subCategoryOptional.isPresent()) {
            response.setStatus(false);
            response.setStatusDesc("Sub-job not found.");
            return response;
        }

        CreateJobs job = jobOptional.get();
        job.setDescription(request.getDescription());
        job.setForCompany(request.getForCompany());
        job.setJobtitle(subCategoryOptional.get());
        job.setStatus(request.getStatus());

        CreateJobs updated = createJobs.save(job);

        response.setStatus(true);
        response.setStatusDesc("Job updated successfully.");
        response.setData(updated);

        return response;
    }

    // Delete job
    public BaseResponseDto delete(Long id) {
        BaseResponseDto response = new BaseResponseDto();
        if (!createJobs.existsById(id)) {
            response.setStatus(false);
            response.setStatusDesc("Job not found with ID: " + id);
            return response;
        }

        createJobs.deleteById(id);
        response.setStatus(true);
        response.setStatusDesc("Job deleted successfully.");
        return response;
    }
}


