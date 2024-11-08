package org.evgeny.t1.registrationtaskt1.service.exception;

public class InternalServiceException extends RegistrationServiceException {
    public InternalServiceException (){
        super();
    }
    public InternalServiceException(Throwable cause) {
        super(cause);
    }
    public InternalServiceException(String message) {
        super(message);
    }
    public InternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

