package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.WallMessage;
import ru.starry_sky.model.data_layer.WallMessageComment;
import ru.starry_sky.model.domain_layer.WallCommentDTO;
import ru.starry_sky.model.domain_layer.WallMessageDTO;

import java.util.List;

public interface WallMessagesServices {
    boolean sendMessage(WallMessageDTO dto);
    List<WallMessage> getUserMessages(Long id);
    boolean postComment(WallCommentDTO dto);
    List<WallMessageComment> getMessageComments(Long messageID);
    List<WallMessageComment> getAllWallComments();
}
