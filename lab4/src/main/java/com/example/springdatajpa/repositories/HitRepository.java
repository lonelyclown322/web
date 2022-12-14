package com.example.springdatajpa.repositories;

import com.example.springdatajpa.entities.Hit;
import org.springframework.data.repository.CrudRepository;

public interface HitRepository extends CrudRepository<Hit, Long> {
}
