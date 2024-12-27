package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull
        String username,

        @NotNull
        String password
) {
}