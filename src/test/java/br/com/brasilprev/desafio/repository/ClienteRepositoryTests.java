package br.com.brasilprev.desafio.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.brasilprev.desafio.model.entity.Cliente;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ClienteRepositoryTests {

    @Autowired
    private ClienteRepository repository;

    Cliente cliente1 = new Cliente("Cliente 1", "922.143.660-83", "c1@gmail.com");
    Cliente cliente2 = new Cliente("Cliente 2", "601.125.770-40", "c2@gmail.com");
    Cliente cliente3 = new Cliente("Milond", "691.786.980-57", "milond@gmail.com");
    Cliente cliente4 = new Cliente("Cliente 4", null, "c4@gmail.com");
    Cliente cliente5 = new Cliente("Cliente 5", "482.655.570-94", "c5@gmail.com");

    @Test
    @Order(1)
    public void testSaveSuccess() {
        Cliente c1 = repository.save( cliente1 );
        Cliente c2 = repository.save( cliente2 );
        Cliente c3 = repository.save( cliente3 );
        assertNotNull(c1);
        assertNotNull(c2);
        assertNotNull(c3);
    }

    @Test
    @Order(2)
    public void testSaveErrorCpfNull() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save( cliente4 );
        });
    }

    @Test
    @Order(3)
    public void testSaveErrorCpfExist() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save( cliente3 );
        });
    }

    @Test
    @Order(4)
    public void testFindByCpfSuccess() {
        Optional<Cliente> cliente = repository.findByCpf(cliente3.getCpf());
        assertTrue( cliente3.getCpf().equals( cliente.get().getCpf() ) );
    }

    @Test
    @Order(5)
    public void testFindByCpfError() {
        Optional<Cliente> cliente = repository.findByCpf(cliente5.getCpf());
        assertTrue( !cliente.isPresent() );
    }

    @Test
    @Order(6)
    public void testFindAll() {
        List<Cliente> clientes = repository.findAll();
        assertTrue(clientes.size() > 0);
    }

    @Test
    @Order(9)
    public void testDelete() {
        Optional<Cliente> cliente = repository.findByCpf(cliente3.getCpf());
        repository.delete(cliente.get());
        List<Cliente> clientes = repository.findAll();
        assertTrue( clientes.stream().anyMatch(c -> !cliente3.getCpf().equals( c.getCpf() )) );
    }

}