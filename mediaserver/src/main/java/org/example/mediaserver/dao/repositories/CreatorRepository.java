package org.example.mediaserver.dao.repositories;

import org.example.mediaserver.dao.entities.CreatorEntity;
import org.example.mediaserver.dao.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Objects;
@Repository
public interface CreatorRepository extends JpaRepository<CreatorEntity, String> {


}
