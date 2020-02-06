package ru.starry_sky.model.data_layer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Таблица users
 */
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "birth_date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    @Column
    private String gender;

    @Column(nullable = false)
    private String email;


    // ниже идут связи для хибернейта

    @JsonManagedReference
    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY)
    private List<PrivateMessage> sentOutPrivateMessages;


    @JsonManagedReference
    @OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY)
    private List<PrivateMessage> receivedPrivateMessages;


    @JsonManagedReference
    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY)
    private List<WallMessage> sentOutWallMessages;


    @JsonManagedReference
    @OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY)
    private List<WallMessage> receivedWallMessages;



    @JsonManagedReference
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_communities",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "community_id") }
    )
    private List<Communities> communities;


    @JsonBackReference
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "friends",
            joinColumns = { @JoinColumn(name = "requester") },
            inverseJoinColumns = { @JoinColumn(name = "friend") }
    )
    private List<User> requester;


    @JsonManagedReference
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "friends",
            joinColumns = { @JoinColumn(name = "friend") },
            inverseJoinColumns = { @JoinColumn(name = "requester") }
    )
    private List<User> friend;

}
