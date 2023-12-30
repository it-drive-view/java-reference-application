package com.coussy.reference.data.provider;

import com.coussy.reference.data.provider.http.DataProviderHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

abstract class DataProviderHttpClientTest {

    protected DataProviderHttpClient dataProviderHttpClient;
    protected MockWebServer mockWebServer;
    protected String dataProviderBaseUrl;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        // ici, le port est géré le serveur de mock donc rien à penser..
        mockWebServer.start();
        dataProviderBaseUrl = mockWebServer.url("").toString();

        dataProviderHttpClient = new DataProviderHttpClient(new OkHttpClient(), dataProviderBaseUrl);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

}