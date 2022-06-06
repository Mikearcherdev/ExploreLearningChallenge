package com.marcher.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = { @UniqueConstraint(columnNames = {"firstName", "lastName"})})
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;
}
