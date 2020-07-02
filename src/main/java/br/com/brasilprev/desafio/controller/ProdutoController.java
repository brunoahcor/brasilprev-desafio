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

import br.com.brasilprev.desafio.model.vo.ProdutoVO;
import br.com.brasilprev.desafio.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listar(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        try {
            Page<ProdutoVO> produtos = produtoService.listar(PageRequest.of(page, pageSize));
            if (produtos == null || produtos.getTotalElements() < 1) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Page<ProdutoVO>>(produtos, HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}/buscarPorId", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {

		try {

            ProdutoVO vo = produtoService.buscarPorId(id);
			if (!Optional.of(vo).isPresent()) {
				return new ResponseEntity<String>("Produto nao foi encontrado!",HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<ProdutoVO>(vo, HttpStatus.OK);
		} catch (final Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value = "/salvar", 
                    method = RequestMethod.PUT, 
                    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<?> salvar(@Valid @RequestBody ProdutoVO vo) {

		try {
            ProdutoVO retorno = produtoService.salvar(vo);
            return new ResponseEntity<ProdutoVO>(retorno, HttpStatus.CREATED); 
		} catch (final Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value = "/{id}/deletar", 
                    method = RequestMethod.DELETE, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {

		try {

            ProdutoVO vo = produtoService.buscarPorId(id);
			if (!Optional.of(vo).isPresent()) {
				return new ResponseEntity<String>("Produto nao foi encontrado!",HttpStatus.NOT_FOUND);
			}

			produtoService.deletar(vo.getId());

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (final Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}