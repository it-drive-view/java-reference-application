package com.coussy.reference.jobPositionsFetch.configuration;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SchedulerDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.response.DataProviderHttpClient;
import com.coussy.reference.jobPositionsFetch.services.FetchJob;
import com.coussy.reference.jobPositionsFetch.services.FetchJobOrchestrator;
import com.coussy.reference.jobPositionsFetch.services.FetchJobOrchestratorFake;
import com.coussy.reference.jobPositionsFetch.services.FindWorkApiService;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.FindworkHttpClient;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.JobPositionDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabaseRepository;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class FetchJobHttpConfiguration {

    @Bean
    DataProviderHttpClient dataProviderHttpClient(OkHttpClient okHttpClient, @Value("${dataprovider.url}") String baseUrl) {
        return new DataProviderHttpClient(okHttpClient, baseUrl);
    }

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

//    @Bean
//    public FindWorkApiService findWorkPlatformService2(
//            FindworkHttpClient findworkHttpClient,
//            JobPositionDatabaseRepository jobPositionDatabaseRepository,
//            SkillDatabaseRepository skillDatabaseRepository
//    ) {
//        return new FindWorkApiService(findworkHttpClient, jobPositionDatabaseRepository, skillDatabaseRepository);
//    }

    // c'est étrange quie ça ne plante pas !  ce n'est pas un bean !
    @Bean
    @Profile("!test")
    public FetchJob fetchJobOrchestrator(@Value("#{'${job.platforms}'.split(',')}") List<String> jobPlatforms, ApplicationContext applicationContext, SchedulerDatabaseRepository schedulerDatabaseRepository) {
        return new FetchJobOrchestrator(jobPlatforms, applicationContext, schedulerDatabaseRepository);
    }

    @Bean
    @Profile("test")
    public FetchJob fetchJobOrchestratorTest(@Value("#{'${job.platforms}'.split(',')}") List<String> jobPlatforms, ApplicationContext applicationContext, SchedulerDatabaseRepository schedulerDatabaseRepository) {
        return new FetchJobOrchestratorFake(jobPlatforms, applicationContext, schedulerDatabaseRepository);
    }






}


