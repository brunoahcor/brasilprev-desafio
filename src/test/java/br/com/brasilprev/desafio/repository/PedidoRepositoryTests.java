package br.com.brasilprev.desafio.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.model.entity.ItemPedido;
import br.com.brasilprev.desafio.model.entity.Pedido;
import br.com.brasilprev.desafio.model.entity.Produto;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PedidoRepositoryTests {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository repository;

    @BeforeEach
    public void setUp() {

        // # Initial Clientes
        clienteRepository.findByCpf("161.047.770-74").orElseGet(()->
            clienteRepository.save(new Cliente("Jararn", "161.047.770-74", "jararn@gmail.com"))
        );

        clienteRepository.findByCpf("657.282.350-42").orElseGet(()->
            clienteRepository.save(new Cliente("Rokur", "657.282.350-42", "rokur@gmail.com"))
        );

        clienteRepository.findByCpf("538.133.570-90").orElseGet(()->
            clienteRepository.save(new Cliente("Ciolar", "538.133.570-90", "ciolar@gmail.com"))
        );

        clienteRepository.findByCpf("397.197.300-05").orElseGet(()->
            clienteRepository.save(new Cliente("Welfim", "397.197.300-05", "welfim@gmail.com"))
        );

        clienteRepository.findByCpf("274.598.920-09").orElseGet(()->
            clienteRepository.save(new Cliente("Enbi", "274.598.920-09", "enbi@gmail.com"))
        );

        // # Initial Produtos
        for (int i = 0; i < 20; i++) {
            BigDecimal valorUnidade = new BigDecimal("2").multiply(new BigDecimal(String.valueOf(i + 1)));
            produtoRepository.save(new Produto("Produto ".concat(String.valueOf(i + 1)), valorUnidade));
        }

    }

    @Test
    @Order(1)
    public void testSave() {

        Optional<Cliente> cliente = clienteRepository.findByCpf("161.047.770-74");

        Pedido pedido = new Pedido(cliente.get());
        List<ItemPedido> itensPedido = new ArrayList<>();
        pedido.setItensPedido(itensPedido);

        int qtdItensPedido = 10;
        Optional<Produto> produto = null;
        for (int i = 0; i < qtdItensPedido; i++) {
            produto = produtoRepository.findById((long) i + 1);
            itensPedido.add(new ItemPedido(qtdItensPedido, produto.get(), pedido));
        }

        pedido.setQtd(itensPedido.stream().mapToInt(ItemPedido::getQtd).sum());
        pedido.setValorTotal(itensPedido.stream().map(ItemPedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
        pedido = repository.save(pedido);

        assertNotNull(pedido);

    }

    @AfterEach
    public void testDeleteAll() {
        
        repository.deleteAll();
        produtoRepository.deleteAll();
        clienteRepository.deleteAll();
       
    }

}