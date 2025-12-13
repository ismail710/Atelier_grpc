package org.example.mediaserver.dao.entities;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CreatorEntity {
    @Id
    private String id;
    private String name;
    private String email;
}
