# brasilprev-desafio
==================================================

Contexto:
--------------------------------------------------
Construa uma API REST para simular uma loja virtual. Esta loja deve ter um cadastro de seus
clientes, produtos e pedidos. Fique à vontade para escolher como fará a arquitetura do
sistema, bem como frameworks que utilizará.

Funcionalidades:
--------------------------------------------------
* Cadastro de clientes
* Cadastro de produtos
* Cadastro de pedidos
* Autenticacao

Requisitos:
--------------------------------------------------
* [Java Platform (JDK) 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)
* PostgreSQL
* Docker
* docker-compose
* Oauth2
* Junit + Mockito
* Swagger
* Heroku

Iniciando a aplicação com docker e docker-compose
--------------------------------------------------
1. Execute o comando no terminal `mvn clean install`
2. docker-compose up
3. Para ver a documentação da API Rest, basta digitar no navegador o endereço [http://localhost:8089/swagger-ui.html](http://localhost:8089/swagger-ui.html).
4. Logo apos sera solicitado userario(admin@brasilprev.com) e senha(123456).
4. Para acessar o PostgreSQL, basta digitar no navegador o endereço [http://localhost:8081/](http://localhost:8081/).