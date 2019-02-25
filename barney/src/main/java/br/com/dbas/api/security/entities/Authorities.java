package br.com.dbas.api.security.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authority")
public class Authorities implements GrantedAuthority {

    private static String PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "authority_endpoints",
            joinColumns = { @JoinColumn(name = "authority_id") },
            inverseJoinColumns = { @JoinColumn(name = "endpoints_id") })
    private Set<Endpoint> endpoints;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority.replaceAll(PREFIX, "");
    }

    public void setAuthority(String authority) {
        this.authority = PREFIX + authority;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(Set<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

}
