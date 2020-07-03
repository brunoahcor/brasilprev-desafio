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

    Cliente cliente1 = new Cliente("Jararn", "161.047.770-74", "jararn@gmail.com");
    Cliente cliente2 = new Cliente("Rokur", "657.282.350-42", "rokur@gmail.com");

    @BeforeEach
    public void setUp() {
        // # Initial Clientes
        clienteRepository.findByCpf(cliente1.getCpf()).orElseGet(()->clienteRepository.save(cliente1));
        clienteRepository.findByCpf(cliente2.getCpf()).orElseGet(()->clienteRepository.save(cliente2));

        // # Initial Produtos
        for (int i = 0; i < 20; i++) {
            BigDecimal valorUnidade = new BigDecimal("2").multiply(new BigDecimal(String.valueOf(i + 1)));
            produtoRepository.save(new Produto("Produto ".concat(String.valueOf(i + 1)), valorUnidade));
        }
    }

    @Test
    @Order(1)
    public void testSave() {

        Optional<Cliente> cliente = clienteRepository.findByCpf(cliente2.getCpf());

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
        /*
        repository.deleteAll();
        produtoRepository.deleteAll();
        clienteRepository.deleteAll();
        */
    }

}