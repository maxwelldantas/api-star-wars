# API Star Wars

API que contém os dados dos planetas do Star Wars.

## Tecnologias

Tecnologias utilizadas no desenvolvimento:

- Java SE Development Kit 11 (Java JDK 11)
- Spring Boot v2.4.5
- MongoDB
- Gradle Wrapper
- Docker
- Swagger OpenAPI

O projeto utiliza o banco de dados MongoDB.

## 1. Requisitos e Configurações

- Para executar o projeto é necessária a instalação do Java JDK 11 e o Docker (caso queira executar o projeto pelo mesmo).

## 2. Executando o Projeto

#### Observação: Os comandos devem ser executados estando na raiz do projeto /api-star-wars

Caso não tenha instalado o Docker, basta executar o projeto por um dos comandos a seguir:

Execute o comando abaixo para sistemas baseados em Unix (Linux) utilizando o Terminal

```sh
$ ./gradlew bootRun
```

Execute o comando abaixo para sistema Windows utilizando o Prompt de Comando (cmd)

```sh
$ gradlew.bat bootRun
```

Caso tenha instalado o Docker e queira executar pelo mesmo, execute o comando abaixo

```sh
$ docker-compose up
```

Para acessar o projeto em um navegador será [http://localhost:8080/api-ui](http://localhost:8080/api-ui)
com acesso direto a documentação do projeto e endpoints criados utilizando o Swagger OpenAPI.
