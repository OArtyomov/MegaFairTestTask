package com.megafair.auth.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class AuthenticationResult {
    String token;
    String tokenType;
}
