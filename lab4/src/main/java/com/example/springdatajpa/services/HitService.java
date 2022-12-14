package com.example.springdatajpa.services;

import com.example.springdatajpa.entities.Hit;
import com.example.springdatajpa.repositories.HitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HitService {

    private final HitRepository repository;

    public HitService(HitRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> service(Float x, Float y, Float r) {

        if (r < 0) {
            return ResponseEntity.badRequest().body("r parameter should be a positive number");
        }

        boolean hitted = isHit(x, y, r);

        Hit newHit = new Hit(x, y, r, hitted);

        repository.save(newHit);

        return ResponseEntity.ok().body(
                newHit
        );
    }

    private boolean isHit(Float x, Float y, Float r) {
        return x >= 0 && y >= 0 && x <= r && y <= r / 2
                || x <= 0 && y >= 0 && y <= 2 * x + r
                || x <= 0 && y <= 0 && (x * x + y * y <= r * r / 4);
    }

    public ResponseEntity<List<Hit>> getHits() {

        List<Hit> hits = new ArrayList<>();

        repository.findAll().forEach((hits::add));

        if (hits.size() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(hits);
    }

    public ResponseEntity delete() {
        if (repository.count() == 0) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
