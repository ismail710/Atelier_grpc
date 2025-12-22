package org.example.mediaclient.controllers;

import jakarta.validation.Valid;
import org.example.mediaclient.dto.UploadVideoDto;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.service.VideoServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xproce.lab.Creator;
import org.xproce.lab.UploadVideoRequest;
@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoServiceClient videoService;
    @PostMapping("addVideo")
    public VideoDto uploadVideo(@Valid @RequestBody UploadVideoDto upload) {
        Creator.Builder creatorBuilder = Creator.newBuilder();

        if (upload.getCreator().getId() != null) creatorBuilder.setId(upload.getCreator().getId());
        if (upload.getCreator().getName() != null) creatorBuilder.setName(upload.getCreator().getName());
        if (upload.getCreator().getEmail() != null) creatorBuilder.setEmail(upload.getCreator().getEmail());

        UploadVideoRequest.Builder requestBuilder = UploadVideoRequest.newBuilder()
                .setTitle(upload.getTitle())
                .setCreator(creatorBuilder.build());

        if (upload.getDescription() != null) requestBuilder.setDescription(upload.getDescription());
        if (upload.getUrl() != null) requestBuilder.setUrl(upload.getUrl());
        if (upload.getDurationSeconds() > 0) requestBuilder.setDurationSeconds(upload.getDurationSeconds());

        return videoService.uploadVideo(requestBuilder.build());
    }
    @GetMapping("/{id}")
    public VideoDto getVideo(@PathVariable String id) {
        return videoService.getVideo(id);
    }
}
