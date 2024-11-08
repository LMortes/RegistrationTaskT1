package org.evgeny.t1.registrationtaskt1.api.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InternalServerException extends RegistrationApiException{
    public InternalServerException(String message) {
        super(message);
    }
    public InternalServerException(Throwable cause) {
        super("50x responce code was recieved", cause);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
