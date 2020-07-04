package br.com.brasilprev.desafio.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.desafio.model.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

   Page<Produto> findAll(Pageable pageable);

   @Query("select p from Produto p where upper(p.nome) in :nome")
   Optional<Produto> findByNome(@Param("nome") String nome);

   @Query("select p from Produto p where p.nome like %:nome%")
   Page<Produto> findByNome(Pageable pageable, @Param("nome") String nome);

}