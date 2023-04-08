package com.training.agora.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Table
@Entity(name = "users")

public class User implements UserDetails {
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

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities=new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(isAdmin==true?"ROLE_ADMIN":"ROLE_USER"));
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
