package com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.response;

import com.coussy.reference.common.configuration.DependencyError;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class DataProviderHttpClient {



    // FIXME recopié pour le moment du projet MPay ==> à étudier
    private final String HTTP_CLIENT_IDENTIFIER = "admis-pour-le-moment";

    protected final ObjectMapper OBJECT_MAPPER =
            JsonMapper.builder().addModule(new JavaTimeModule()).build();

    OkHttpClient okHttpClient;
    String url;

    public DataProviderHttpClient(OkHttpClient okHttpClient, String url) {
        this.okHttpClient = okHttpClient;
        this.url = url;
    }

    public String getToken() {

        HttpUrl httpUrl = HttpUrl.parse("%s/data/token".formatted(url));
        Request request = new Request.Builder().get().url(httpUrl).build();
        Response response = callClient(request);
        String token = fromJson(response, String.class);
        response.close();
        return token;
    }

    private void m1(Response response) {
        System.out.println("");
    }

    protected Response callClient(Request request) {

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response;
            }
            throw new DependencyError("DEPENDENCY ERROR");
        } catch (IOException e) {
            throw new DependencyError(e.getMessage());
        }
    }

    protected <T> T fromJson(Response response, Class<T> valueType) {

        // REVOIR
        try {
            return OBJECT_MAPPER.readValue(Objects.requireNonNull(response.body()).string(), valueType);
        } catch (IOException | NullPointerException e) {
            throw new DependencyError(e.getMessage());
        }

    }

    protected <T> T fromJson(Response response, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER
                    .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                    .readValue(Objects.requireNonNull(response.body()).string(), valueTypeRef);
        } catch (IOException | NullPointerException e) {
//            throw new DependencyError(HTTP_CLIENT_IDENTIFIER, e);
            throw new IllegalStateException("prob !!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

}
