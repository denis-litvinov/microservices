package com.dlitv.cloud.request;

public record CreateStudentRequest(String firstName, String lastName, String email, long addressId) {
}
