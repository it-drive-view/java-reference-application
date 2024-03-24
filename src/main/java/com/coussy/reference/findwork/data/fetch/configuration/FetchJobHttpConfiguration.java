package com.coussy.reference.findwork.data.fetch.configuration;

import com.coussy.reference.findwork.data.fetch.DtoMapper;
import com.coussy.reference.findwork.data.fetch.implem.findwork.FindWorkPlatformService;
import com.coussy.reference.findwork.data.fetch.http.FindworkHttpClient;
import com.coussy.reference.findwork.data.fetch.http.JobPositionDatabaseRepository;
import com.coussy.reference.findwork.data.fetch.http.SkillDatabaseRepository;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FetchJobHttpConfiguration {

    @Bean
    public FindworkHttpClient findworkHttpClient(OkHttpClient okHttpClient, @Value("${findwork.url}") String baseUrl, @Value("${findwork.token}") String token) {
        return new FindworkHttpClient(okHttpClient, baseUrl, token);
    }

    @Bean
    public FindWorkPlatformService findWorkPlatformService(
            FindworkHttpClient findworkHttpClient,
            JobPositionDatabaseRepository jobPositionDatabaseRepository,
            SkillDatabaseRepository skillDatabaseRepository,
            DtoMapper dtoMapper
    ) {
        return new FindWorkPlatformService(findworkHttpClient, jobPositionDatabaseRepository, skillDatabaseRepository, dtoMapper);
    }

    @Bean
    public FindWorkPlatformService findWorkPlatformService2(
            FindworkHttpClient findworkHttpClient,
            JobPositionDatabaseRepository jobPositionDatabaseRepository,
            SkillDatabaseRepository skillDatabaseRepository,
            DtoMapper dtoMapper
    ) {
        return new FindWorkPlatformService(findworkHttpClient, jobPositionDatabaseRepository, skillDatabaseRepository, dtoMapper);
    }

}


