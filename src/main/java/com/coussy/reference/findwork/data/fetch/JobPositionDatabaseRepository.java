package com.coussy.reference.findwork.data.fetch;

import com.coussy.reference.findwork.data.fetch.JobPositionDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobPositionDatabaseRepository extends JpaRepository<JobPositionDatabase, UUID>
{





}
