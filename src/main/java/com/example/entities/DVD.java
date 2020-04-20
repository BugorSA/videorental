package com.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dvd_disc")
public @Getter @Setter class DVD{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String russianName;
    @Column
    private String englishName;
    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public DVD() {}

    public DVD(String russianName, String englishName, int year){
        this.russianName = russianName;
        this.englishName = englishName;
        this.year = year;
    }

    @Override
    public String toString() {
        return "ID " + id + ": " + russianName + " " + englishName + " " + year;
    }
}
