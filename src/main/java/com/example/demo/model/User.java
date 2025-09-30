package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    @OneToMany(mappedBy="createdBy", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Task> tasks;

    @Column(nullable = true)
    private LocalDate accountEnabledDate;

    @Column(nullable = true)
    private LocalDate accountLockedDate;

    @Column(nullable = true)
    private LocalDate accountExpirationDate;

    @Column(nullable = true)
    private LocalDate credentialsExpirationDate;

    public User(
        String username, 
        String password, 
        Set<String> roles, 
        LocalDate accountEnabledDate, 
        LocalDate accountLockedDate,
        LocalDate accountExpirationDate, 
        LocalDate credentialsExpirationDate
    ) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.accountEnabledDate = accountEnabledDate;
        this.accountLockedDate = accountLockedDate;
        this.accountExpirationDate = accountExpirationDate;
        this.credentialsExpirationDate = credentialsExpirationDate;
    }

}
