package ru.starry_sky.dao.interfases;

import ru.starry_sky.model.data_layer.Community;

import java.util.List;

public interface CommunitiesDao extends GenericDao<Community, Long>{
    List<Community> getUserCommunities(Long id);
}
