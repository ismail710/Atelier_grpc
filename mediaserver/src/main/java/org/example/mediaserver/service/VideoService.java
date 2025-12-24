package org.example.mediaserver.service;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.mediaserver.dao.entities.CreatorEntity;
import org.example.mediaserver.dao.repositories.CreatorRepository;
import org.example.mediaserver.dao.repositories.VideoRepository;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.example.mediaserver.dao.entities.VideoEntity;
import org.springframework.util.StringUtils;
import org.xproce.lab.*;

@GrpcService
@RequiredArgsConstructor
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    private final VideoMapper mapper ;
    private final VideoRepository videoRepository ;
    private final CreatorRepository creatorRepository ;
    private final CreatorMapper creatorMapper ;
    @Override
    public void uploadVideo(UploadVideoRequest request,
                            StreamObserver<Video> responseObserver) {
        Creator creator = request.getCreator();
        if (creator == null) {
            responseObserver.onError(new IllegalArgumentException("Creator payload is required."));
            return;
        }

        CreatorEntity creatorEntity;
        if (StringUtils.hasText(creator.getId())) {
            creatorEntity = creatorRepository.findById(creator.getId())
                    .orElseGet(() -> creatorRepository.save(creatorMapper.fromProto(creator)));
        } else {
            creatorEntity = creatorRepository.save(creatorMapper.fromProto(creator));
        }

        VideoEntity entity = mapper.toEntity(request);
        entity.setCreator(creatorEntity);

        // Persist the video (creator cascades too for safety)
        videoRepository.save(entity);

        responseObserver.onNext(mapper.toProto(entity));
        responseObserver.onCompleted();
    }
    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        String id = request.getId();
        videoRepository.findById(id).ifPresentOrElse(v -> {
            responseObserver.onNext(mapper.toProto(v));
            responseObserver.onCompleted();
        }, () -> {
            responseObserver.onError(new RuntimeException("Video not found: " + id));
        });
    }
}