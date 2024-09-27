package com.dlitv.cloud.service;

import com.dlitv.cloud.entity.AddressEntity;
import com.dlitv.cloud.repository.AddressRepository;
import com.dlitv.cloud.request.CreateAddressRequest;
import com.dlitv.cloud.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        AddressEntity createdAddressEntity = addressRepository.save(AddressEntity.builder()
                .street(createAddressRequest.street())
                .city(createAddressRequest.city())
                .build());

        return new AddressResponse(createdAddressEntity.getId(), createdAddressEntity.getStreet(), createdAddressEntity.getCity());
    }

    public AddressResponse getById(Long id) {
        logger.info("Inside getById {}",id);

        return addressRepository.findById(id)
                .map(e -> new AddressResponse(e.getId(), e.getStreet(), e.getCity()))
                .orElse(null);
    }
}
