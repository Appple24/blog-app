package com.blogging.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Table(name="users")
@Getter
@Setter
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="user_name",nullable = false)
    private String name;
    private String password;
    private String email;
    private String about;
    @OneToMany
    private List<Post> posts=new ArrayList<>();

}
