package com.dlitv.cloud.controller;

import com.dlitv.cloud.request.CreateAddressRequest;
import com.dlitv.cloud.response.AddressResponse;
import com.dlitv.cloud.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
        return addressService.createAddress(createAddressRequest);
    }

    @GetMapping("/{id}")
    public AddressResponse getById(@PathVariable Long id) {
        return addressService.getById(id);
    }

}
