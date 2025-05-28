package com.example.notification.service;

import com.example.notification.Repository.AgentRepository;
import com.example.notification.Repository.ApplictionRepository;
import com.example.notification.Repository.CreateJobsRepository;
import com.example.notification.dto.request.jobs.AplicationDto;
import com.example.notification.model.Agent.Agents;
import com.example.notification.model.Jobs.Application;
import com.example.notification.model.Jobs.CreateJobs;
import com.example.notification.response.BaseListDto;
import com.example.notification.response.BaseResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

   @Autowired
    AgentRepository agentRepository;
   @Autowired
   CreateJobsRepository createJobsRepository;
    @Autowired
    ApplictionRepository applictionRepository;


    public BaseResponseDto create(AplicationDto request) {
        logger.info("Received job application request: {}", request);

        BaseResponseDto responseDto = new BaseResponseDto();

        Optional<Agents> agents = agentRepository.findByEmail(request.getAgent_email());
        if (agents.isEmpty()) {
            logger.warn("No agent found with email: {}", request.getAgent_email());
            responseDto.setStatusDesc("Please register to apply for this job.");
            responseDto.setStatus(false);
            return responseDto;
        }

        Agents agent = agents.get();
        logger.info("Agent found: {}", agent.getId());

        Optional<CreateJobs> jobs = createJobsRepository.findById(request.getJob_Id());
        if (jobs.isEmpty()) {
            logger.warn("No job found with ID: {}", request.getJob_Id());
            responseDto.setStatusDesc("This job might have been removed or may not be accessible at this time.");
            responseDto.setStatus(false);
            return responseDto;
        }

        CreateJobs job = jobs.get();
        logger.info("Job found: {}", job.getId());

        Application application = Application.builder()
                .agent(agent)
                .job(job)
                .appliedAt(request.getAppliedAt())
                .applicationStatus("PENDING")
                .coverLetter(request.getCoverLetter())
                .build();

        applictionRepository.save(application);
        logger.info("Application saved with ID: {}", application.getId());

        responseDto.setData(application);
        responseDto.setStatus(true);
        responseDto.setStatusDesc("Successfully applied.");
        logger.info("Job application successful for agent ID: {} and job ID: {}", agent.getId(), job.getId());

        return responseDto;
    }
    public BaseResponseDto update( AplicationDto request) {
        logger.info("Received job application update request: ID={}, Data={}", request.getId(), request);

        BaseResponseDto responseDto = new BaseResponseDto();

        Optional<Application> optionalApplication = applictionRepository.findById(request.getId());
        if (optionalApplication.isEmpty()) {
            logger.warn("No application found with ID: {}", request.getId());
            responseDto.setStatus(false);
            responseDto.setStatusDesc("Application not found.");
            return responseDto;
        }

        Application application = optionalApplication.get();

        // Optional: validate if agent and job still exist
        Optional<Agents> agentOpt = agentRepository.findByEmail(request.getAgent_email());
        if (agentOpt.isEmpty()) {
            logger.warn("No agent found with email: {}", request.getAgent_email());
            responseDto.setStatus(false);
            responseDto.setStatusDesc("Invalid agent email.");
            return responseDto;
        }

        Optional<CreateJobs> jobOpt = createJobsRepository.findById(request.getJob_Id());
        if (jobOpt.isEmpty()) {
            logger.warn("No job found with ID: {}", request.getJob_Id());
            responseDto.setStatus(false);
            responseDto.setStatusDesc("Invalid job ID.");
            return responseDto;
        }

        // Update fields
        application.setAppliedAt(request.getAppliedAt());
        application.setCoverLetter(request.getCoverLetter());

        applictionRepository.save(application);
        logger.info("Application updated with ID: {}", application.getId());

        responseDto.setData(application);
        responseDto.setStatus(true);
        responseDto.setStatusDesc("Application updated successfully.");
        return responseDto;
    }
    public BaseResponseDto delete(Long applicationId) {
        logger.info("Received request to delete application with ID: {}", applicationId);

        BaseResponseDto responseDto = new BaseResponseDto();

        Optional<Application> optionalApplication = applictionRepository.findById(applicationId);
        if (optionalApplication.isEmpty()) {
            logger.warn("Application not found with ID: {}", applicationId);
            responseDto.setStatus(false);
            responseDto.setStatusDesc("Application not found.");
            return responseDto;
        }

        applictionRepository.deleteById(applicationId);
        logger.info("Application deleted with ID: {}", applicationId);

        responseDto.setStatus(true);
        responseDto.setStatusDesc("Application deleted successfully.");
        return responseDto;
    }


    public BaseListDto getAgentsByJobId(Long jobId) {
        BaseListDto responseDto = new BaseListDto();

        // Validate job existence
        Optional<CreateJobs> job = createJobsRepository.findById(jobId);
        log.error("Failed to retrieve agents for job {}: {}", job);

        if (job.isEmpty()) {
            responseDto.setStatusDesc("Job not found.");
            responseDto.setStatus(false);
            return responseDto;
        }

        try {
            // Find all applications for the given job
            List<Application> applications = applictionRepository.findByJobsId(jobId);
            log.error("Failed to retrieve agents for job {}: {}", applications);

            if (applications.isEmpty()) {
                responseDto.setStatusDesc("No agents have applied for this job.");
                responseDto.setStatus(false);
                return responseDto;
            }

            // Extract agents from applications
            List<Agents> agents = applications.stream()
                    .map(Application::getAgent)
                    .distinct() // Ensure no duplicate agents
                    .collect(Collectors.toList());



            long countApplicants = agents.size(); // More efficient than stream().count()
            log.error("Failed to retrieve agents for job {}: {}", countApplicants);

            responseDto.setDatas(agents);
            responseDto.setCount(countApplicants);
            responseDto.setStatus(true);
            responseDto.setStatusDesc("Successfully retrieved agents for job : ");
            return responseDto;

        } catch (Exception e) {
            // Log the error (assuming a logger is configured)
            log.error("Failed to retrieve agents for job {}: {}", jobId, e.getMessage());
            responseDto.setStatusDesc("An error occurred while retrieving agents.");
            responseDto.setStatus(false);

        }

        return responseDto;
    }




}
