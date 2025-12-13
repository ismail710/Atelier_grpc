package org.example.mediaserver.service;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


import org.example.mediaserver.dao.entities.VideoEntity;
import org.example.mediaserver.dao.entities.VideoStreamEntity;
import org.example.mediaserver.dao.repositories.CreatorRepository;
import org.example.mediaserver.dao.repositories.VideoRepository;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.xproce.lab.*;

import java.util.List;

@GrpcService
public class CreatorService extends CreatorServiceGrpc.CreatorServiceImplBase {

    private final CreatorRepository repository = new CreatorRepository();
    private final VideoRepository videoRepository = new VideoRepository();
    private final CreatorMapper mapper = new CreatorMapper();
    private final VideoMapper videoMapper = new VideoMapper();

    @Override
    public void getCreator(CreatorIdRequest request, StreamObserver<Creator> responseObserver) {
        String id = request.getId();

        repository.findById(id).ifPresentOrElse(
                creator -> {
                    responseObserver.onNext(mapper.toProto(creator));
                    responseObserver.onCompleted();
                },
                () -> responseObserver.onError(new RuntimeException("Creator not found: " + id))
        );
    }
    @Override
    public void getCreatorVideos(CreatorIdRequest request,
                                 StreamObserver<VideoStream> responseObserver) {

        List<VideoEntity> videos = videoRepository.findByCreatorId(request.getId())
                .stream()

                .toList();



        responseObserver.onNext(videoMapper.toVideoStream(videos));
        responseObserver.onCompleted();
    }



}