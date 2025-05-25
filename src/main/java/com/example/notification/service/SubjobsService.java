package com.example.notification.service;

import com.example.notification.Repository.JobsRepo;
import com.example.notification.Repository.SubJobsRepository;
import com.example.notification.dto.SubCatagoryDto;
import com.example.notification.dto.request.Jobname;
import com.example.notification.model.Jobs.Jobs;
import com.example.notification.model.Jobs.SubCategory;
import com.example.notification.response.BaseListDto;
import com.example.notification.response.BaseResponseDto;
import com.example.notification.response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjobsService {

    @Autowired
    SubJobsRepository repository;
    @Autowired
    JobsRepo jobsRepo;



    public BaseResponseDto createSubjobs(SubCatagoryDto request) {
        BaseResponseDto response = new BaseResponseDto();

        Jobs jobs = jobsRepo.findBYJobsName(request.getJobs());
        if (jobs == null){
            response.setErrorCode("this catagory jobs are not found plice create");
            response.setStatus(false);
            return response;

        }
        SubCategory subCategory = repository.findByName(request.getSubjobsName() ,jobs);

        if(subCategory != null ){
            response.setErrorCode("this sub job oredy exist " + subCategory.getSubjobsName());
            response.setStatus(false);
            return response;
        }

            SubCategory entity = new SubCategory();
            entity.setSubjobsName(request.getSubjobsName());
            entity.setJobs(jobs);
            entity.setDescription(request.getDescription());

            repository.save(entity);

            response.setData(entity);
            response.setStatusDesc("sujobs created soccsoss fully");
            response.setStatus(true);

            return response;

    }

    public BaseListDto getBYjobsNameSubjobs(Jobname request) {
        BaseListDto response = new BaseListDto();
        Jobs jobs = jobsRepo.findBYJobsName(request.getJobName());

        if (jobs == null) {
            response.setErrorCode("This category of jobs was not found, please create it.");
            response.setStatus(false);
            return response;
        }

        List<SubCategory> subCategories = repository.findByJobs(jobs);

        // ✅ Map SubCategory list to list of subjobsName
        List<String> names = subCategories.stream()
                .map(SubCategory::getSubjobsName)
                .collect(Collectors.toList());

        long numberOfSubJobs = names.size();

        response.setDatas(names); // Assuming setDatas accepts List<String>
        response.setCount(numberOfSubJobs);
        response.setStatusDesc("List of sub-jobs under job: " + jobs.getCategory());
        response.setStatus(true);

        return response;
    }

    public BaseResponseDto updateSubjobs(SubCatagoryDto request) {
        BaseResponseDto response = new BaseResponseDto();

        // 1. Find the job by name
        Jobs jobs = jobsRepo.findBYJobsName(request.getJobs());
        if (jobs == null) {
            response.setErrorCode("This category job was not found. Please create it.");
            response.setStatus(false);
            return response;
        }

        // 2. Find the SubCategory by name and job
        SubCategory subCategory = repository.findByName(request.getSubjobsName(), jobs);
        if (subCategory == null) {
            response.setErrorCode("Sub-job '" + request.getSubjobsName() + "' not found under job " + request.getJobs());
            response.setStatus(false);
            return response;
        }

        // 3. Check if the new name already exists under the same job (and it's not the same as the one we're updating)
        SubCategory duplicate = repository.findByName(request.getSubjobsName(), jobs);
        if (duplicate != null && duplicate.getId() != subCategory.getId()) {
            response.setErrorCode("A different sub-job with the name '" + request.getSubjobsName() + "' already exists.");
            response.setStatus(false);
            return response;
        }

        // 4. Update the fields
        subCategory.setSubjobsName(request.getSubjobsName());
        subCategory.setDescription(request.getDescription());
        repository.save(subCategory);

        // 5. Return success
        response.setStatus(true);
        response.setStatusDesc("Sub-job updated successfully.");
        return response;
    }

    public ResponseDto deleteById(long id) {
        ResponseDto response = new ResponseDto();

        Optional<SubCategory> subCategoryOptional = repository.findById(id);
        SubCategory subCategory = subCategoryOptional.get();
        String name = subCategory.getSubjobsName();
        if (!subCategoryOptional.isPresent()) {
            response.setStatus(false);
            response.setStatusDesc("SubCategory with ID  not found.");
            return response;
        }

        repository.deleteById(id);
        response.setStatus(true);
        response.setStatusDesc("SubCategory with  " + name +"  deleted successfully.");
        return response;
    }

}
