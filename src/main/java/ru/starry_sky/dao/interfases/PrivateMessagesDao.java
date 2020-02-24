package ru.starry_sky.dao.interfases;

import ru.starry_sky.model.data_layer.PrivateMessage;

import java.util.List;

public interface PrivateMessagesDao extends GenericDao<PrivateMessage, Long> {
    List<PrivateMessage> getUserMessages(Long id);
}
