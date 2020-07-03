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

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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