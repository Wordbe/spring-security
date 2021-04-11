package co.wordbe.springsecurity.service.impl;

import co.wordbe.springsecurity.domain.entity.Resources;
import co.wordbe.springsecurity.repository.ResourcesRepository;
import co.wordbe.springsecurity.service.ResourcesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;

    @Transactional
    @Override
    public Resources getResources(long id) {
        return resourcesRepository.findById(id).orElse(new Resources());
    }

    @Transactional
    @Override
    public List<Resources> getResources() {
        return resourcesRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    @Transactional
    @Override
    public void createResources(Resources resources){
        resourcesRepository.save(resources);
    }

    @Transactional
    @Override
    public void deleteResources(long id) {
        resourcesRepository.deleteById(id);
    }
}
