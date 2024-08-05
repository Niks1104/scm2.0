package com.scm.entities;

import org.aspectj.apache.bcel.classfile.Module.Provide;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @Column(name="user_id")
    private String userId;
    @Column(name="user_nm", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @Column(length = 1000)
    private String about;

    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;

    private boolean enabled=false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    //Signup -> SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
}
