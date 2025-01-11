package com.example.minorProject.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.minorProject.enums.AccountStatus;
import com.example.minorProject.models.Student;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    private String address;

    public Student toStudent() {
        return Student.builder()
                .name(name).email(email).phone(phone).address(address)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }
}
