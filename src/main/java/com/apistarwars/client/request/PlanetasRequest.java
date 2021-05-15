package com.apistarwars.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanetasRequest {

    private Integer count;
    private String next;
    private String previous;
    private List<Object> results;
}