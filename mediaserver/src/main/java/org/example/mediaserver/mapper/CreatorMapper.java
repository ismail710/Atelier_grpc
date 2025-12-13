package org.example.mediaserver.mapper;

import org.example.mediaserver.dao.entities.CreatorEntity;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
@Component
public class CreatorMapper {
    public CreatorEntity fromProto(Creator req) {
        CreatorEntity e = new CreatorEntity();
        e.setName(req.getName());
        e.setEmail(req.getEmail());
        return e;
    }

    public Creator toProto(CreatorEntity e) {
        return Creator.newBuilder()
                .setId(e.getId())
                .setName(e.getName())
                .setEmail(e.getEmail())
                .build();
    }
}
