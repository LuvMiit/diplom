package org.ssmp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private long roleID;

    @Column(name = "name_role")
    private String roleName;

//    @ManyToMany(mappedBy = "roles")
//    private Set<Staff> staffCollection = new HashSet<>();

    @Override
    public String getAuthority() {
        List<String> roles = new ArrayList<>();
        roles.add(roleName);
        return roles.getFirst();
    }
}
