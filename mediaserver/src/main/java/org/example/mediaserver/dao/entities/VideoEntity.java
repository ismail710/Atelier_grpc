package org.example.mediaserver.dao.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class VideoEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private CreatorEntity creator;

}
