package com.te.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="positions")
public class Position {

    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="position_id_seq")
    @SequenceGenerator(name="position_id_seq", sequenceName = "position_id_seq", allocationSize=1)
    private Long id;

    @Column(name="auditor")
    private String auditor;

    @Column(name="engineer")
    private String engineer;

    @Column(name="manager")
    private String manager;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="region_id")
    @JsonIgnore
    private Region region;

    public Long getId(){return id;}

    public String getAuditor(){return auditor;}
    public void setAuditor(String auditor){this.auditor=auditor;}

    public String getEngineer(){return engineer;}
    public void setEngineer(String engineer){this.engineer=engineer;}

    public String getManager(){return manager;}
    public void setManager(String manager){this.manager=manager;}

    public Region getRegion(){return region;}
    public void setRegion(Region region){this.region=region;}

    @Override
    public String toString(){
        return String.format("[%d|%s|%s|%s]",id,auditor,engineer,manager);
    }
}
