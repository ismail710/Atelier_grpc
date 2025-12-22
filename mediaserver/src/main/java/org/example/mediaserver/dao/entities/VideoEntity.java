package org.example.mediaserver.dao.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class VideoEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private String url;
    private int durationSeconds;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "creator_id")
    private CreatorEntity creator;
}
