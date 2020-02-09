package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.starry_sky.dao.interfases.PrivateMessagesDao;
import ru.starry_sky.model.data_layer.PrivateMessage;

@Repository
@Slf4j
public class PrivateMessagesDaoImpl extends GenericAbstractDaoImpl<PrivateMessage, Long> implements PrivateMessagesDao {
}
