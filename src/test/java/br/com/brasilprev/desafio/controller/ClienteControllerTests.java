package br.com.brasilprev.desafio.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.brasilprev.desafio.model.vo.ClienteVO;
import br.com.brasilprev.desafio.service.ClienteService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ClienteControllerTests {

    @Autowired
    private ClienteService service;

    @Test
    public void testFindByCpfError() {
        ClienteVO cliente = service.buscarPorCpf("123");
        assertTrue( !Optional.ofNullable(cliente).isPresent() );
    }
    
}