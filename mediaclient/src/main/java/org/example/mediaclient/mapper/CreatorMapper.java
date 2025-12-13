package org.example.mediaclient.mapper;

import org.example.mediaclient.dto.CreatorDto;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;

@Component
public class CreatorMapper {

    // Convert Proto -> DTO
    public CreatorDto fromProto(Creator creator) {
        CreatorDto dto = new CreatorDto();
        dto.setId(creator.getId());
        dto.setName(creator.getName());
        dto.setEmail(creator.getEmail());
        return dto;
    }

    // Convert DTO -> Proto
    public Creator toProto(CreatorDto dto) {
        return Creator.newBuilder()
                .setId(dto.getId())
                .setName(dto.getName())
                .setEmail(dto.getEmail())
                .build();
    }
}
