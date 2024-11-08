package org.evgeny.t1.registrationtaskt1.service.exception;

public class RegistrationServiceException extends RuntimeException {
    public RegistrationServiceException() {

    }

    public RegistrationServiceException(Throwable cause) {
        super(cause);
    }

    public RegistrationServiceException(String message) {
        super(message);
    }

    public RegistrationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
