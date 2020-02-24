package ru.starry_sky.dao.interfases;

import ru.starry_sky.model.data_layer.WallMessage;

import java.util.List;

public interface WallMessagesDao extends GenericDao<WallMessage, Long> {
    List<WallMessage> getUserMessages(Long id);
}
