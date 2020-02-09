package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import ru.starry_sky.dao.interfases.WallMessageCommentDao;
import ru.starry_sky.model.data_layer.WallMessagesComment;

@Slf4j
public class WallMessageCommentDaoImpl extends GenericAbstractDaoImpl<WallMessagesComment, Long> implements WallMessageCommentDao {
}
