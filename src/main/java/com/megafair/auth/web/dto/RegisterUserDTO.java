package com.megafair.auth.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserDTO {

    @NotBlank
    String userIdentifier;

    @NotBlank
    String platformIdentifier;
}
