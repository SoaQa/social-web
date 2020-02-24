package ru.starry_sky.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.starry_sky.dao.interfases.WallMessageCommentDao;
import ru.starry_sky.dao.interfases.WallMessagesDao;
import ru.starry_sky.model.data_layer.WallMessage;
import ru.starry_sky.model.data_layer.WallMessageComment;
import ru.starry_sky.model.domain_layer.WallCommentDTO;
import ru.starry_sky.model.domain_layer.WallMessageDTO;
import ru.starry_sky.services.interfaces.WallMessagesServices;

import java.util.List;

@Service
@Slf4j
public class WallMessagesServicesImpl implements WallMessagesServices {

    @Autowired
    private WallMessagesDao wallMessagesDao;
    @Autowired
    private WallMessageCommentDao wallMessageCommentDao;

    public boolean sendMessage(WallMessageDTO dto){
        try{
        WallMessage wallMessage = new WallMessage();
        wallMessage.setSenderID(dto.getSenderID());
        wallMessage.setRecipientID(dto.getRecipientID());
        wallMessage.setMessageBody(dto.getBody());
        wallMessagesDao.save(wallMessage);
        return true;
        } catch (Exception e){
            log.error("sendMessage error", e);
            return false;
        }

    }

    @Override
    public List<WallMessage> getUserMessages(Long id) {
        return wallMessagesDao.getUserMessages(id);
    }

    public boolean postComment(WallCommentDTO dto){
        try{
            WallMessageComment comment = new WallMessageComment();
            comment.setSenderID(dto.getSenderID());
            comment.setMessageBody(dto.getBody());
            comment.setMessageID(dto.getMessageID());
            wallMessageCommentDao.save(comment);
            return true;
        }  catch (Exception e){
        log.error("sendMessage error", e);
        return false;
    }
    }

    public List<WallMessageComment> getMessageComments(Long messageID){
        return wallMessageCommentDao.getMessageComments(messageID);
    }

    public List<WallMessageComment> getAllWallComments(){
        return wallMessageCommentDao.getAllData();
    }

}
