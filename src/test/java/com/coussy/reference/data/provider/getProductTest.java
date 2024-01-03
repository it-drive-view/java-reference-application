package com.coussy.reference.data.provider;

import com.coussy.reference.data.provider.dto.ProductDto;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class getProductTest extends DataProviderHttpClientTest {

    @Test
    public void getProductTest() throws InterruptedException {

        // Given
        String body = new JSONObject(
                Map.ofEntries(
                        Map.entry("id", "1589785"),
                        Map.entry("name", "ledger nano X")
                )
        ).toString();
        MockResponse mockResponse = new MockResponse()
                .setBody(body)
                .setResponseCode(200);
        mockWebServer.enqueue(mockResponse);

        // When
        ProductDto product = dataProviderHttpClient.getProduct();

        // Then
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals( dataProviderBaseUrl + "/data/product", recordedRequest.getRequestUrl().toString());

        Assertions.assertEquals("1589785", product.getId());
        Assertions.assertEquals("ledger nano X", product.getName());


    }

}
