package com.example.notification.Repository;

import com.example.notification.model.Agent.Agents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agents,Long> {




    Optional<Agents> findByEmail(String username);
}
