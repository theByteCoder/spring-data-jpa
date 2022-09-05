package com.spring.springdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Guardian {

    @Column(name = "guardian_name", nullable = false)
    private String guardianName;

    @Column(name = "guardian_email", nullable = false)
    private String guardianEmail;
    /*@Column(name = "guardian_phone", nullable = false)
    private String guardianPhone;*/
}
