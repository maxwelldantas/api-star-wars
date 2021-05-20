package com.apistarwars.controller;

import com.apistarwars.controller.context.ContextController;
import com.apistarwars.dto.PlanetaDto;
import com.apistarwars.model.Planeta;
import com.apistarwars.service.PlanetaService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@OpenAPIDefinition(info = @Info(title = "API que contém os dados dos planetas do Star Wars."))
@RestController
@RequestMapping(ContextController.API)
public class PlanetaController {

    @Autowired
    PlanetaService planetaService;

    @Operation(summary = "Adicionar planeta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Planeta adicionado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Planeta não adicionado", content = @Content)})
    @PostMapping(value = ContextController.PLANETAS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

        var planeta = planetaService.adicionar(planetaDto, null);
        return ResponseEntity.created(URI.create(ContextController.API + "planetas")).body(planeta);
    }

    @Operation(summary = "Obter planetas ou obter planeta pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planetas obtidos ou planeta obtido pelo nome", content = @Content)})
    @GetMapping(ContextController.PLANETAS)
    public ResponseEntity<List<Planeta>> listar(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok().body((nome == null || nome.isBlank()) ? planetaService.listar() : planetaService.buscarPorNome(nome));
    }

    @Operation(summary = "Obter planeta pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planeta obtido pelo id", content = @Content)})
    @GetMapping(ContextController.PLANETAS_ID)
    public ResponseEntity<Optional<Planeta>> obterPorId(@PathVariable String id) {
        return ResponseEntity.ok().body(planetaService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar planeta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planeta atualizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Planeta não atualizado", content = @Content)})
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

        var planeta = planetaService.adicionar(planetaDto, id);
        return ResponseEntity.ok(planeta);
    }

    @Operation(summary = "Deletar planeta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planeta deletado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Planeta não deletado", content = @Content)})
    @DeleteMapping(ContextController.PLANETAS_ID)
    public ResponseEntity<HttpStatus> remover(@PathVariable String id) {
        planetaService.remover(id);
        return ResponseEntity.ok().build();
    }
}