package com.webbanhangnongsan.vn.webbanhangnongsan.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long roleId;
    String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
