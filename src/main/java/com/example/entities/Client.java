package com.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public @Getter @Setter class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column
    private String firstName;
    @Column
    private String secondName;
    private int telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DVD> dvdList;

    public Client(){}

    public Client(String firstName, String secondName, int telephone){
        this.firstName = firstName;
        this.secondName = secondName;
        this.telephone = telephone;
        dvdList = new ArrayList<>();
    }

    public String getFullName(){
        return firstName + " " + secondName;
    }

    public void addDVD(DVD dvd){
        dvd.setClient(this);
        dvdList.add(dvd);
    }
    public void removeDVD(DVD dvd){ dvdList.remove(dvd); }

    @Override
    public String toString() {
        return "ID " + clientId + ": " + secondName + " " + firstName + " " + telephone;
    }
}
