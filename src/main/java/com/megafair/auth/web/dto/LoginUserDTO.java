package com.megafair.auth.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class LoginUserDTO {

    @NotNull(message = "Platform identifier may be not empty")
    Long platformId;

    @NotBlank(message = "User identifier may not be blank")
    String userIdentifier;

    @NotBlank(message = "User signature may be not be blank")
    String userSignature;

    @NotNull(message = "Game identifier may be not empty")
    Long gameId;

}
