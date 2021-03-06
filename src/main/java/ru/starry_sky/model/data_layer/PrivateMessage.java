package ru.starry_sky.model.data_layer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "private_messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PrivateMessage {


    @Id
    @SequenceGenerator(name = "messagesSequence", sequenceName = "all_messages_seq_pk", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messagesSequence")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sender_id", insertable = false, updatable = false)
    private User sender;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipient_id", insertable = false, updatable = false)
    private User recipient;

    @PrePersist
    private void preInsert(){
        if (getSendTime() == null){setSendTime(LocalDateTime.now());}
    }
}
