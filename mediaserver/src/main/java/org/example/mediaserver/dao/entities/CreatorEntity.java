package org.example.mediaserver.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CreatorEntity {
    @Id
    private String id;
    private String name;
    private String email;
}
