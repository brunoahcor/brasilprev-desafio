package br.com.brasilprev.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.converter.ClienteConverter;
import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.model.vo.ClienteVO;
import br.com.brasilprev.desafio.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteConverter converter;

    @Override
    public ClienteVO salvar(ClienteVO vo){
        Cliente cliente = clienteRepository.save( converter.convertToEntity(vo) );
        return converter.convertToVO(cliente);
    }

    @Override
    public Page<ClienteVO> listar(Pageable pageable){
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        List<ClienteVO> vos = converter.convertToListVO(clientes.getContent());
        return new PageImpl<ClienteVO>(vos, pageable, clientes.getTotalElements());
    }

    @Override
    public ClienteVO buscarPorId(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.isPresent() ? converter.convertToVO(cliente.get()) : null; 
    }

    @Override
    public ClienteVO buscarPorCpf(String cpf){
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
        return cliente.isPresent() ? converter.convertToVO(cliente.get()) : null;
    }

    @Override
    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }
    
}