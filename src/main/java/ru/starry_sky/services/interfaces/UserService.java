package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.domain_layer.NewUser;
import ru.starry_sky.model.domain_layer.UpdateUserProfileDTO;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    boolean createUser(NewUser newUser);
    User getUser(Long id);
    List<User> getUserFriends(Long id);
    User getUserByLogin(String login);
    boolean updateUserInfo(Long id, UpdateUserProfileDTO dto);
    List<User> findUserByName(String name);
    boolean inToCommunity(Long userID, Long communityID);
    boolean deleteFriend(Long userID, Long friendID);
    boolean disActive(Long id);
}
