package org.evgeny.t1.registrationtaskt1.api.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotKnownException extends RegistrationApiException {

    public NotKnownException(String message) {
        super(message);
    }
    public NotKnownException(Throwable cause) {
        super("Unknown exception was threw, maybe network error, see cause for details", cause);
    }

    public NotKnownException(String message, Throwable cause) {
        super(message, cause);
    }
}
