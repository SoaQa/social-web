package ru.starry_sky.model.data_layer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.starry_sky.utils.Genders;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @SequenceGenerator(name = "usersSequence", sequenceName = "users_seq_pk", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersSequence")
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
    private Genders gender;

    @Column
    private String email;


    @Override
    public String toString(){
        return "UserID - " + this.id + ". User login - " + this.login;
    }

    // ниже идут связи для хибернейта
    @JsonManagedReference
    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrivateMessage> sentOutPrivateMessages;


    @JsonManagedReference
    @OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrivateMessage> receivedPrivateMessages;


    @JsonManagedReference
    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WallMessage> sentOutWallMessages;


    @JsonManagedReference
    @OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WallMessage> receivedWallMessages;



    @JsonManagedReference
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_communities",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "community_id") }
    )
    private List<Community> communities;



    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "friends",
            joinColumns = { @JoinColumn(name = "requester") },
            inverseJoinColumns = { @JoinColumn(name = "friend") }
    )
    private List<User> requester;


    @ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "requester")
    private List<User> friend;

}
