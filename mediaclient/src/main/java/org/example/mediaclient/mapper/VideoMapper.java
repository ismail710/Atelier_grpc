package org.example.mediaclient.mapper;

import org.example.mediaclient.dto.CreatorDto;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.dto.UploadVideoDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;

@Component
public class VideoMapper {

    private final ModelMapper mapper = new ModelMapper();

    public VideoDto fromVideoProto(Video video) {
        return mapper.map(video, VideoDto.class);
    }

    public UploadVideoRequest fromUploadVideoDto(UploadVideoDto dto) {
        Creator creator = Creator.newBuilder()
                .setId(dto.getCreator().getId())
                .setName(dto.getCreator().getName())
                .setEmail(dto.getCreator().getEmail())
                .build();

        return UploadVideoRequest.newBuilder()
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setUrl(dto.getUrl())
                .setDurationSeconds(dto.getDurationSeconds())
                .setCreator(creator)
                .build();
    }
}
