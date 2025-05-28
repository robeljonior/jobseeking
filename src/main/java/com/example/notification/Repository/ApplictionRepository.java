package com.example.notification.Repository;

import com.example.notification.model.Jobs.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplictionRepository extends JpaRepository<Application,Long> {

    @Query("select m.agent FROM  Application m where m.job = :id")
    List<Application> findByJobsId(@Param("id") Long jobId);
}
