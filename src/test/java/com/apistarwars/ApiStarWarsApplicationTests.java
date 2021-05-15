package com.apistarwars;

import com.apistarwars.controller.PlanetaController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
class ApiStarWarsApplicationTests {

	@Autowired
	public PlanetaController planetaController = Mockito.mock(PlanetaController.class);

	@Test
	void deveReceberPlanetasComOuSemParametroOuParametroVazio() {
		String nomePlaneta = "Tatooine";
		Assertions.assertNotNull(planetaController.listar(nomePlaneta));
		Assertions.assertNotNull(planetaController.listar(null));
		Assertions.assertNotNull(planetaController.listar(""));
	}
}