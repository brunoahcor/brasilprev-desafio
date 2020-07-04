package br.com.brasilprev.desafio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.converter.ClienteConverter;
import br.com.brasilprev.desafio.exception.BusinessException;
import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.model.vo.ClienteVO;
import br.com.brasilprev.desafio.repository.ClienteRepository;
import br.com.brasilprev.desafio.util.Validador;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteConverter converter;

    @Override
    public ClienteVO salvar(ClienteVO vo) throws Exception {
        if( !Optional.ofNullable( vo.getNome() ).isPresent() ){
            throw new BusinessException("NOME nao informado.",HttpStatus.BAD_REQUEST);
        }
        if( !Validador.isCPF(vo.getCpf()) ){
            throw new BusinessException("CPF nao informado ou invalido.",HttpStatus.BAD_REQUEST);
        }
        if( Validador.isEmail(vo.getEmail()) ){
            throw new BusinessException("EMAIL nao informado ou invalido.",HttpStatus.BAD_REQUEST);
        }
        vo.setDataCadastro( Optional.ofNullable(vo.getDataCadastro()).orElse(LocalDateTime.now()) );
        Cliente cliente = clienteRepository.save( converter.toEntity(vo) );
        return converter.toVO(cliente);
    }

    @Override
    public Page<ClienteVO> listar(Pageable pageable){
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        List<ClienteVO> vos = converter.convertToListVO(clientes.getContent());
        return new PageImpl<ClienteVO>(vos, pageable, clientes.getTotalElements());
    }

    @Override
    public ClienteVO buscarPorId(Long id) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return converter.toVO(cliente.get());
        } else {
            throw new BusinessException("Cliente nao encontrado com ID informado.",HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ClienteVO buscarPorCpf(String cpf) throws BusinessException {
        if( !Validador.isCPF(cpf) ){
            throw new BusinessException("CPF nao informado.",HttpStatus.BAD_REQUEST);
        }
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if(!cliente.isPresent()){
            throw new BusinessException("Cliente nao encontrado com CPF informado.",HttpStatus.NO_CONTENT);
        }
        return cliente.isPresent() ? converter.toVO(cliente.get()) : null;
    }

    @Override
    public void deletar(Long id) throws Exception {
        buscarPorId(id);
        clienteRepository.deleteById(id);
    }
    
}