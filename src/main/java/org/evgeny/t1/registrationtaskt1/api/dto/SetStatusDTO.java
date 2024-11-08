package org.evgeny.t1.registrationtaskt1.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SetStatusDTO {
    String status;
    String token;
}
