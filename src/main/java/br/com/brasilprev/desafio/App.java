package br.com.brasilprev.desafio;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.model.entity.Produto;
import br.com.brasilprev.desafio.repository.ClienteRepository;
import br.com.brasilprev.desafio.repository.ProdutoRepository;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        return args -> {

            // # Initial Clientes
            clienteRepository.findByCpf("753.073.290-08").orElseGet(()->
                clienteRepository.save(new Cliente("Guoradan", "753.073.290-08", "guoradan@gmail.com"))
            );
            clienteRepository.findByCpf("745.583.470-57").orElseGet(()->
                clienteRepository.save(new Cliente("Koyxos", "745.583.470-57", "koyxos@gmail.com"))
            );
            clienteRepository.findByCpf("446.742.770-86").orElseGet(()->
                clienteRepository.save(new Cliente("Doltithen", "446.742.770-86", "doltithen@gmail.com"))
            );

            // # Initial Produtos
            for (int i = 0; i < 20; i++) {
                BigDecimal valorUnidade = new BigDecimal("2").multiply(new BigDecimal(String.valueOf(i+1)));
                produtoRepository.save(new Produto("Produto ".concat(String.valueOf(i+1)), valorUnidade));
            }

        };
    }

}
