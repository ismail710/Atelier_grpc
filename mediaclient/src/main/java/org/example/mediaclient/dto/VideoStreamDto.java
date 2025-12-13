package org.example.mediaclient.dto;

import lombok.Data;
import java.util.List;

@Data
public class VideoStreamDto {
    private List<VideoDto> videos;
}
