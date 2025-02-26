package gm.sandbox.websandbox.dto;


import gm.sandbox.websandbox.type.GenreType;
import gm.sandbox.websandbox.type.PlatformType;

public record GameDto(
        Long id,
        String name,
        GenreType genre,
        PlatformType platform,
        String rating,
        String description) {
}
