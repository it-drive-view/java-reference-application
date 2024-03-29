package com.coussy.reference.job.platform.fetch.http;

import com.coussy.reference.common.configuration.DependencyError;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class ReferenceApplicationHttpClient {

    private final OkHttpClient okHttpClient;

    private final  String HTTP_CLIENT_IDENTIFIER;

    private final org.slf4j.Logger LOGGER ;

    private final String baseUrl;

    private final String token;

    public ReferenceApplicationHttpClient(OkHttpClient okHttpClient, String httpClientIdentifier, Logger logger, String baseUrl, String token) {
        this.okHttpClient = okHttpClient;
        this.HTTP_CLIENT_IDENTIFIER = httpClientIdentifier;
        this.LOGGER = logger;
        this.baseUrl = baseUrl;
        this.token = token;
    }

    protected Response callClient(Request request) {

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response;
            }

            var responseBody = getResponseBody(response);
            var statusCode = response.code();
            var errorCode = Objects.requireNonNullElse(getErrorCode(response), "");
            logResponseInError(response, responseBody, statusCode, errorCode);

            var dependencyIdentifier =
                    responseBody.contains("API Gateway encountered an error.")
                            ? "API_GATEWAY"
                            : HTTP_CLIENT_IDENTIFIER;

            throw new DependencyError(
                    dependencyIdentifier,
                    "External API failed with status code %s".formatted(statusCode),
                    errorCode);

        } catch (IOException e) {
            throw new DependencyError(e.getMessage());
        }

    }

    @NotNull
    private String getResponseBody(Response response) {
        try {
            return response.peekBody(Long.MAX_VALUE).string();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DependencyError(HTTP_CLIENT_IDENTIFIER, "Fail to parse response body", null);
        }
    }

    private void logResponseInError(
            Response response, String responseBody, int statusCode, String errorCode) {
        var responseDetails = getResponseDetails(response);
        responseDetails.put("errorCode", errorCode);
        responseDetails.put("message", responseBody);

        var request = response.request();
        var title =
                "Outbound response error - %s %s %s"
                        .formatted(statusCode, request.method(), request.url().encodedPath());
        var name = "OUTBOUND_RESPONSE_%s".formatted(HTTP_CLIENT_IDENTIFIER);
        LOGGER.error("title: {} name: {} responseDetails: {}", title, name, responseDetails);
    }

    private Map<String, Object> getResponseDetails(Response response) {
        var responseDetails = getRequestDetails(response.request());
        responseDetails.put("statusCode", response.code());
        var duration = response.receivedResponseAtMillis() - response.sentRequestAtMillis();
        responseDetails.put("duration", duration);

        return responseDetails;
    }

    private Map<String, Object> getRequestDetails(Request request) {
        var method = Map.entry("method", request.method());
        var url = Map.entry("url", request.url().encodedPath());
        var client = Map.entry("client", HTTP_CLIENT_IDENTIFIER);

        var requestDetails = new HashMap<String, Object>(Map.ofEntries(method, url, client));
        if (request.url().encodedQuery() != null)
            requestDetails.put("params", request.url().encodedQuery());

        return requestDetails;
    }

    protected abstract String getErrorCode(Response response);

}
