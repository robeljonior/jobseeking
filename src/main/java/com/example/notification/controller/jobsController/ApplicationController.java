package com.example.notification.controller.jobsController;

import com.example.notification.dto.request.jobs.AplicationDto;
import com.example.notification.response.BaseListDto;
import com.example.notification.response.BaseResponseDto;
import com.example.notification.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("applications")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    // CREATE
    @PostMapping("/create")
    public BaseResponseDto createApplication(@RequestBody AplicationDto request) {
        return service.create(request);
    }

    // READ ALL
    @PostMapping("/agents")
    public ResponseEntity<BaseListDto> getAgentsByJobId(@PathVariable Long jobId) {
        BaseListDto response = service.getAgentsByJobId(jobId);
            return ResponseEntity.ok(response);
    }
    @PutMapping("/updateApplication")
    public ResponseEntity<BaseResponseDto> updateApplication(@RequestBody AplicationDto request) {
        BaseResponseDto response = service.update(request);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/DelateApllication")
    public ResponseEntity<BaseResponseDto> deleteApplication(@PathVariable("id") Long id) {
        BaseResponseDto response = service.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }




}
