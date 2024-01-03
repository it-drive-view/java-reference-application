package com.coussy.reference.data.provider;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class getTokenTest extends DataProviderHttpClientTest {

    @Test
    public void getToken() throws InterruptedException {

        // given
        MockResponse mockResponse = new MockResponse()
                .setBody("\"7dbaeb4a-3430-4a06-a5d2-64e570f75c0f\"")
                .setResponseCode(200);
        mockWebServer.enqueue(mockResponse);

        // when
        String token = dataProviderHttpClient.getToken();


        // then
        Assertions.assertEquals("7dbaeb4a-3430-4a06-a5d2-64e570f75c0f", token);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals(dataProviderBaseUrl +  "/data/token" , recordedRequest.getRequestUrl().toString()  );
    }

}
