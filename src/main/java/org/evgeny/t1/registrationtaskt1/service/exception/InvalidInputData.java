package org.evgeny.t1.registrationtaskt1.service.exception;

public class InvalidInputData extends RegistrationServiceException {
    public InvalidInputData(Throwable cause) {
        super(cause);
    }
    public InvalidInputData() {
        super();
    }
    public InvalidInputData(String message) {
        super(message);
    }
    public InvalidInputData(String message, Throwable cause) {
        super(message, cause);
    }
}
