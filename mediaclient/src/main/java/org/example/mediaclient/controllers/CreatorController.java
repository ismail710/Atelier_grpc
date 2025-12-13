package org.example.mediaclient.controllers;

import org.example.mediaclient.dto.CreatorDto;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.dto.VideoStreamDto;
import org.example.mediaclient.service.CreatorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/creators")
public class CreatorController {

    @Autowired
    private CreatorServiceClient creatorServiceClient;

    @GetMapping("/{id}")
    public CreatorDto getCreator(@PathVariable String id) {
        return creatorServiceClient.getCreator(id);
    }
    @GetMapping("/{id}/videos")
    public VideoStreamDto getCreatorVideos(@PathVariable String id) {
        return creatorServiceClient.getCreatorVideos(id);
    }
}