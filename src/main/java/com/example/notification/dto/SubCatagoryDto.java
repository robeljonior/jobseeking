package com.example.notification.dto;

import com.example.notification.model.Jobs.Jobs;
import lombok.Data;

@Data

public class SubCatagoryDto {
    private long id;
    private String jobs;
    private String subjobsName;
    private String description;
}
