package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.Community;
import ru.starry_sky.model.domain_layer.CreateCommunityDTO;

import java.util.List;

public interface CommunitiesServices {
    boolean createCommunity(CreateCommunityDTO dto);
    Community getCommunityByID(Long id);
    List<Community> getCommunities();
    List<Community> getUserCommunities(Long userID);
}
