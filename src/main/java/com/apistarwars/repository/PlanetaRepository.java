package com.apistarwars.repository;

import com.apistarwars.model.Planeta;
import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String> {

    List<Planeta> findByNomeIgnoreCaseContaining(String nome);
    @Override
    void deleteById(@NonNull String id);
}