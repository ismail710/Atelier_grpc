package org.example.mediaserver.dao.entities;

import lombok.Data;

import java.util.List;
@Data
public class VideoStreamEntity {
    private List<VideoEntity> videos;
}
