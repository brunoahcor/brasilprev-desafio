package br.com.brasilprev.desafio.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.desafio.model.vo.ClienteVO;
import br.com.brasilprev.desafio.model.vo.ErroVO;
import br.com.brasilprev.desafio.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listar(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        try {
            Page<ClienteVO> clientes = clienteService.listar(PageRequest.of(page, pageSize));
            if (clientes == null || clientes.getTotalElements() < 1) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Page<ClienteVO>>(clientes, HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}/buscarPorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {

        try {

            ClienteVO vo = clienteService.buscarPorId(id);
            if (!Optional.ofNullable(vo).isPresent()) {
                return new ResponseEntity<>(new ErroVO(HttpStatus.NOT_FOUND, "Cliente nao encontrado!"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<ClienteVO>(vo, HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{cpf}/buscarPorCpf", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarPorCpf(@PathVariable("cpf") String cpf) {

        try {

            ClienteVO vo = clienteService.buscarPorCpf(cpf);
            if (!Optional.ofNullable(vo).isPresent()) {
                return new ResponseEntity<>(new ErroVO(HttpStatus.NOT_FOUND, "Cliente nao encontrado!"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<ClienteVO>(vo, HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<?> salvar(@Valid @RequestBody ClienteVO vo) {

        try {
            ClienteVO retorno = clienteService.salvar(vo);
            return new ResponseEntity<ClienteVO>(retorno, HttpStatus.CREATED);
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}/deletar", method = RequestMethod.DELETE, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {

        try {

            ClienteVO vo = clienteService.buscarPorId(id);
            if (!Optional.ofNullable(vo).isPresent()) {
                return new ResponseEntity<>(new ErroVO(HttpStatus.NOT_FOUND, "Cliente nao encontrado!"), HttpStatus.NOT_FOUND);
            }

            clienteService.deletar(vo.getId());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}