package com.dekard02.librarymanagement.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {
    private Long id;

    @NotEmpty(message = "Khong duoc bo trong")
    private String fullName;

    private String address;

    private String email;

    private LocalDateTime dateOfBirth;

    private String phone;

    private Boolean active;

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends ReaderDto {
        private String photo;
        private LocalDateTime createdAt;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Request extends ReaderDto {

        @NotNull(message = "Khong the thieu anh")
        private MultipartFile photo;
    }
}
