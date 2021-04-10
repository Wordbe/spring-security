package co.wordbe.springsecurity.service;

import co.wordbe.springsecurity.domain.entity.Resources;

import java.util.List;

public interface ResourcesService {

    Resources getResource(Long id);

    List<Resources> getResources();

    void createResources(Resources resources);

    void deleteResources(Long id);
}
