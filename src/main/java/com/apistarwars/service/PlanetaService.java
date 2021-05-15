package com.apistarwars.service;

import com.apistarwars.dto.PlanetaDto;
import com.apistarwars.model.Planeta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlanetaService {

    String obterQtdAparicaoFilme(String nomePlaneta);
    Planeta adicionar(PlanetaDto planeta);
    List<Planeta> listar();
    List<Planeta> buscarPorNome(String nome);
    Optional<Planeta> buscarPorId(String id);
    void remover(String id);
}