package br.com.brasilprev.desafio.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.brasilprev.desafio.model.vo.ClienteVO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PedidoControllerTests {

    @Autowired
    private ClienteController controller;

    ClienteVO cliente1 = new ClienteVO(null, "Enbi", "274.598.920-09", "enbi@gmail.com", LocalDateTime.now());
    ClienteVO cliente2 = new ClienteVO(null, "Muiuil", "228.365.080-19", "muiuil@gmail.com", LocalDateTime.now());

    @BeforeEach
    public void setUp() {

    }

}