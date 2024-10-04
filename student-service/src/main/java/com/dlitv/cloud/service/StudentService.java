package com.dlitv.cloud.service;

import com.dlitv.cloud.entity.StudentEntity;
import com.dlitv.cloud.repository.StudentRepository;
import com.dlitv.cloud.request.CreateStudentRequest;
import com.dlitv.cloud.response.AddressResponse;
import com.dlitv.cloud.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final CommonService commonService;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        StudentEntity createdStudentEntity = studentRepository.save(StudentEntity.builder()
                .firstName(createStudentRequest.firstName())
                .lastName(createStudentRequest.lastName())
                .email(createStudentRequest.email())
                .addressId(createStudentRequest.addressId())
                .build());

        AddressResponse addressResponse = commonService.getAddressById(createStudentRequest.addressId());

        return StudentResponse.builder()
                .id(createdStudentEntity.getId())
                .firstName(createdStudentEntity.getFirstName())
                .lastName(createdStudentEntity.getLastName())
                .email(createdStudentEntity.getEmail())
                .address(addressResponse)
                .build();
    }

    public StudentResponse getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);

        AddressResponse addressResponse = commonService.getAddressById(studentEntity.getAddressId());

        return StudentResponse.builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .email(studentEntity.getEmail())
                .address(addressResponse)
                .build();
    }
}
