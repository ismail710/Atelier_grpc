package org.example.mediaserver.dao.repositories;

import org.example.mediaserver.dao.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity,String> {
    List<VideoEntity> findByCreatorId(String id);

}
