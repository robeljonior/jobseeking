package com.example.notification.controller;

import com.example.notification.dto.JobsDto;
import com.example.notification.response.BaseResponseDto;
import com.example.notification.service.JobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobsController {
    @Autowired
    private final JobsService jobsService;

    @PostMapping("/createJob")
    public JobsDto createJob(@RequestBody JobsDto jobsDto) {
        return jobsService.createJob(jobsDto);
    }

    @GetMapping("/alljob")
    public List<JobsDto> getAllJobs() {
        return jobsService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobsDto getJobById(@PathVariable Long id) {
        return jobsService.getJobById(id);
    }

    @PutMapping("/update/{id}")
    public BaseResponseDto updateJob(@RequestBody JobsDto jobsDto) {
        BaseResponseDto response =  jobsService.updateJob(jobsDto);

        return  response;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobsService.deleteJob(id);
        return "Job deleted successfully";
    }
}