package ru.starry_sky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.starry_sky.dao.interfases.CommunitiesDao;
import ru.starry_sky.model.data_layer.Community;
import ru.starry_sky.model.domain_layer.CreateCommunityDTO;
import ru.starry_sky.services.interfaces.CommunitiesServices;

import java.util.List;

@Service
public class CommunitiesServicesImpl implements CommunitiesServices {
    @Autowired
    private CommunitiesDao communitiesDao;

    public boolean createCommunity(CreateCommunityDTO dto){
        Community community = new Community();
        community.setAgeLimit(dto.getAgeLimit());
        community.setCommunityName(dto.getName());
        communitiesDao.save(community);
        return true;
    }

    public Community getCommunityByID(Long id){
        return communitiesDao.getByID(id);
    }

    public List<Community> getCommunities(){
        return communitiesDao.getAllData();
    }

    public List<Community> getUserCommunities(Long userID){
        return communitiesDao.getUserCommunities(userID);
    }
}
