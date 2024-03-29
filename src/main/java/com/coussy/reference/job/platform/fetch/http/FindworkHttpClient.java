package com.coussy.reference.job.platform.fetch.http;

import com.coussy.reference.job.platform.fetch.dto.ParentDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FindworkHttpClient extends ReferenceApplicationHttpClient {

    private static final String HTTP_CLIENT_IDENTIFIER = "FINDWORK";

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FindworkHttpClient.class);

    private final String baseUrl;

    private final String token;

    private final ObjectMapper OBJECT_MAPPER;

    {
        OBJECT_MAPPER = new ObjectMapper()
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JavaTimeModule());
    }

    public FindworkHttpClient(OkHttpClient okHttpClient, String baseUrl, String token) {
        super(okHttpClient, HTTP_CLIENT_IDENTIFIER, LOGGER, baseUrl, token);
        this.baseUrl = baseUrl;
        // TODO passer le token dans le code !!!!!!!!!!!!!!!
        // TODO passer le token dans le code !!!!!!!!!!!!!!!
        this.token = token;
    }

    @Override
    protected String getErrorCode(Response response) {
        ParentDto dto = deserialize(response);
        return dto.detail();
    }

    public ParentDto getJobs(String url) {

        HttpUrl httpUrl;
        if (url == null) {
            httpUrl = HttpUrl.parse("%s/jobs".formatted(baseUrl));
        } else {
            httpUrl = HttpUrl.parse(url);
        }

        Request request = new Request.Builder()
                .get()
                .url(httpUrl)
                .addHeader("Authorization", "Token %s".formatted(token))
                .build();
        Response response = callClient(request);
        return deserialize(response);
    }

    // TODO on peut utiliser le mot clé défault ?
    protected ParentDto deserialize(Response response) {
        try {
            ParentDto dto = OBJECT_MAPPER
                    .readValue(response.body().string(), ParentDto.class);
            return dto;

//            return OBJECT_MAPPER
//                    .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
//                    .readValue(Objects.requireNonNull(response.body()).string(), valueTypeRef);
        } catch (IOException | NullPointerException e) {
//            throw new DependencyError(HTTP_CLIENT_IDENTIFIER, e);
            throw new IllegalStateException("prob: " + e.getMessage());
        }
    }

}
