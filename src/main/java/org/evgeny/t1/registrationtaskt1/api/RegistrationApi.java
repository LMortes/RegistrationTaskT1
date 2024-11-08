package org.evgeny.t1.registrationtaskt1.api;

import org.evgeny.t1.registrationtaskt1.api.exceptions.InvalidClientDataException;
import org.evgeny.t1.registrationtaskt1.api.exceptions.NotKnownException;
import org.evgeny.t1.registrationtaskt1.api.exceptions.InternalServerException;

import java.util.List;

public interface RegistrationApi {

     List<String> getRolesList()
            throws InternalServerException, InvalidClientDataException, NotKnownException;

    String signUp(String role, String firstName, String lastName, String email)
            throws InternalServerException, InvalidClientDataException, NotKnownException;

    String getCode(String email)
            throws InternalServerException, InvalidClientDataException, NotKnownException;

    String setStatus(String token, String status)
            throws InternalServerException, InvalidClientDataException, NotKnownException;

}
