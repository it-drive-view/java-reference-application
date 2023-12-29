package com.coussy.reference.data.provider.http;

import com.coussy.reference.data.provider.dto.ProductDto;
import okhttp3.OkHttpClient;

public class DataProviderHttpClient {

    OkHttpClient okHttpClient;
    String url;

    public DataProviderHttpClient(OkHttpClient okHttpClient, String url) {
    this.okHttpClient = okHttpClient;
        this.url = url;
    }

    public String getToken() {
        return "token from data provider";
    }

    public ProductDto getProduct() {
        return new ProductDto("this is id", "this is name");
    }

}
