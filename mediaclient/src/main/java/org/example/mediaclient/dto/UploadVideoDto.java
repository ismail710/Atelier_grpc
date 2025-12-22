package org.example.mediaclient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UploadVideoDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String url;
    @Positive
    private int durationSeconds;
    @Valid
    @NotNull
    private CreatorDto creator;
}
