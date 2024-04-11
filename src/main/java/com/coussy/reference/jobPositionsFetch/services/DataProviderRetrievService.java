package com.coussy.reference.jobPositionsFetch.services;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.response.DataProviderHttpClient;
import org.springframework.stereotype.Service;

@Service
public class DataProviderRetrievService {

    private final DataProviderHttpClient dataProviderHttpClient;

    public DataProviderRetrievService(DataProviderHttpClient dataProviderHttpClient) {
        this.dataProviderHttpClient = dataProviderHttpClient;
    }

    public String getToken() {
        return dataProviderHttpClient.getToken();
    }

}
