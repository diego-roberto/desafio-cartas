# Desafio Cartas API
<h3>
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot"/>
<img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
<img src="https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white"/>
<img src="https://img.shields.io/badge/PostgeSQL-003545?style=for-the-badge&logo=postgre&logoColor=white"/>
<img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white"/>
<img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white"/>
<img src="https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white"/>
</h3>

### Tecnologias utilizadas
Java 17 </br>
Spring-boot 2.7.11</br>
Swagger 3.0.0</br>
JUnit 5.8.2</br>
Mockito 3.11.2</br>
Lombok 1.18.26</br>
PostgreSQL 13</br>

Este projeto consiste na implementação de uma aplicação Spring Boot que utiliza a API Deck of Cards para criar um baralho e, em seguida, montar quatro mãos de cartas, cada uma com cinco cartas.
</br>

## Regras do Jogo
O objetivo é calcular a somatória das cartas em cada mão e determinar o vencedor. Em caso de empate, o sistema identifica e retorna os vencedores empatados.

>A = 1 </br>
>K = 13 </br>
>Q = 12 </br>
>J = 11 </br>

<br></br>
<img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white">

## Executando em ambiente local com Docker 🐋
A partir da pasta raiz do projeto, acesse a pasta docker-compose e execute o comando para iniciar o container:
> docker-compose up --build
>

Feito isso, verifique se os containers estão "up" e faça uma requisição na API utilizando Postman ou até mesmo o swagger-ui:
> localhost:8080/play/new-game
>

>http://localhost:8080/swagger-ui/#/play/new/game
>

O container postgresql é dependência do container da API, então irá iniciar antes do build, automaticamente.
Senão, utilize o comando abaixo antes de executar o backend novamente:

> docker-compose up -d postgresql
>


<br></br>
<img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/></br>

## Executando em ambiente local com Maven
### Na raiz da pasta do backend, execute:
> mvn install
>
> mvn spring-boot:run
>

#### Ou utilize a IDE de sua preferência.

<br></br>
<img src="https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white"/>
## Executando testes unitários:
> ./mvnw test
>

Porém, recomendo que os testes sejam executados pela IDE, devido a variáveis de ambiente e versões de Java e Maven.
Cada classe de teste pode ser testada independente.

<br></br>
<img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white"/>
## Documentação da API:
> http://localhost:8080/swagger-ui.html
>
<p align="center">
  <img width="860" height="500" src="desafio-cartas/src/main/resources/assets/swagger_ui.png">
</p>

# Diagrama da Base de Dados
<p align="center">
  <img width="500" height="400" src="desafio-cartas/src/main/resources/assets/db_schm.png">
</p>

# Estrutura de Containers - Docker
<p align="center">
  <img width="500" height="400" src="desafio-cartas/src/main/resources/assets/docker_containers.png">
</p>