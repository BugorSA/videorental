package com.example.repositories;

import com.example.entities.Client;
import com.example.entities.DVD;
import org.springframework.data.repository.CrudRepository;

public interface DVDRepository extends CrudRepository<DVD, Integer> {
    Iterable<DVD> findAllByClientEquals(Client client);
}
