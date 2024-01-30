package com.coussy.reference.data.provider;


import com.coussy.reference.data.provider.dto.ProductDto;
import com.coussy.reference.data.provider.dto.TemperatureDto;
import com.coussy.reference.exception.ApiRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/data/provider/retrieve/")
public class DataProviderRetrieveController {

    private final DataProviderRetrievService dataProviderRetrievService;

    public DataProviderRetrieveController(DataProviderRetrievService dataProviderRetrievService) {
        this.dataProviderRetrievService = dataProviderRetrievService;
    }

    @GetMapping("product")
    public ProductDto getProduct() {
        return  dataProviderRetrievService.getProduct();
    }

    @GetMapping("temperatures")
    public List<TemperatureDto> get() {
        return dataProviderRetrievService.getTemperatures();
    }

    @GetMapping("throw")
    public List<TemperatureDto> getTemperatures2()
    {
        throw new ApiRequestException("can not provide temperatures.");
    }

}
