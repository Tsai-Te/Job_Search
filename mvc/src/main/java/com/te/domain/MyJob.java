package com.te.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="my_jobs")
public class MyJob {

    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="my_job_id_seq")
    @SequenceGenerator(name="my_job_id_seq", sequenceName = "my_job_id_seq", allocationSize=1)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @Column(name="saved_jobs")
    private String savedJobs="xxx";

    public Long getId(){return id;}

    public String getSavedJobs(){return savedJobs;}
    public void setSavedJobs(String savedJobs){this.savedJobs=savedJobs;}

//    public Long getUserId(){return this.user.getId();}
    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}

    @Override
    public String toString(){
        return String.format("[%d|%s]",id,savedJobs);
    }
}
