package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.domain_layer.PrivateMessageDTO;

import java.util.List;

public interface PrivateMessagesServices {
    boolean sendMessage(PrivateMessageDTO dto);
    List<PrivateMessage> getMessages();
}
