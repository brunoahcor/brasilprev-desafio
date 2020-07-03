package br.com.brasilprev.desafio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.converter.PedidoConverter;
import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.model.entity.ItemPedido;
import br.com.brasilprev.desafio.model.entity.Pedido;
import br.com.brasilprev.desafio.model.entity.Produto;
import br.com.brasilprev.desafio.model.vo.ItemPedidoVO;
import br.com.brasilprev.desafio.model.vo.PedidoVO;
import br.com.brasilprev.desafio.repository.ClienteRepository;
import br.com.brasilprev.desafio.repository.PedidoRepository;
import br.com.brasilprev.desafio.repository.ProdutoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    private transient static Logger logger = LoggerFactory.getLogger(PedidoServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoConverter converter;

    @Override
    public PedidoVO salvar(PedidoVO vo){ 

        logger.info("### salvar :: vo = {}",vo.toString());

        Optional<Cliente> cliente = clienteRepository.findByCpf(vo.getCliente().getCpf());

        Pedido pedido = new Pedido( cliente.get() );
        
        //List<ItemPedido> itensPedido = converter.toListItemPedidoEntity(vo.getItensPedido());
        List<ItemPedido> lista = new ArrayList<>();
        pedido.setItensPedido(lista);
        
        for (ItemPedidoVO item : vo.getItensPedido()) {
            Optional<Produto> produto = produtoRepository.findById(item.getProduto().getId());
            logger.info("### salvar :: produto = {}",produto.toString());
            lista.add( new ItemPedido(10, produto.get(), pedido) );
        }

        pedido.setQtd(lista.stream().mapToInt(ItemPedido::getQtd).sum());
        pedido.setValorTotal(lista.stream().map(ItemPedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
        
        repository.save(pedido);

        return converter.toVO(pedido);
    }

    @Override
    public Page<PedidoVO> listar(Pageable pageable) {
        Page<Pedido> pedidos = repository.findAll(pageable);
        List<PedidoVO> vos = converter.toListVO(pedidos.getContent());
        return new PageImpl<PedidoVO>(vos, pageable, pedidos.getTotalElements());
    }

    @Override
    public PedidoVO buscarPorId(Long id){
        Optional<Pedido> pedido = repository.findById(id);
        return converter.toVO(pedido.get());
    }

    @Override
    public void deletar(Long id){
        repository.deleteById(id);
    }

}