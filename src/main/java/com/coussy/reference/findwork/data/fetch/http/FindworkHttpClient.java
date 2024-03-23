package com.coussy.reference.findwork.data.fetch.http;

import com.coussy.reference.common.configuration.DependencyError;
import com.coussy.reference.findwork.data.fetch.dto.ParentDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class FindworkHttpClient {

    private final OkHttpClient okHttpClient;

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());

    private final String baseUrl;

    private final String token;

    public FindworkHttpClient(OkHttpClient okHttpClient, String baseUrl, String token) {
        this.okHttpClient = okHttpClient;
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public ParentDto getJobs() {

        Request request = new Request.Builder()
                .get()
                .url(HttpUrl.parse( "%s/jobs".formatted(baseUrl)))
                .addHeader("Authorization" , "Token 2a63f298d63f2cda7b33df3b6a2741aeca96d9aa")
                .build();
        Response response = callClient(request);
        return fromJson2(response);
    }

    protected ParentDto fromJson2(Response response) {
        try {
            ParentDto dto = OBJECT_MAPPER
                    .readValue(response.body().string(), ParentDto.class );
            return dto;

//            return OBJECT_MAPPER
//                    .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
//                    .readValue(Objects.requireNonNull(response.body()).string(), valueTypeRef);
        } catch (IOException | NullPointerException e) {
//            throw new DependencyError(HTTP_CLIENT_IDENTIFIER, e);
            throw new IllegalStateException("prob: " + e.getMessage());
        }
    }

    private String responseToString(Response response) {
        return null;
    }

    protected Response callClient(Request request) {

    try {
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response;
        }
        throw new DependencyError("DEPENDENCY_ERROR");
    } catch (IOException e) {
        throw new DependencyError(e.getMessage());
    }

}
}