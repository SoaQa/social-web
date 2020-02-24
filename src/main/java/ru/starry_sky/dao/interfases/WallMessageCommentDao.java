package ru.starry_sky.dao.interfases;

import org.springframework.stereotype.Repository;
import ru.starry_sky.model.data_layer.WallMessageComment;

import java.util.List;

@Repository
public interface WallMessageCommentDao extends GenericDao<WallMessageComment, Long> {
    List<WallMessageComment> getMessageComments(Long messageID);
}
