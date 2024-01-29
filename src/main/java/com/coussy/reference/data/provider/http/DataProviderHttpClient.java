package com.coussy.reference.data.provider.http;

import com.coussy.reference.common.configuration.DependencyError;
import com.coussy.reference.data.provider.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
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

    public ProductDto getProduct() {

        HttpUrl httpUrl = HttpUrl.parse("%s/data/product".formatted(url));
        Request request = new Request.Builder().get().url(httpUrl).build();
        Response response = callClient(request);
        ProductDto productDto = fromJson(response, ProductDto.class);
        response.close();
        return productDto;
    }

    public List<Object> getTemperatures() {

        HttpUrl httpUrl = HttpUrl.parse("%s/temperatures".formatted(url));
        Request request = new Request.Builder().get().url(httpUrl).build();
        Response response = callClient(request);
//        TemperatureDto temperatures = fromJson(response, new TypeReference<TemperatureDto>() {
//        });

//        ParameterizedTypeReference<List<String>> parameterizedTypeReference = new ParameterizedTypeReference<List<String>>() {};
        m1(response);

//        List<String> temperatures = fromJson(
//                response,
//                new TypeReference<List<String>>() {}
//        );
//
//
////        String s = fromJson(response, String.class);
//
//
//        response.close();
////        return Collections.singletonList(temperatures);
        return Collections.singletonList(null);
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

}
