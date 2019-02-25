package br.com.dbas.api.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 200)
    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @Size(min = 1, max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "accountNonExpired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "users_authorities",
            joinColumns = { @JoinColumn(name = "users_id") },
            inverseJoinColumns = { @JoinColumn(name = "authorities_id") })
    private Collection<Authorities> authorities;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Collection<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authorities> authorities) {
        this.authorities = authorities;
    }


    //  true se a conta do usuário for válida (ou seja, não expirada),
    //  false se não for mais válido (ou seja, expirado)
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }


    //  true se o usuário não estiver bloqueado
    //  false caso contrário
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }


    //  true se as credenciais do usuário forem válidas (ou seja, não expiradas),
    //  false se não for mais válido (ou seja, expirado)
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    //  true se o usuário estiver habilitado,
    //  false caso contrário
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}