package com.coussy.reference.findwork.data.fetch.http;

import com.coussy.reference.findwork.data.fetch.SkillDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillDatabaseRepository extends JpaRepository<SkillDatabase, UUID>  {



}
