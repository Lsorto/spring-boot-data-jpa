package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    private String username;
    @Column(length = 60)
    private String password;
    private Boolean enable;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Role> roles;

    public Usuario() {
    }

    public Usuario(Long id, String username, String password, Boolean enable) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enable = enable;
    }

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    private static final Long serialVersionUID = 1L;
}
