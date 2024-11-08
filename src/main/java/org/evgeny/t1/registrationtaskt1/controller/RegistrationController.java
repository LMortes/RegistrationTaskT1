package org.evgeny.t1.registrationtaskt1.controller;

import jakarta.validation.Valid;
import org.evgeny.t1.registrationtaskt1.api.dto.RolesListDTO;
import org.evgeny.t1.registrationtaskt1.controller.dto.RegistrationRequestDTO;
import org.evgeny.t1.registrationtaskt1.controller.dto.RegistrationResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.evgeny.t1.registrationtaskt1.service.RegistrationService;



@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(@Autowired RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/roles")
    public RolesListDTO roles() {
        return new RolesListDTO(registrationService.getRoles());
    }

    @PostMapping("/register")
    public RegistrationResultDTO registerCandidate(@Valid @RequestBody RegistrationRequestDTO registrInfo) {
        String answer = registrationService.registerCandidate(
                registrInfo.getRole(),
                registrInfo.getFirstName(),
                registrInfo.getLastName(),
                registrInfo.getEmail());

        return new RegistrationResultDTO(answer);
    }

}
