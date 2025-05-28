package com.example.notification.service.jobsAndAplication;

import com.example.notification.Repository.JobsRepo;
import com.example.notification.dto.JobsDto;
import com.example.notification.model.Jobs.Jobs;
import com.example.notification.response.BaseResponseDto;
import com.example.notification.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobsService {

    private final JobsRepo jobsRepository;
    private final ModelMapper modelMapper;

    // 🔹 Create a job
    public JobsDto createJob(JobsDto jobsDto) {
        // Check if a job with the same name already exists
        Jobs existingJob = jobsRepository.findBYJobsName(jobsDto.getCategory());
        if (existingJob != null) {
            throw new RuntimeException("A job with the name '" + jobsDto.getCategory() + "' already exists.");
        }

        Jobs job = modelMapper.map(jobsDto, Jobs.class);
        Jobs savedJob = jobsRepository.save(job);
        return modelMapper.map(savedJob, JobsDto.class);
    }


    // 🔹 Get all jobs
    public List<JobsDto> getAllJobs() {
        return jobsRepository.findAll().stream()
                .map(job -> modelMapper.map(job, JobsDto.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get job by ID
    public JobsDto getJobById(Long id) {
        Jobs job = jobsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return modelMapper.map(job, JobsDto.class);
    }

    // 🔹 Update job
    public BaseResponseDto updateJob(JobsDto request) {
        BaseResponseDto response = new BaseResponseDto();

        // 1. Find the existing job by old category name (you need to pass oldCategory in DTO)
        Jobs existingJob = jobsRepository.findBYJobsName(request.getCategory());
        if (existingJob == null) {
            response.setStatus(false);
            response.setStatusDesc("Job with name '" + request.getCategory() + "' not found.");
            return response;
        }

        // 2. Check if new category name already exists and is different job
        if (!request.getCategory().equalsIgnoreCase(request.getCategory())) {
            Jobs jobWithSameName = jobsRepository.findBYJobsName(request.getCategory());
            if (jobWithSameName != null) {
                response.setStatus(false);
                response.setStatusDesc("Job name '" + request.getCategory() + "' already exists.");
                return response;
            }
        }

        // 3. Update fields
        existingJob.setCategory(request.getCategory());
        existingJob.setCategoryDescription(request.getCategoryDescription());

        // 4. Save updated job
        Jobs updatedJob = jobsRepository.save(existingJob);

        // 5. Prepare success response
        response.setStatus(true);
        response.setStatusDesc("Job updated successfully");
        response.setData(updatedJob);

        return response;
    }


    public ResponseDto deleteJob(Long id) {
        ResponseDto response = new ResponseDto();

        Optional<Jobs> jobs = jobsRepository.findById(id);

        if (!jobs.isPresent()) {
            response.setStatus(false);
            response.setStatusDesc("Job with id  does not exist.");
            return response;
        }

        jobsRepository.deleteById(id);
        response.setStatus(true);
        response.setStatusDesc("Job deleted successfully.");
        return response;
    }


}
