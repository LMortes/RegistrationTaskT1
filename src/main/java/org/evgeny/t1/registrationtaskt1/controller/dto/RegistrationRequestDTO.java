package org.evgeny.t1.registrationtaskt1.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegistrationRequestDTO {

    @NotBlank(message = "first_name must not be blank")
    @NotNull(message = "first_name field not presented")
    private String firstName;

    @NotBlank(message = "last_name must not be blank")
    @NotNull(message = "last_name field not presented")
    private String lastName;

    @NotBlank(message = "email must not be blank")
    @Email(regexp = "^^(.+)@(\\S+)$")
    @NotNull
    private String email;

    @NotBlank(message = "role must not be blank")
    @NotNull(message = "role field not presented")
    private String role;
}
