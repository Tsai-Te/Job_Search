package com.te.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="regions")
public class Region {
    @Id
    @GeneratedValue(strategy= SEQUENCE, generator="region_id_seq")
    @SequenceGenerator(name="region_id_seq", sequenceName = "region_id_seq", allocationSize=1)
    private Long id;

    @Column(name="state")
    private String state;

    @Column(name="city")
    private String city;

    @Column(name="zip_code")
    private int zipCode;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "region", cascade = CascadeType.ALL)
    private List<Position> positions;

    public Long getId(){return id;}

    public String getState(){return state;}
    public void setState(String state){this.state=state;}

    public String getCity(){return city;}
    public void setCity(String city){this.city=city;}

    public int getZipCode(){return zipCode;}
    public void setZipCode(int zipCode){this.zipCode=zipCode;}

    public List<Position> getPositions(){return positions;}
    public void setPositions(List<Position> positions){this.positions=positions;}

    @Override
    public String toString(){
        return String.format("[%d|%s|%s|%d]",id,state,city,zipCode);
    }
}
