package com.coussy.reference.job.platform.fetch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobPositionDatabaseRepository extends JpaRepository<JobPositionDatabase, UUID>
{

    public JobPositionDatabase findBySourceAndJobPlatformId(String source, String jobPlatformId);

}
