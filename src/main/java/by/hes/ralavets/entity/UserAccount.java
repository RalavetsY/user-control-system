package by.hes.ralavets.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Data
@Entity
public class UserAccount implements UserDetails{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=16, nullable=false, unique=true)
    private String username;

    @Column(length=60, nullable=false)
    private String password;

    @Column(length=16, nullable=false)
    private String firstName;

    @Column(length=16, nullable=false)
    private String lastName;

    @Column(length=12, nullable=false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable=false)
    private boolean status;

    @Column(nullable = false)
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status;
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