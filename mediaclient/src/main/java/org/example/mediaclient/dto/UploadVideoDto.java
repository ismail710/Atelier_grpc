package org.example.mediaclient.dto;

import lombok.Data;

@Data
public class UploadVideoDto {
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private CreatorDto creator;
}
