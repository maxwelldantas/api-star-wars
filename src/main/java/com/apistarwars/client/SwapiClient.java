package com.apistarwars.client;

import com.apistarwars.client.context.ContextClient;
import com.apistarwars.client.request.PlanetaRequest;
import com.apistarwars.client.request.PlanetasRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "swapi", url = "${api.star.wars}")
public interface SwapiClient {

    @GetMapping(value = ContextClient.OBTER_PLANETA, produces = "application/json")
    PlanetaRequest obterPlaneta(@PathVariable Integer numero);

    @GetMapping(value = ContextClient.OBTER_PLANETA, produces = "application/json")
    Map<String, Object> obterPlanetaMap(@PathVariable Integer numero);

    @GetMapping(value = ContextClient.OBTER_PLANETAS, produces = "application/json")
    PlanetasRequest obterPlanetas(@RequestParam("page") Integer page);

    @GetMapping(value = ContextClient.OBTER_PLANETAS, produces = "application/json")
    Map<String, Object> obterPlanetasMap(@RequestParam("page") Integer page);
}