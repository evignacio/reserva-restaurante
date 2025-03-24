# Tech challenge FIAP 🎓

1. [Introdução](#Introducao)
2. [Principais funcionalidades](#Funcionalidades)
3. [Instalação](#Instalacao)
4. [Testes](#Testes)
5. [Cloud](#cloud)

## Introdução
Este projeto foi desenvolvido como parte do desafio técnico do curso de arquitetura em Java da FIAP. A aplicação consiste em uma API REST de gestão de reservas de restaurantes, desenvolvida utilizando arquitetura limpa.

### Desenvolvedores
* [Evandro 🧑🏾‍💻](https://github.com/evignacio) <br>
* [Ariel 🧑‍💻](https://github.com/Neuyer)

### Tecnologias utilizadas
![Java](https://img.shields.io/badge/Java-21-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-3.8.6-C71A36?style=for-the-badge&logo=apachemaven)
![Docker](https://img.shields.io/badge/Docker-24.0.6-2496ED?style=for-the-badge&logo=docker)
![Mongo](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)

## Principais funcionalidades
* Cadastrar restaurante
* Cadastrar usuário
* Buscar restaurante (filtros: Nome, endereço, categoria, data desejada da reserva)
* Realizar reserva (Funcionalidade do usuário)
* Buscar reservas
* Finalizar reserva (Funcionalidade do restaurante)
* Cadastrar avaliação de restaurante (Funcionalidade do usuário)

## Instalação
* Instale o Docker em sua máquina: [Docker](https://docs.docker.com/engine/install/)
* Via terminal, clone o repositório utilizando o comando: `git clone https://github.com/evignacio/reserva-restaurante`
* Após finalizar o download do projeto, execute o comando: `cd reserva-restaurante`
* Em seguida, execute: `docker-compose up -d`
* Por fim, para acessar os recursos oferecidos pela aplicação, acesse: [Swagger](http://localhost:8080/swagger-ui/index.html#/)

## Testes
Para executar os testes, basta executar o comando `mvn test` no terminal.

### Relatórios
Após a finalização dos testes, será possível também acessar os relatórios da aplicação seguindo as instruções listadas abaixo:
* Testes unitários/integrados: acesse o endereco [Allure reports](http://localhost:5050/allure-docker-service/projects/default/reports/latest/index.html?redirect=false)
* Performance: acesse o diretório `gatling/appsilumation-20250323172102588/index.html`
* BDD: acesse o diretório `target/cucumber-reports.html`

## Cloud
Acesse a aplicação no ambiente cloud utilizando o endereço [Swagger](https://reserva-restaurate.onrender.com/swagger-ui/index.html)