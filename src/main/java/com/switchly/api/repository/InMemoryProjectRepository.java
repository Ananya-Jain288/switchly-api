package com.switchly.api.repository;

import com.switchly.api.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryProjectRepository implements ProjectRepository {

    private final ConcurrentMap<UUID, Project> projects = new ConcurrentHashMap<>();

    @Override
    public Project save(Project project) {
        projects.put(project.getId(), project);
        return project;
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return Optional.ofNullable(projects.get(id));
    }

    @Override
    public List<Project> findByOrganizationId(UUID organizationId) {
        List<Project> result = new ArrayList<>();

        for (Project project : projects.values()) {
            if (project.getOrganizationId().equals(organizationId)) {
                result.add(project);
            }
        }

        return result;
    }
}

