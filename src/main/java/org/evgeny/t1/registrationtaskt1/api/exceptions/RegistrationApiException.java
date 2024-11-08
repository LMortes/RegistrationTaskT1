package org.evgeny.t1.registrationtaskt1.api.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegistrationApiException extends RuntimeException {

    public RegistrationApiException(String message) {
        super(message);
    }

    public RegistrationApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
