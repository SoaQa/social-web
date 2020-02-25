package ru.starry_sky.model.data_layer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.starry_sky.utils.enums.Genders;
import ru.starry_sky.utils.enums.Status;


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

    @JsonIgnore
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
    @Enumerated(EnumType.STRING)
    private Genders gender;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString(){
        return "UserID - " + this.id + ". User login - " + this.login;
    }

    // ниже идут связи для хибернейта
    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrivateMessage> sentOutPrivateMessages;


    @JsonIgnore
    @OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrivateMessage> receivedPrivateMessages;


    @JsonIgnore
    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WallMessage> sentOutWallMessages;


    @JsonIgnore
    @OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WallMessage> receivedWallMessages;


    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_communities",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "community_id") }
    )
    private List<Community> communities;


    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "friendship",
            joinColumns = { @JoinColumn(name = "requester") },
            inverseJoinColumns = { @JoinColumn(name = "friend") }
    )
    private List<User> requester;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "requester")
    private List<User> friend;

   // проставим дефолтные значения
    @PrePersist
    private void preInsert(){
        if(getStatus() == null){setStatus(Status.ACTIVE);}
    }
}
