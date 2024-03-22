package com.coussy.reference.findwork.data.fetch.configuration;

import com.coussy.reference.findwork.data.fetch.http.FindworkHttpClient;
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

}


