package com.dekard02.librarymanagement.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto {
    private Long id;

    @Size(max = 150, message = "150 chars")
    @NotEmpty(message = "Full name can be empty")
    private String fullName;

    @Past(message = "dateOfBirth invalid")
    private LocalDateTime dateOfBirth;

    private Boolean isDeleted;

}
