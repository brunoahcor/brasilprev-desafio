package br.com.brasilprev.desafio.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.model.vo.ClienteVO;

@Component
public class ClienteConverter {

    @Autowired
    private ModelMapper mm;

    public ClienteVO toVO(Cliente cliente) {
        return mm.map(cliente, ClienteVO.class);
    } 

    public Cliente toEntity(ClienteVO vo) {
        return mm.map(vo, Cliente.class);
    } 

    public List<ClienteVO> convertToListVO(List<Cliente> clientes) {
        return clientes.stream().map(this::toVO).collect(Collectors.toList());
    } 

    public List<Cliente> convertToListEntity(List<ClienteVO> vos) {
        return vos.stream().map(this::toEntity).collect(Collectors.toList());
    } 
    
}