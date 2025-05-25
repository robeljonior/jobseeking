package com.example.notification.Repository;

import com.example.notification.model.Jobs.Jobs;
import com.example.notification.model.Jobs.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubJobsRepository extends JpaRepository<SubCategory,Long> {
//
//
//    SubCategory findByName(@Param("jobs") String subjobsName@Param("jobs") String subjobsName);

    List<SubCategory> findByJobs(Jobs jobs);

    @Query("SELECT m FROM SubCategory m WHERE m.subjobsName =:subjobsName AND m.jobs = :jobs ")
    SubCategory findByName(@Param("subjobsName")String subjobsName,@Param("jobs") Jobs jobs);
}
