package br.com.brasilprev.desafio.controller;

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

import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.model.vo.ClienteVO;
import br.com.brasilprev.desafio.repository.ClienteRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
public class ClienteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository mockRepository;

    Cliente c1 = new Cliente("Cliente 1", "136.241.890-00", "c1@gmail.com");
    Cliente c2 = new Cliente("Cliente 1", null, "c1@gmail.com");
    ClienteVO cliente1 = new ClienteVO(null, "Cliente 1", "136.241.890-00", "c1@gmail.com", LocalDateTime.now());
    ClienteVO cliente2 = new ClienteVO(null, "Cliente 2", "123456", "c2@gmail.com", LocalDateTime.now());

    @Test
    public void testSalvarSuccess() throws Exception {

        Mockito.when( mockRepository.save(c1) ).thenReturn(c1);

        mockMvc.perform( MockMvcRequestBuilders.put("/api/clientes/salvar")
                            .content( asJsonString(cliente1) )
                            .contentType( MediaType.APPLICATION_JSON )
                            .accept( MediaType.APPLICATION_JSON ))
                            .andExpect( MockMvcResultMatchers.status().isCreated() );

    }

    @Test
    public void testSalvarError() throws Exception {

        Mockito.when( mockRepository.save(c2) ).thenReturn(c2);

        mockMvc.perform( MockMvcRequestBuilders.put("/api/clientes/salvar")
                            .content( asJsonString(cliente2) )
                            .contentType( MediaType.APPLICATION_JSON )
                            .accept( MediaType.APPLICATION_JSON ))
                            .andExpect( MockMvcResultMatchers.status().isBadRequest() );

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}