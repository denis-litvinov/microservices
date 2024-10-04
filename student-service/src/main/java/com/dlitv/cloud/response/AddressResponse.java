package com.dlitv.cloud.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public record AddressResponse(Long addressId, String street, String city) { }
