package com.megafair.auth.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityNotFoundException extends BaseServiceException{
    String errorMessage;
}
