package br.com.brasilprev.desafio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.brasilprev.desafio.model.entity.Produto;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProdutoRepositoryTests {

    @Autowired
    private ProdutoRepository repository;

    private Produto produto1 = new Produto("Produto 100", new BigDecimal("15.00"));
    private Produto produto2 = new Produto("Produto 101", new BigDecimal("49.90"));
    private Produto produto3 = new Produto("Produto 102", new BigDecimal("99.99"));
 
    @Test
    @Order(1)
    public void testSaveSuccess() {
        Produto p1 = repository.save( produto1 );
        Produto p2 = repository.save( produto2 );
        assertNotNull(p1);
        assertNotNull(p2);
    }

    @Test
    @Order(2)
    public void testFindByIdSuccess(){
        //Optional<Produto> produto = repository.findById(1L);
        //assertNotNull(produto.get());
    }
    
    @Test
    @Order(3)
    public void testFindByNomeSuccess(){
        Optional<Produto> produto = repository.findByNome(produto1.getNome());
        assertEquals(produto1.getNome(), produto.get().getNome());
    }

    @Test
    @Order(4)
    public void testFindByNomeError(){
        Optional<Produto> produto = repository.findByNome(produto3.getNome());
        assertTrue( !produto.isPresent() );
    }

    @Test
    @Order(5)
    public void testFindAllSuccess(){
        List<Produto> produtos = repository.findAll();
        assertTrue( produtos.size() > 0 );
    }
    
}