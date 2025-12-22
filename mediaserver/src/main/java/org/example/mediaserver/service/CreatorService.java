package org.example.mediaserver.service;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.mediaserver.dao.entities.VideoEntity;
import org.example.mediaserver.dao.entities.CreatorEntity;
import org.example.mediaserver.dao.repositories.CreatorRepository;
import org.example.mediaserver.dao.repositories.VideoRepository;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.xproce.lab.*;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class CreatorService extends CreatorServiceGrpc.CreatorServiceImplBase {


    private final CreatorRepository creatorRepository;

    private final VideoRepository videoRepository;
    private final CreatorMapper mapper ;
    private final VideoMapper videoMapper ;



    @Override
    public void getCreator(CreatorIdRequest request, StreamObserver<Creator> responseObserver) {
    String id = request.getId();
        System.out.println("Received getCreator request for ID: " + id);
        creatorRepository.findById(id).ifPresentOrElse(
                creator -> {
                    System.out.println("Creator found: " + creator);
                    responseObserver.onNext(mapper.toProto(creator));
                    responseObserver.onCompleted();
                },
                () -> {
                    System.err.println("Creator not found for ID: " + id);
                    responseObserver.onError(new RuntimeException("Creator not found: " + id));
                }
        );
    }
    @Override
    public void getCreatorVideos(CreatorIdRequest request,
                                 StreamObserver<VideoStream> responseObserver) {

        List<VideoEntity> videos = videoRepository.findByCreatorId(request.getId())
                .stream()
                .toList();
        System.out.println(videos);
        responseObserver.onNext(videoMapper.toVideoStream(videos));
        responseObserver.onCompleted();
    }}
