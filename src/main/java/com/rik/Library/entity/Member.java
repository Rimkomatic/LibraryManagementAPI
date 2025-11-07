package com.rik.Library.entity;

import com.rik.Library.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "members")
public class Member {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDate joined_date;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ADMIN','CUSTOMER') DEFAULT 'CUSTOMER'")
    private Role role = Role.CUSTOMER;

    @PrePersist
    public void setJoiningDate() {
        if (joined_date == null) {
            joined_date = LocalDate.now();
        }
    }
}
