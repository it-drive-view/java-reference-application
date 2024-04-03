package com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillDatabaseRepository extends JpaRepository<SkillDatabase, UUID>  {



}
