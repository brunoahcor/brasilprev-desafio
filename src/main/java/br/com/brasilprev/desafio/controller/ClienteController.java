package br.com.brasilprev.desafio.controller;

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

import br.com.brasilprev.desafio.exception.BusinessException;
import br.com.brasilprev.desafio.model.vo.ClienteVO;
import br.com.brasilprev.desafio.model.vo.ErroVO;
import br.com.brasilprev.desafio.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "API desenvolvida para efetuar as operações dos clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Retorna uma lista de clientes.", response = ResponseEntity.class)	
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!"),
            @ApiResponse(code = 401, message = "Usuário não autenticado."),
            @ApiResponse(code = 403, message = "Usuário não possui autorização para acessar o recurso."),
	        @ApiResponse(code = 404, message = "O recurso nao foi encontrado."),
	        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado, contate o administrador.")
	})
    @RequestMapping(method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
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

    @ApiOperation(value = "Retorna o cliente pelo ID.", response = ResponseEntity.class)	
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!"),
            @ApiResponse(code = 401, message = "Usuário não autenticado."),
            @ApiResponse(code = 403, message = "Usuário não possui autorização para acessar o recurso."),
	        @ApiResponse(code = 404, message = "O recurso nao foi encontrado."),
	        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado, contate o administrador.")
	})
    @RequestMapping(value = "/{id}/buscarPorId", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {

        try {
            ClienteVO vo = clienteService.buscarPorId(id);
            return new ResponseEntity<ClienteVO>(vo, HttpStatus.OK);
        } catch (final BusinessException e) {
            return new ResponseEntity<>(new ErroVO(e.getStatus(), e.getMessage()), e.getStatus());
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Retorna o cliente pelo CPF.", response = ResponseEntity.class)	
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!"),
            @ApiResponse(code = 401, message = "Usuário não autenticado."),
            @ApiResponse(code = 403, message = "Usuário não possui autorização para acessar o recurso."),
	        @ApiResponse(code = 404, message = "O recurso nao foi encontrado."),
	        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado, contate o administrador.")
	})
    @RequestMapping(value = "/{cpf}/buscarPorCpf", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarPorCpf(@PathVariable("cpf") String cpf) {

        try {
            ClienteVO vo = clienteService.buscarPorCpf(cpf);
            return new ResponseEntity<ClienteVO>(vo, HttpStatus.OK);
        } catch (final BusinessException e) {
            return new ResponseEntity<>(new ErroVO(e.getStatus(), e.getMessage()), HttpStatus.NO_CONTENT);
        } catch (final Exception e) {
           return new ResponseEntity<>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@ApiOperation(value = "Salva o cliente no banco de dados.", response = ResponseEntity.class)	
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Cliente inserido com sucesso!"),
            @ApiResponse(code = 400, message = "Requisicao invalida."),
            @ApiResponse(code = 401, message = "Usuário não autenticado."),
            @ApiResponse(code = 403, message = "Usuário não possui autorização para acessar o recurso."),
	        @ApiResponse(code = 404, message = "O recurso nao foi encontrado."),
	        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado, contate o administrador.")
	})
    @RequestMapping(value = "/salvar", 
                    method = RequestMethod.PUT, 
                    produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<?> salvar(@Valid @RequestBody ClienteVO vo) {

        try {
            ClienteVO retorno = clienteService.salvar(vo);
            return new ResponseEntity<ClienteVO>(retorno, HttpStatus.CREATED);
        } catch (final BusinessException e) {
            return new ResponseEntity<>(new ErroVO(e.getStatus(), e.getMessage()), e.getStatus());
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Apaga o cliente do banco de dados.", response = ResponseEntity.class)	
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!"),
            @ApiResponse(code = 401, message = "Usuário não autenticado."),
            @ApiResponse(code = 403, message = "Usuário não possui autorização para acessar o recurso."),
	        @ApiResponse(code = 404, message = "O recurso nao foi encontrado."),
	        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado, contate o administrador.")
	})
    @RequestMapping(value = "/{id}/deletar", 
                    method = RequestMethod.DELETE, 
                    produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {

        try {
            clienteService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (final BusinessException e) {
            return new ResponseEntity<>(new ErroVO(e.getStatus(), e.getMessage()), e.getStatus());
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}