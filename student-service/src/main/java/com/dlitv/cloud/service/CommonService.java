package com.dlitv.cloud.service;

import com.dlitv.cloud.feignclients.ApiGatewayFeignClient;
import com.dlitv.cloud.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonService {

    private Logger logger = LoggerFactory.getLogger(CommonService.class);

    private long count = 1;

    private final ApiGatewayFeignClient apiGatewayFeignClient;

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId) {
        logger.info("count = {}", count);
        count++;
        return apiGatewayFeignClient.getById(addressId);
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable ) {
        logger.error("Error = {}", throwable);
        return new AddressResponse(addressId, null, null);
    }
}
