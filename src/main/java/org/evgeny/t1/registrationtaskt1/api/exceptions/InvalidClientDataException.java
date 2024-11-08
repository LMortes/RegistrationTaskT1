package org.evgeny.t1.registrationtaskt1.api.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidClientDataException  extends RegistrationApiException{
    public InvalidClientDataException(String message) {
        super(message);
    }
    public InvalidClientDataException(Throwable cause) {
        super("40x responce code was recieved", cause);
    }

    public InvalidClientDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
