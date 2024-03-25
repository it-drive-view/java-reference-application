package com.coussy.reference.job.platform.fetch.configuration;

import com.coussy.reference.job.platform.fetch.FetchJobOrchestrator;
import com.coussy.reference.job.platform.fetch.implementation.FindWorkApiService;
import com.coussy.reference.job.platform.fetch.http.FindworkHttpClient;
import com.coussy.reference.job.platform.fetch.JobPositionDatabaseRepository;
import com.coussy.reference.job.platform.fetch.SkillDatabaseRepository;
import com.coussy.reference.student.Student;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FetchJobHttpConfiguration {

    @Bean
    public FindworkHttpClient findworkHttpClient(OkHttpClient okHttpClient, @Value("${findwork.url}") String baseUrl, @Value("${findwork.token}") String token) {
        return new FindworkHttpClient(okHttpClient, baseUrl, token);
    }

    @Bean
    public FindWorkApiService findWork(
            FindworkHttpClient findworkHttpClient,
            JobPositionDatabaseRepository jobPositionDatabaseRepository,
            SkillDatabaseRepository skillDatabaseRepository
    ) {
        return new FindWorkApiService(findworkHttpClient, jobPositionDatabaseRepository, skillDatabaseRepository);
    }

    @Bean
    public FindWorkApiService findWorkPlatformService2(
            FindworkHttpClient findworkHttpClient,
            JobPositionDatabaseRepository jobPositionDatabaseRepository,
            SkillDatabaseRepository skillDatabaseRepository
    ) {
        return new FindWorkApiService(findworkHttpClient, jobPositionDatabaseRepository, skillDatabaseRepository);
    }

    // c'est étrange quie ça ne plante pas !  ce n'est pas un bean !
    @Bean
    public FetchJobOrchestrator fetchJobOrchestrator(@Value("#{'${job.platforms}'.split(',')}") List<String> jobPlatforms, ApplicationContext applicationContext) {
        return new FetchJobOrchestrator(jobPlatforms, applicationContext);
    }

}


