package br.com.brasilprev.desafio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.desafio.model.vo.ErroVO;
import br.com.brasilprev.desafio.model.vo.PedidoVO;
import br.com.brasilprev.desafio.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(description = "API desenvolvida para efetuar as operações dos pedidos")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @ApiOperation(value = "Retorna uma lista de pedidos.", response = ResponseEntity.class)	
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!"),
            @ApiResponse(code = 401, message = "Usuário não autenticado."),
            @ApiResponse(code = 403, message = "Usuário não possui autorização para acessar o recurso."),
	        @ApiResponse(code = 404, message = "O recurso nao foi encontrado."),
	        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado, contate o administrador.")
	})
    @RequestMapping(method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listar(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        try {
            Page<PedidoVO> pedidos = pedidoService.listar(PageRequest.of(page, pageSize));
            if (pedidos == null || pedidos.getTotalElements() < 1) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Page<PedidoVO>>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Salva o produto no banco de dados.", response = ResponseEntity.class)	
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Produto inserido com sucesso!"),
            @ApiResponse(code = 400, message = "Requisicao invalida."),
            @ApiResponse(code = 401, message = "Usuário não autenticado."),
            @ApiResponse(code = 403, message = "Usuário não possui autorização para acessar o recurso."),
	        @ApiResponse(code = 404, message = "O recurso nao foi encontrado."),
	        @ApiResponse(code = 500, message = "Ocorreu um erro inesperado, contate o administrador.")
	})
    @RequestMapping(value = "/salvar", 
                    method = RequestMethod.PUT, 
                    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<?> salvar(@Valid @RequestBody PedidoVO vo) {

        try {
            PedidoVO retorno = pedidoService.salvar(vo);
            return new ResponseEntity<PedidoVO>(retorno, HttpStatus.CREATED);
        } catch (final Exception e) {
            return new ResponseEntity<ErroVO>(new ErroVO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}