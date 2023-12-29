package com.coussy.reference.common.http;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConfiguration {

    @Bean
    OkHttpClient httpClient() {
        return new OkHttpClient.Builder().build();
    }

}
