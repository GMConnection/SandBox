package gm.sandbox.websandbox.dto;

import gm.sandbox.websandbox.type.GenreType;
import gm.sandbox.websandbox.type.PlatformType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record GameDto(
        Long id,
        @NotBlank(message = "{name_is_required}")
        String name,
        GenreType genre,
        PlatformType platform,
        @Pattern(regexp = "^[0-9](\\.[0-9])?$", message = "{rating_is_required}")
        String rating,
        @Size(min = 3, message = "{description_is_required}")
        String description) {
}
