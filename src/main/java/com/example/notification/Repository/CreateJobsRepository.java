package com.example.notification.Repository;

import com.example.notification.model.CreateJobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateJobsRepository extends JpaRepository<CreateJobs,Long> {

}
