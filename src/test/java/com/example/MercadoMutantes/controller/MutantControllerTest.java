package com.example.MercadoMutantes.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /mutant debe retornar 200 para mutante")
    void testCheckMutant_ReturnOk_WhenIsMutant() throws Exception {
        String jsonRequest = """
            {
              "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
            }
            """;
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());  // ← Verifica HTTP 200
    }
}
