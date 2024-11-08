package org.evgeny.t1.registrationtaskt1.service;

import org.evgeny.t1.registrationtaskt1.service.exception.InternalServiceException;
import org.evgeny.t1.registrationtaskt1.service.exception.InvalidInputData;

import java.util.List;

public interface RegistrationService {

    String registerCandidate(String role, String firstName, String lastName, String email)
            throws InternalServiceException, InvalidInputData;


    List<String> getRoles() throws InternalServiceException;

}

