package org.ssmp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "staff")
@Getter
@Setter
public class Staff implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_worker")
    private long workerID;

    @ManyToOne
    @JoinColumn(name="post_id", referencedColumnName = "id_post")
    private Post post;

    @Column(name = "surname")
    private String surname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "patronimyc")
    private String patronymic;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "id_worker"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_role"))
    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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

    @Override
    public String toString() {
        return "Staff{" +
                "workerID=" + workerID +
                ", post='" + post + '\'' +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
