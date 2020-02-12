package ru.starry_sky.model.data_layer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wall_messages_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WallMessageComment {
    @Id
    @Column(name = "comment_id")
    @SequenceGenerator(name = "messagesSequence", sequenceName = "all_messages_seq_pk", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messagesSequence")
    private Long commentID;

    @Column(name = "message_id")
    private Long messageID;

    @Column(name = "send_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime sendTime;

    @Column(name = "sender_id")
    private Long senderID;

    @Column(name = "message_body")
    private String message_body;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="message_id", nullable=false, insertable = false, updatable = false)
    private WallMessage wallMessage;


}
