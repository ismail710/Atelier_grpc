package org.example.mediaserver.dao.repositories;

import org.example.mediaserver.dao.entities.CreatorEntity;

import java.util.*;

public class CreatorRepository {

    private final Map<String, CreatorEntity> store = new HashMap<>();

    public CreatorEntity save(CreatorEntity creator) {
        store.put(creator.getId(), creator);
        return creator;
    }

    public Optional<CreatorEntity> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<CreatorEntity> findAll() {
        return new ArrayList<>(store.values());
    }
}
