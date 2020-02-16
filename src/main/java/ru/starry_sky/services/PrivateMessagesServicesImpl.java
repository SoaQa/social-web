package ru.starry_sky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.starry_sky.dao.interfases.PrivateMessagesDao;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.domain_layer.PrivateMessageDTO;
import ru.starry_sky.services.interfaces.PrivateMessagesServices;

import java.util.List;

@Service
public class PrivateMessagesServicesImpl implements PrivateMessagesServices {
    @Autowired
    private PrivateMessagesDao privateMessagesDao;

    public boolean sendMessage(PrivateMessageDTO dto){
        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setSenderID(dto.getSenderID());
        privateMessage.setRecipientID(dto.getRecipientID());
        privateMessage.setMessageBody(dto.getMessageBody());
        privateMessagesDao.save(privateMessage);
        return true;
    }

    public List<PrivateMessage> getMessages(){
        return privateMessagesDao.getAllData();
    }
}
