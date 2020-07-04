package br.com.brasilprev.desafio.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.brasilprev.desafio.model.vo.ClienteVO;
import br.com.brasilprev.desafio.model.vo.ItemPedidoVO;
import br.com.brasilprev.desafio.model.vo.PedidoVO;
import br.com.brasilprev.desafio.model.vo.ProdutoVO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PedidoServiceTests {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoService service;

    ClienteVO cliente1 = new ClienteVO(null, "Ciolar", "538.133.570-90", "ciolar@gmail.com", LocalDateTime.now());
    ClienteVO cliente2 = new ClienteVO(null, "Welfim", "397.197.300-05", "welfim@gmail.com", LocalDateTime.now());

    @BeforeEach
    public void setUp() throws Exception {
      
        if(Optional.ofNullable(clienteService.buscarPorCpf(cliente1.getCpf())).isPresent()){
            clienteService.salvar(cliente1);
        }
        if(Optional.ofNullable(clienteService.buscarPorCpf(cliente2.getCpf())).isPresent()){
            clienteService.salvar(cliente2);
        }
        
        // # Initial Produtos
        for (int i = 0; i < 20; i++) {
            BigDecimal valorUnidade = new BigDecimal("2").multiply(new BigDecimal(String.valueOf(i + 21)));
            produtoService.salvar( new ProdutoVO(null,"Produto ".concat(String.valueOf(i + 21)), valorUnidade, LocalDateTime.now()));
        }
    }

    @Test
    @Order(1)
    public void testSave() throws Exception {

        ClienteVO cliente = clienteService.buscarPorCpf(cliente1.getCpf());

        PedidoVO pedido = new PedidoVO(cliente);
        List<ItemPedidoVO> itensPedido = new ArrayList<>();
        pedido.setItensPedido(itensPedido);

        int qtdItensPedido = 15;
        ProdutoVO produto = null;
        for (int i = 0; i < qtdItensPedido; i++) {
            produto = produtoService.buscarPorId((long) i + 21);
            itensPedido.add( new ItemPedidoVO(qtdItensPedido, produto) );
        }

        pedido.setQtd(itensPedido.stream().mapToInt(ItemPedidoVO::getQtd).sum());
        pedido.setValorTotal(itensPedido.stream().map(ItemPedidoVO::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
        pedido = service.salvar(pedido);

        assertNotNull(pedido);

    }

}