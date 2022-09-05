package com.spring.springdatajpa.dto;

import com.spring.springdatajpa.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    private String name;

    // private Instant dob;
    private Gender gender;

    private String email;
    // private String phone;

    private String guardianName;
    private String guardianEmail;
    // private String guardianPhone;
}
