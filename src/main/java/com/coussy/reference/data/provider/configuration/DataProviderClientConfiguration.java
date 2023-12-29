package com.coussy.reference.data.provider.configuration;

import com.coussy.reference.data.provider.http.DataProviderHttpClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataProviderClientConfiguration {

    @Bean
    DataProviderHttpClient dataProviderClient(OkHttpClient okHttpClient, @Value("${data.provider.url}") String url) {
        return new DataProviderHttpClient(okHttpClient, url);
    }

}
