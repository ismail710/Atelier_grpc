package org.example.mediaclient.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.mediaclient.dto.CreatorDto;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.dto.VideoStreamDto;
import org.example.mediaclient.mapper.CreatorMapper;
import org.example.mediaclient.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.lab.*;

import java.util.stream.Collectors;

@Service
public class CreatorServiceClient {

    @GrpcClient("mediaserver")
    private CreatorServiceGrpc.CreatorServiceBlockingStub stub;

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private VideoMapper videoMapper;

    public CreatorDto getCreator(String id) {
        Creator req = stub.getCreator(CreatorIdRequest.newBuilder().setId(id).build());
        return creatorMapper.fromProto(req);
    }

    public VideoStreamDto getCreatorVideos(String id) {
        VideoStream stream = stub.getCreatorVideos(
                CreatorIdRequest.newBuilder().setId(id).build()
        );

        VideoStreamDto dto = new VideoStreamDto();
        dto.setVideos(
                stream.getVideosList().stream()
                        .map(videoMapper::fromVideoProto)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
