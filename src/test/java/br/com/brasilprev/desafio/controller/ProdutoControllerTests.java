package br.com.brasilprev.desafio.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.brasilprev.desafio.model.entity.Produto;
import br.com.brasilprev.desafio.model.vo.ProdutoVO;
import br.com.brasilprev.desafio.repository.ProdutoRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
public class ProdutoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoRepository mockRepository;

    Produto p1 = new Produto("Produto Controller 1", new BigDecimal("45.00"));
    ProdutoVO produto1 = new ProdutoVO(null, "Produto Controller 1", new BigDecimal("45.00"), LocalDateTime.now());

    @Test
    public void testSalvarSuccess() throws Exception {

        Mockito.when( mockRepository.save(p1) ).thenReturn(p1);

        mockMvc.perform( MockMvcRequestBuilders.put("/api/clientes/salvar")
                            .content( asJsonString(produto1) )
                            .contentType( MediaType.APPLICATION_JSON )
                            .accept( MediaType.APPLICATION_JSON ))
                            .andExpect( MockMvcResultMatchers.status().isCreated() );

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}