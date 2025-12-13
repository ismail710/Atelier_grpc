package org.example.mediaserver.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.mediaserver.dao.repositories.CreatorRepository;
import org.example.mediaserver.dao.repositories.VideoRepository;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.example.mediaserver.dao.entities.VideoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;
import org.xproce.lab.VideoIdRequest;
import org.xproce.lab.VideoServiceGrpc;
import org.xproce.lab.VideoStream;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    private final VideoMapper mapper = new VideoMapper();
    private final VideoRepository repository = new VideoRepository();
    private final CreatorRepository creatorRepository = new CreatorRepository();
    @Override
    public void uploadVideo(UploadVideoRequest request,
                            StreamObserver<Video> responseObserver) {

        VideoEntity entity = mapper.toEntity(request);
        repository.save(entity);
        creatorRepository.save(entity.getCreator());
        responseObserver.onNext(mapper.toProto(entity));
        responseObserver.onCompleted();
    }


    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        String id = request.getId();
        repository.findById(id).ifPresentOrElse(v -> {
            responseObserver.onNext(mapper.toProto(v));
            responseObserver.onCompleted();
        }, () -> {
            responseObserver.onError(new RuntimeException("Video not found: " + id));
        });
    }
}