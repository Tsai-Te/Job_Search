package com.te.domain;

import com.amazonaws.services.dynamodbv2.xspec.S;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

//19921221
@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="image_id_seq")
    @SequenceGenerator(name="image_id_seq", sequenceName = "image_id_seq", allocationSize=1)
    private Long id;

    @NotNull
    @Column(name="image_title", unique=true)
    private String imageTitle(){
        return uuid+"."+extension;
    }

    @NotNull
    @Column(name = "image_UUID", unique=true)
    private String uuid=UUID.randomUUID().toString();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user; //mappedBy = "user"
//    private Long user_id;//todo ask

    @Column(name="extension")
    private String extension;

//    private Long user_id;

//    @NotNull
//    @Column(name="image_file_name")
//    public String imageFileName (){
//        return uuid+"."+extension;
//    }

    public Long getId(){return id;}

//    public User getUser(){return user;}
//    public void setUser(User user){this.user=user;}

    public Long getUserId(){return this.user.getId();} //todo ask getId()

    public String getUuid(){
        return uuid;
    }

    public String getExtension(){
        return extension;
    }
    public void setExtension(String extension){
        this.extension=extension;
    }


}

