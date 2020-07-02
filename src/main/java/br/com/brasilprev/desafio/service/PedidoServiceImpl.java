package br.com.brasilprev.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.converter.PedidoConverter;
import br.com.brasilprev.desafio.model.entity.Pedido;
import br.com.brasilprev.desafio.model.vo.PedidoVO;
import br.com.brasilprev.desafio.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoConverter converter;

    @Override
    public PedidoVO salvar(PedidoVO vo){
        Pedido pedido = pedidoRepository.save( converter.convertToEntity(vo) );
        return converter.convertToVO(pedido);
    }

    @Override
    public Page<PedidoVO> listar(Pageable pageable) {
        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        List<PedidoVO> vos = converter.convertToListVO(pedidos.getContent());
        return new PageImpl<PedidoVO>(vos, pageable, pedidos.getTotalElements());
    }

    @Override
    public PedidoVO buscarPorId(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return converter.convertToVO(pedido.get());
    }

    @Override
    public void deletar(Long id){
        pedidoRepository.deleteById(id);
    }

}