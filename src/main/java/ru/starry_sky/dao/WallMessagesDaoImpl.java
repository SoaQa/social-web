package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import ru.starry_sky.dao.interfases.WallMessagesDao;
import ru.starry_sky.model.data_layer.WallMessage;

@Slf4j
public class WallMessagesDaoImpl extends GenericAbstractDaoImpl<WallMessage, Long> implements WallMessagesDao {
}
