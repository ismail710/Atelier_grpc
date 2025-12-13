package org.example.mediaclient.dto;

import lombok.Data;

import java.util.List;

@Data
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private CreatorDto creator;


}
