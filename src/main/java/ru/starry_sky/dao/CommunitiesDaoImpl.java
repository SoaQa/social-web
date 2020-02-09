package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import ru.starry_sky.dao.interfases.CommunitiesDao;
import ru.starry_sky.model.data_layer.Community;

@Slf4j
public class CommunitiesDaoImpl extends GenericAbstractDaoImpl<Community, Long> implements CommunitiesDao {
}
