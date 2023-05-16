package com.dekard02.librarymanagement.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {
    private Long id;

    @NotEmpty(message = "Not empty")
    @Size(max = 100, message = "100 characters")
    private String name;

    private String slug;

    private LocalDateTime createdAt;

    private Boolean isDeleted;
}
