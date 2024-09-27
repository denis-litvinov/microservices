package com.dlitv.cloud.feignclients;

import com.dlitv.cloud.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway")
public interface ApiGatewayFeignClient {

    @GetMapping("/address-service/api/address/{id}")
    AddressResponse getById(@PathVariable Long id);

}
