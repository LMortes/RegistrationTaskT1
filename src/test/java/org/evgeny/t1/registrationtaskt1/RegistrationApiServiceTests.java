package org.evgeny.t1.registrationtaskt1;

import org.evgeny.t1.registrationtaskt1.api.RegistrationApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.evgeny.t1.registrationtaskt1.api.exceptions.InternalServerException;
import org.evgeny.t1.registrationtaskt1.service.RegistrationApiService;
import org.evgeny.t1.registrationtaskt1.service.exception.InternalServiceException;
import org.evgeny.t1.registrationtaskt1.service.exception.InvalidInputData;
import org.evgeny.t1.registrationtaskt1.util.EncodingUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class RegistrationApiServiceTests {

    @Mock
    private RegistrationApi mockRegistrationApi;

    private RegistrationApiService registrationApiService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        registrationApiService = new RegistrationApiService(mockRegistrationApi);
    }

    /**
     * Проверка ожидаемого поведения сервиса в случае успеха
     */
    @Test
    public void testRegisterCandidate_Success() {

        String role = "candidate";
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";

        String signUpAnswer = "SignUp success";
        String code = "123456";
        String token = EncodingUtils.encodeEmailAndCode(email, code);
        String setStatusAnswer = "increased";

        when(mockRegistrationApi.signUp(role, firstName, lastName, email)).thenReturn(signUpAnswer);
        when(mockRegistrationApi.getCode(email)).thenReturn("\"" + code + "\"");
        when(mockRegistrationApi.setStatus(token, "increased")).thenReturn(setStatusAnswer);

        String result = registrationApiService.registerCandidate(role, firstName, lastName, email);


        String expected = String.format("""
                     Registration is processed .
                     SignUp process answer: %s .
                     SettingStatus process answer (try to set status %s): %s
                    """, signUpAnswer, "increased", setStatusAnswer);
        assertEquals(expected, result);
    }


    /**
     * Проверка валидации входных параметров сервиса
     */
    @Test
    public void testRegisterCandidate_InvalidInputData() {

        String role = null;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";

        assertThrows(InvalidInputData.class,
                () -> registrationApiService.registerCandidate(role, firstName, lastName, email));

    }
    /**
     * Ловим ошибки в мапинге эксепшенов API, на эксепшены сервиса
     */
    @Test
    public void testRegisterCandidate_InternalServiceException() {

        String role = "candidate";
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";

        when(mockRegistrationApi.signUp(role, firstName, lastName, email))
                .thenThrow(new InternalServerException());


        InternalServiceException exception = assertThrows(InternalServiceException.class,
                () -> registrationApiService.registerCandidate(role, firstName, lastName, email));
    }
}
