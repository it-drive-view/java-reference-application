package com.coussy.reference.statistic;

import com.coussy.reference.data.provider.http.DataProviderHttpClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StatisticService {

    private final DataProviderHttpClient dataProviderHttpClient;

    public StatisticService(DataProviderHttpClient dataProviderHttpClient) {
        this.dataProviderHttpClient = dataProviderHttpClient;
    }

    public StatisticDto computeStatistic() {
        String token = dataProviderHttpClient.getToken();
        return new StatisticDto(token, new BigDecimal(2));
    }
}
