package org.example.mediaserver.mapper;

import org.example.mediaserver.dao.entities.CreatorEntity;
import org.example.mediaserver.dao.entities.VideoEntity;
import org.example.mediaserver.dao.entities.VideoStreamEntity;
import org.springframework.stereotype.Component;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;
import org.xproce.lab.VideoStream;

import java.util.List;

@Component
public class VideoMapper {

    public VideoEntity toEntity(UploadVideoRequest request) {
        VideoEntity video = new VideoEntity();
        video.setTitle(request.getTitle());
        video.setDescription(request.getDescription());
        video.setUrl(request.getUrl());
        video.setDurationSeconds(request.getDurationSeconds());

        CreatorEntity creator = new CreatorEntity();
        creator.setId(request.getCreator().getId());
        creator.setName(request.getCreator().getName());
        creator.setEmail(request.getCreator().getEmail());

        video.setCreator(creator);
        video.setId(java.util.UUID.randomUUID().toString());

        return video;
    }

    public org.xproce.lab.Video toProto(VideoEntity entity) {
        org.xproce.lab.Creator creator = org.xproce.lab.Creator.newBuilder()
                .setId(entity.getCreator().getId())
                .setName(entity.getCreator().getName())
                .setEmail(entity.getCreator().getEmail())
                .build();

        return org.xproce.lab.Video.newBuilder()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setUrl(entity.getUrl())
                .setDurationSeconds(entity.getDurationSeconds())
                .setCreator(creator)
                .build();
    }
    public VideoStream toVideoStream(List<VideoEntity> entities) {

        List<Video> videos = entities.stream()
                .map(this::toProto)   // VideoEntity → Video
                .toList();

        return VideoStream.newBuilder()
                .addAllVideos(videos)
                .build();
    }


}
