package com.dlitv.cloud.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressResponse(@JsonProperty Long addressId, @JsonProperty String street, @JsonProperty String city) { }
