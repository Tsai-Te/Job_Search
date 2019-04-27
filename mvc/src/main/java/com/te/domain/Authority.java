package com.te.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="authority_id_seq")
    @SequenceGenerator(name="authority_id_seq", sequenceName = "authority_id_seq", allocationSize=1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    @NotNull
    private User user;

    @NotNull
    @Column(name="authority")
    private String authority;

    @Column(name="delete_admin")
    @JsonIgnore
    private Boolean deleteAdmin=Boolean.FALSE;

    public Long getId(){return id;}

    @Override
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority){
        this.authority=authority;
    }

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }

    @JsonIgnore
    private Boolean isDeleteAdmin (){return deleteAdmin;}
}




