package com.example.notification.Repository;

import com.example.notification.model.Jobs.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepo extends JpaRepository<Jobs,Long> {
   @Query("SELECT m FROM Jobs m where m.category =:category")
    Jobs findBYJobsName(@Param("category") String jobname);
}
