package com.coussy.reference.data.provider;

import com.coussy.reference.data.provider.dto.ProductDto;
import com.coussy.reference.data.provider.dto.TemperatureDto;
import com.coussy.reference.data.provider.http.DataProviderHttpClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataProviderRetrievService {

    private final DataProviderHttpClient dataProviderHttpClient;

    public DataProviderRetrievService(DataProviderHttpClient dataProviderHttpClient) {
        this.dataProviderHttpClient = dataProviderHttpClient;
    }

    public String getToken() {
        return dataProviderHttpClient.getToken();
    }

    public ProductDto getProduct() {
        return dataProviderHttpClient.getProduct();
    }

    public List<TemperatureDto> getTemperatures() {
        return dataProviderHttpClient.getTemperatures();
    }


}
