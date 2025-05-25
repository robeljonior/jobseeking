package com.example.notification.controller;

import com.example.notification.dto.SubCatagoryDto;
import com.example.notification.dto.request.Jobname;
import com.example.notification.response.BaseListDto;
import com.example.notification.response.BaseResponseDto;
import com.example.notification.response.ResponseDto;
import com.example.notification.service.SubjobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Subjobs")
@RequiredArgsConstructor
public class SubjobsController {

    @Autowired
    SubjobsService subjobsService;


    @PostMapping("/createSubJob")
    private BaseResponseDto createSudjobs(SubCatagoryDto request){

        BaseResponseDto responseDto = subjobsService.createSubjobs(request);

        return responseDto;


    }
    @PostMapping("/getAllSubJob")
    private BaseListDto getSudjobs(Jobname request){

        BaseListDto responseDto = subjobsService.getBYjobsNameSubjobs(request);

        return responseDto;


    }
    @PostMapping("/UpdateSubJob")
    private BaseResponseDto UpdateSudjobs(SubCatagoryDto request){

        BaseResponseDto responseDto = subjobsService.updateSubjobs(request);

        return responseDto;

    }
    @DeleteMapping("/delateSubjobs")
    private ResponseDto id(long id ){
        ResponseDto responseDto = subjobsService.deleteById(id);
        return responseDto;
    }


}
