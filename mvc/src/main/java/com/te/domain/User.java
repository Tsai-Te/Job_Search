package com.te.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users") //table in jpa, java persistence, in order to serialize object into database
public class User implements UserDetails, Comparable<User>{
    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName = "user_id_seq", allocationSize=1)
    private Long id; //instance variable, define id variable, but id is empty now

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL) //mappedBy: every image class has "car" variable. CascadeType: if i delete a user, i have authority to delete.
    private List<Image> images;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)//todo where is this "user" from
//    private List<MyJob> myJobs;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    @Transient //not saving authority in user table (database)
    private List<Authority> authorities;

    @NotNull
    @Column(name="username", unique=true)
    private String username;

    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Column(name="email", unique=true)
    private String email;

    @NotNull
    @Column(name="password")
    private String password;

    @Column(name="date_of_birth")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
//    @JsonIgnore
    private Date dateOfBirth;

//  @ManyToOne(fetch=FetchType.LAZY)
//  @JoinColumn(name="car_id")
//  private Car car;

    @Column(name="account_expired")
//    @JsonIgnore // JsonIgnore will not show this column in postman, but we can find them in database
    private Boolean accountExpired=Boolean.FALSE;

    @Column(name="credentials_expired")
//    @JsonIgnore
    private Boolean credentialsExpired=Boolean.FALSE;

    @Column(name="account_locked")
//    @JsonIgnore
    private Boolean accountLocked=Boolean.FALSE;

    @Column(name="enabled")
//    @JsonIgnore
    private Boolean enabled=Boolean.TRUE; //todo ask what is enable

    @Column(name="delete_account")
//    @JsonIgnore
    private Boolean deleteAccount=Boolean.FALSE;

//    @JsonIgnore
    public Long getId() {
        return id;
    }

    //todo migrate column
    @Override
//    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
//    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                username.equals(user.username) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                dateOfBirth.equals(user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, dateOfBirth);
    }

    @Override
//    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
//    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

//    @Override
//    @JsonIgnore
    public boolean isDeleteAccount(){return deleteAccount;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}

//    @JsonIgnore
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName) {this.firstName=firstName;}

//    @JsonIgnore
    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName=lastName;}

//    @JsonIgnore
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public List<Image> getImages(){return images;}

    @Override
    @JsonIgnore
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    } //userDetails return whether this user has authority
    public void setAuthorities(List<Authority> authorities){this.authorities=authorities;}

    @Override
//    @JsonIgnore //todo ask why this cannot be JsonIgnore
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

//    @JsonIgnore
    public Date getDateOfBirth(){return dateOfBirth;}
    public void setDateOfBirth(Date dateOfBirth){this.dateOfBirth=dateOfBirth;}

    @Override
    public int compareTo(User user) {
        //return this.getUsername().compareTo(username);
        return this.id.intValue()-user.id.intValue();
       // return (this.username-user.username);
    }

    @Override
    public String toString(){
        return String.format("[%d|%s|%s|%s]",id,username,firstName,lastName,email);
    }


//    @Override
//    public int compare(User user1, User user2) {
//        return user1.getLastName().compareTo(user2.getLastName());
//    }

//    @Override
//    public int compare(User user1, User user2) {
//        return user1.getEmail().compareTo(user2.getEmail());
//    }

//    public List<MyJob> getMyJobs() {
//        return myJobs;
//    }
//    public void setMyJobs(List<MyJob> myJobs) {
//        this.myJobs = myJobs;
//    }

//    public List<Authority> getAuthority(){return authorities;}




//    public List<Image> getImagesId(){return images.get();}
}
