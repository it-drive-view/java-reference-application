package com.coussy.reference.findwork.data.fetch.http;

import com.coussy.reference.findwork.data.fetch.JobsFindWorkDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobsFindWorkRepository extends JpaRepository<JobsFindWorkDatabase, UUID>
{





}
