package com.example.notification.controller;

import com.example.notification.dto.request.CreateJobsDto;
import com.example.notification.response.BaseResponseDto;
import com.example.notification.service.CreateJobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Subjobs")
@RequiredArgsConstructor
public class CreateJobsController {

    @Autowired
    CreateJobsService createJobsService;

    @PostMapping("/createJobs")
    private BaseResponseDto createJobs(@RequestBody CreateJobsDto reqest){
        BaseResponseDto response = createJobsService.create(reqest);

        return response;
    }


}
