package org.evgeny.t1.registrationtaskt1.service;

import org.evgeny.t1.registrationtaskt1.api.RegistrationApi;
import org.evgeny.t1.registrationtaskt1.api.exceptions.InvalidClientDataException;
import org.evgeny.t1.registrationtaskt1.api.exceptions.NotKnownException;
import org.evgeny.t1.registrationtaskt1.service.exception.InternalServiceException;
import org.evgeny.t1.registrationtaskt1.service.exception.InvalidInputData;
import org.evgeny.t1.registrationtaskt1.util.EncodingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.evgeny.t1.registrationtaskt1.api.exceptions.InternalServerException;

import java.util.List;

@Service
public class RegistrationApiService implements RegistrationService {

    private final RegistrationApi registrationApi;
    private static final String STATUS = "increased";

    public RegistrationApiService(@Autowired RegistrationApi registrationApi) {
        this.registrationApi = registrationApi;
    }

    public String registerCandidate(String role, String firstName, String lastName, String email) {
        if (role == null || firstName == null || lastName == null || email == null) {
            throw new InvalidInputData("all fields must be not null");
        }
        if (role.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            throw new InvalidInputData("all fields must be not empty");
        }
        try {
            String signUpAnswer = registrationApi.signUp(role, firstName, lastName, email);
            String code = registrationApi.getCode(email).replace("\"", "");
            String token = EncodingUtils.encodeEmailAndCode(email, code);
            String setStatusAnswer = registrationApi.setStatus(token, STATUS);
            return String.format("""
                     Registration is processed .
                     SignUp process answer: %s .
                     SettingStatus process answer (try to set status %s): %s
                    """, signUpAnswer, STATUS, setStatusAnswer);
        } catch (InvalidClientDataException e) {
            throw new InvalidInputData(e);
        } catch (InternalServerException | NotKnownException e) {
            throw new InternalServiceException(e);
        }
    }

    public List<String> getRoles() {
        try {
            return registrationApi.getRolesList();
        } catch (InternalServerException |NotKnownException e) {
            throw new InternalServiceException(e);
        }
    }
}
