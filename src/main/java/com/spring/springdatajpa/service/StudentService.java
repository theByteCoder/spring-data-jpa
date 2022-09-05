package com.spring.springdatajpa.service;

import com.spring.springdatajpa.dto.StudentDTO;
import com.spring.springdatajpa.entity.Guardian;
import com.spring.springdatajpa.entity.Student;
import com.spring.springdatajpa.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity<StudentDTO> addStudent(StudentDTO studentDTO) {
        Student student = mapDtoToEntity(studentDTO);

        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        studentRepository.save(student);
        return ResponseEntity.ok().body(studentDTO);
    }

    public ResponseEntity<StudentDTO> getStudent(String email) {
        Optional<Student> studentOptional = studentRepository.findByEmail(email);
        if (studentOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok().body(mapEntityToDto(studentOptional.get()));
    }

    private StudentDTO mapEntityToDto(Student student) {
        String firstName = student.getFirstName();
        String middleName = student.getMiddleName();
        String lastName = student.getLastName();
        String name = StringUtils.isEmpty(middleName)
                ? String.format("%s %s", firstName, lastName)
                : String.format("%s %s %s", firstName, middleName, lastName);

        Guardian guardian = student.getGuardian();

        StudentDTO studentDTO = StudentDTO.builder()
                .name(name)
                // .dob(student.getDob())
                .email(student.getEmail())
                // .phone(student.getPhone())
                .gender(student.getGender())
                .guardianName(guardian.getGuardianName())
                .guardianEmail(guardian.getGuardianEmail())
                // .guardianPhone(guardian.getGuardianPhone())
                .build();
        return studentDTO;
    }

    private Student mapDtoToEntity(StudentDTO studentDTO) {
        List<String> nameParam = Arrays.asList(studentDTO.getName().split(" "));
        String firstName = nameParam.get(0);
        String middleName = "";
        String lastName = nameParam.get(nameParam.size() - 1);

        if (nameParam.size() > 2) {
            middleName = StringUtils.join(nameParam.subList(1, nameParam.size() - 2), " ");
        }

        Student student = new Student();
        student.setFirstName(firstName);
        student.setMiddleName(middleName);
        student.setLastName(lastName);
        student.setGender(studentDTO.getGender());
        /*student.setDob(studentDTO.getDob());
        student.setPhone(studentDTO.getPhone());*/
        student.setEmail(studentDTO.getEmail());

        Guardian guardian = Guardian.builder()
                .guardianName(studentDTO.getGuardianName())
                .guardianEmail(studentDTO.getGuardianEmail())
                // .guardianPhone(studentDTO.getGuardianPhone())
                .build();

        student.setGuardian(guardian);
        return student;
    }


}
