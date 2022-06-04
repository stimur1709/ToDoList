package main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;

    private String lastName;
}
