package org.example.mediaserver.dao.repositories;

import org.example.mediaserver.dao.entities.VideoEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VideoRepository {
    private final List<VideoEntity> videos = new ArrayList<>();

    public VideoEntity save(VideoEntity video) {
        videos.add(video);
        return video;
    }

    public Optional<VideoEntity> findById(String id) {
        return videos.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    public List<VideoEntity> findByCreatorId(String creatorId) {
        List<VideoEntity> result = new ArrayList<>();
        for (VideoEntity video : videos) {
            if (video.getCreator().getId().equals(creatorId)) {
                result.add(video);
            }
        }
        return result;
    }
}
