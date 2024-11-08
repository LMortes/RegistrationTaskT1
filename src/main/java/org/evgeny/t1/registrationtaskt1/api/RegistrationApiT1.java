package org.evgeny.t1.registrationtaskt1.api;

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.evgeny.t1.registrationtaskt1.api.dto.RegistrationRequestDTO;
import org.evgeny.t1.registrationtaskt1.api.dto.RolesListDTO;
import org.evgeny.t1.registrationtaskt1.api.dto.SetStatusDTO;
import org.evgeny.t1.registrationtaskt1.api.exceptions.InvalidClientDataException;
import org.evgeny.t1.registrationtaskt1.api.exceptions.NotKnownException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;
import org.evgeny.t1.registrationtaskt1.api.exceptions.InternalServerException;
import org.evgeny.t1.registrationtaskt1.api.exceptions.RegistrationApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RegistrationApiT1 implements RegistrationApi {

    private final String GET_ROLES_URL;
    private final String SIGN_UP_URL;
    private final String GET_CODE_URL;
    private final String SET_STATUS_URL;
    private final RestClient REST_CLIENT;


    public RegistrationApiT1(@Value("${t1.api.url.get-roles}") String getRolesUrl,
                             @Value("${t1.api.url.sign-up}") String signUpUrl,
                             @Value("${t1.api.url.get-code}") String getCodeUrl,
                             @Value("${t1.api.url.set-status}") String setStatus) {

        GET_ROLES_URL = getRolesUrl;
        SIGN_UP_URL = signUpUrl;
        GET_CODE_URL = getCodeUrl;
        SET_STATUS_URL = setStatus;

        //RestClient is thread safe after creation
        synchronized (this) {
            final HttpComponentsClientHttpRequestFactory factory =
                    new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
            REST_CLIENT = RestClient.builder().requestFactory(factory).build();
        }
    }

    @Override
    public List<String> getRolesList() throws RegistrationApiException {
        try {
            var responce = REST_CLIENT
                    .get()
                    .uri(GET_ROLES_URL)
                    .retrieve()
                    .body(RolesListDTO.class);
            return responce != null ? (responce.getRoles() != null ? responce.getRoles() : new ArrayList<>()) : new ArrayList<>();
        } catch (RestClientResponseException e) {
            if (e.getStatusCode().is4xxClientError()) {
                throw new InvalidClientDataException(e);
            } else if (e.getStatusCode().is5xxServerError()) {
                throw new InternalServerException(e);
            } else {
                throw new NotKnownException(e);
            }
        }
    }

    @Override
    public String signUp(String role, String firstName, String lastName, String email) {
        var regReq = RegistrationRequestDTO.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(role)
                .build();

        try {
            return Optional.ofNullable(
                            REST_CLIENT.post().
                                    uri(SIGN_UP_URL)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(regReq)
                                    .retrieve()
                                    .body(String.class))
                    .orElse("");
        } catch (RestClientResponseException e) {
            if (e.getStatusCode().is4xxClientError()) {
                throw new InvalidClientDataException(e);
            } else if (e.getStatusCode().is5xxServerError()) {
                throw new InternalServerException(e);
            } else {
                throw new NotKnownException(e);
            }
        }
    }

    @Override
    public String getCode(String email) {
        try {
            return Optional.ofNullable(
                            REST_CLIENT
                                    .get()
                                    .uri(GET_CODE_URL + "?email=" + email)
                                    .retrieve()
                                    .body(String.class))
                    .orElse("");
        } catch (RestClientResponseException e) {
            if (e.getStatusCode().is4xxClientError()) {
                throw new InvalidClientDataException(e);
            } else if (e.getStatusCode().is5xxServerError()) {
                throw new InternalServerException(e);
            } else {
                throw new NotKnownException(e);
            }
        }

    }

    @Override
    public String setStatus(String token, String status) {
        try {
            return Optional.ofNullable(REST_CLIENT
                            .post()
                            .uri(SET_STATUS_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(new SetStatusDTO(status, token))
                            .retrieve()
                            .body(String.class))
                    .orElse("");
        } catch (RestClientResponseException e) {
            if (e.getStatusCode().is4xxClientError()) {
                throw new InvalidClientDataException(e);
            } else if (e.getStatusCode().is5xxServerError()) {
                throw new InternalServerException(e);
            } else {
                throw new NotKnownException(e);
            }
        }
    }

}
