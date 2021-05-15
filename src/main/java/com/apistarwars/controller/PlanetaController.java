package com.apistarwars.controller;

import com.apistarwars.controller.context.ContextController;
import com.apistarwars.dto.PlanetaDto;
import com.apistarwars.model.Planeta;
import com.apistarwars.service.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ContextController.API)
public class PlanetaController {

    @Autowired
    PlanetaService planetaService;

    @SuppressWarnings({})
    @GetMapping("teste/{nomePlaneta}")
    public ResponseEntity<Object> teste(@PathVariable String nomePlaneta) {
        planetaService.obterQtdAparicaoFilme(nomePlaneta);
        return null;
    }

    @PostMapping(ContextController.PLANETAS)
    public ResponseEntity<Object> adicionar(@Valid @RequestBody PlanetaDto planetaDto, BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        var planetaExiste = planetaService.buscarPorNome(planetaDto.getNome());

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> "O campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!planetaExiste.isEmpty()) {
            response.put("Error", "O planeta ".concat(planetaDto.getNome()).concat(" já existe na base de dados"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        var planeta = planetaService.adicionar(planetaDto);
        return ResponseEntity.created(URI.create(ContextController.API + "planetas")).body(planeta);
    }

    @GetMapping(ContextController.PLANETAS)
    public ResponseEntity<List<Planeta>> listar(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok().body((nome == null || nome.isBlank()) ? planetaService.listar() : planetaService.buscarPorNome(nome));
    }

    @GetMapping(ContextController.PLANETAS_ID)
    public ResponseEntity<Optional<Planeta>> obterPorId(@PathVariable String id) {
        return ResponseEntity.ok().body(planetaService.buscarPorId(id));
    }

    @PatchMapping(ContextController.PLANETAS_ID)
    public ResponseEntity<Object> atualizar(@PathVariable String id, @Valid @RequestBody PlanetaDto planetaDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        var planetasExistentes = planetaService.listar();
        Optional<Planeta> planetaDestaAtualizacao = planetaService.buscarPorId(id);

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> "O campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if ((planetaDestaAtualizacao.isPresent() && (!planetaDestaAtualizacao.get().getNome().equals(planetaDto.getNome()))) && planetasExistentes.toString().contains(planetaDto.getNome())) {
            response.put("Error", "O planeta ".concat(planetaDto.getNome()).concat(" já existe na base de dados"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        planetaDto.setId(id);
        var planeta = planetaService.adicionar(planetaDto);
        return ResponseEntity.ok(planeta);
    }

    @DeleteMapping(ContextController.PLANETAS_ID)
    public ResponseEntity<HttpStatus> remover(@PathVariable String id) {
        planetaService.remover(id);
        return ResponseEntity.ok().build();
    }
}