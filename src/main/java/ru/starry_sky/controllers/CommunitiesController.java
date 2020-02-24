package ru.starry_sky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.starry_sky.model.data_layer.Community;
import ru.starry_sky.model.domain_layer.CreateCommunityDTO;
import ru.starry_sky.model.domain_layer.UserCommunityDTO;
import ru.starry_sky.services.interfaces.CommunitiesServices;

import java.util.List;

@RestController
@RequestMapping(value = "/communities")
public class CommunitiesController {
    @Autowired
    private CommunitiesServices communitiesServices;

    @PostMapping
    public ResponseEntity createCommunity(@RequestBody CreateCommunityDTO dto){
        if (communitiesServices.createCommunity(dto)){
            return ResponseEntity.ok("Community created");
        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Community not created.");
        }
    }

    @GetMapping
    public List<Community> getCommunities(){
        return communitiesServices.getCommunities();
    }

    @GetMapping(value = "/{id}")
    public Community getCommunityByID(@PathVariable Long id){
        return communitiesServices.getCommunityByID(id);
    }


}
