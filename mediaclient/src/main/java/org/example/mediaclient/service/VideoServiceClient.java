package org.example.mediaclient.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;
import org.xproce.lab.VideoServiceGrpc;

@Service
public class VideoServiceClient {
    @GrpcClient("mediaserver")
    VideoServiceGrpc.VideoServiceBlockingStub stub;
    @Autowired
    private VideoMapper mapper;

    public VideoDto uploadVideo(UploadVideoRequest videoRequest) {
        Video video = stub.uploadVideo(videoRequest);
        VideoDto videoDto = mapper.fromVideoProto(video);
        return videoDto;
    }
    public VideoDto getVideo(String id) {
        var req = org.xproce.lab.VideoIdRequest.newBuilder()
                .setId(id)
                .build();

        Video video = stub.getVideo(req);
        return mapper.fromVideoProto(video);
    }
}
