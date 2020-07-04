package br.com.brasilprev.desafio.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.brasilprev.desafio.model.vo.ClienteVO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ClienteServiceTests {

    @Autowired
    private ClienteService service;

    ClienteVO cliente1 = new ClienteVO(null, "Cliente Service 1", "136.241.890-00", "cs1@gmail.com", LocalDateTime.now());
    ClienteVO cliente2 = new ClienteVO(null, "Cliente Service 2", "537.963.080-40", "cs2@gmail.com", LocalDateTime.now());
    ClienteVO cliente3 = new ClienteVO(null, "Cliente Service 3", "310.638.640-11", "cs3@gmail.com", LocalDateTime.now());
    ClienteVO cliente4 = new ClienteVO(null, "Cliente Service 4", null, "cs4@gmail.com", LocalDateTime.now());

    @Test
    @Order(1)
    public void testSaveSuccess() throws Exception {
        ClienteVO c1 = service.salvar(cliente1);
        ClienteVO c2 = service.salvar(cliente2);
        assertNotNull(c1);
        assertNotNull(c2);
    }

    @Test
    @Order(1)
    public void testSaveError() {
        assertThrows(DataIntegrityViolationException.class, () -> service.salvar(cliente4));
    }

    @Test
    @Order(2)
    public void testBuscarPorCpfSuccess() {
        ClienteVO cliente = service.buscarPorCpf(cliente1.getCpf());
        assertTrue( Optional.ofNullable(cliente).isPresent() );
    }

    @Test
    @Order(3)
    public void testBuscarPorCpfError() {
        ClienteVO cliente = service.buscarPorCpf(cliente3.getCpf());
        assertTrue( !Optional.ofNullable(cliente).isPresent() );
    }

    @Test
    @Order(4)
    public void testDeletarPorIdSuccess() throws Exception {
        ClienteVO cliente = service.buscarPorCpf(cliente2.getCpf());
        service.deletar(cliente.getId());
    }

    @Test
    @Order(5)
    public void testDeletarPorIdError() {
        assertThrows(EmptyResultDataAccessException.class, () -> service.deletar(99999L));
    }

    @Test
    @Order(6)
    public void testListarSuccess() {
        Page<ClienteVO> cliente = service.listar(PageRequest.of(0, 10));
        assertTrue( cliente.getTotalElements() > 0);
    }
    
}