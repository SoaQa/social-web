package ru.starry_sky.model.data_layer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "wall_messages")
@Data
public class PrivateMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "send_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime sendTime;

    @Column(name = "sender_id")
    private Long senderID;

    @Column(name = "recipient_id")
    private Long recipientID;

    @Column(name = "message_body")
    private String messageBody;

    @JsonBackReference
    @OneToMany(mappedBy = "sentOutPrivateMessages")
    private User sender;

    @JsonBackReference
    @OneToMany(mappedBy = "receivedPrivateMessages")
    private User recipient;
}
