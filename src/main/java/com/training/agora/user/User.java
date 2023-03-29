package com.training.agora.user;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;

@Data
@Table
@Entity(name = "users")
public class User {
    @Id
    @SequenceGenerator(sequenceName ="user_seq" ,name ="user_seq" ,allocationSize = 1)
    @GeneratedValue(generator = "user_seq",strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private long id;
    @Nonnull
    private String username;
    @Nonnull
//    @Size(min = 6,message = "Password must be more than 6 characters !!")
    private String password;
    @Nonnull
    private String fullname;
    @Nonnull
//    @Email(message = "InValid Email format !!")
    private String email;
    @Nonnull
    private String phone;
    private Date created_date;
    private Boolean isAdmin;

    public User(String username, String password, String fullname, String email, String phone,Boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.isAdmin=isAdmin;
        this.created_date=new Date();
    }

    public User() {
    }
}
