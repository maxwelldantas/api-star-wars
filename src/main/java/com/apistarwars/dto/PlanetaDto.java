package com.apistarwars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetaDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String clima;
    @NotBlank
    private String terreno;
}