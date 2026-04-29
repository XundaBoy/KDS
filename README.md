# KDS - Plataforma de Troca de Jogos

API backend desenvolvida em Java e Spring Boot para uma plataforma de cadastro, anuncio e troca de jogos entre usuarios. O projeto foi construido como trabalho academico em dupla, durante o curso de Engenharia de Software, com foco em aplicar conceitos de backend, persistencia de dados, regras de negocio e integracao com frontend.

## Contexto

O KDS simula uma plataforma em que usuarios podem cadastrar jogos, criar anuncios, visualizar itens disponiveis, conversar com outros usuarios e solicitar trocas. A principal regra de negocio esta no fluxo de trocas: um usuario solicita a troca de um jogo seu por um jogo de outro usuario, os jogos ficam indisponiveis durante o processo e a troca so e concluida quando ambos confirmam.

Repositorio original: https://github.com/XundaBoy/KDS

## Minha Participacao

Participei do desenvolvimento do projeto em dupla, contribuindo com o backend Java/Spring, modelagem de entidades, controllers, services, repositories e regras de negocio da aplicacao.

Tambem tive contato, em contexto academico, com deploy usando Docker, VMs Alpine Linux, Tomcat, Nginx e HAProxy para subir frontend e backend.

## Tecnologias

- Java 17
- Spring Boot 3.4.3
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Security
- Bean Validation
- MySQL
- Maven
- Lombok
- WebSocket
- JUnit / Spring Boot Test
- JaCoCo

## Funcionalidades

- Cadastro e gerenciamento de usuarios.
- Cadastro e gerenciamento de cidades.
- Cadastro e gerenciamento de rankings.
- Cadastro e gerenciamento de consoles.
- Cadastro e gerenciamento de categorias.
- Cadastro e gerenciamento de jogos.
- Criacao e consulta de anuncios.
- Solicitacao de troca entre usuarios.
- Confirmacao de troca pelos participantes.
- Cancelamento de troca.
- Controle de disponibilidade dos jogos durante o fluxo de troca.
- Historico e envio de mensagens entre usuarios.
- Tratamento global de erros de validacao.

## Regras de Negocio Principais

### Fluxo de Troca

- Um usuario nao pode solicitar troca consigo mesmo.
- O jogo ofertado deve pertencer ao usuario que esta solicitando a troca.
- O jogo desejado deve pertencer ao outro usuario envolvido.
- Ambos os jogos precisam estar disponiveis para iniciar uma troca.
- Ao solicitar uma troca, os jogos ficam indisponiveis.
- Uma troca pode ser cancelada por participante envolvido.
- Uma troca cancelada nao pode ser confirmada.
- Uma troca concluida nao pode ser cancelada.
- A troca so e concluida quando os dois usuarios confirmam.
- Ao concluir a troca, a propriedade dos jogos e transferida entre os usuarios.

## Estrutura do Projeto

```text
src/main/java/app
+-- config
|   +-- SecurityConfig.java
+-- controller
|   +-- AnuncioController.java
|   +-- CategoriaController.java
|   +-- ChatController.java
|   +-- CidadeController.java
|   +-- ConsoleController.java
|   +-- JogoController.java
|   +-- RankingController.java
|   +-- TrocaController.java
|   +-- UsuarioController.java
+-- dto
+-- entity
+-- exception
|   +-- GlobalExceptionHandler.java
+-- repository
+-- service
```

## Principais Entidades

- `Usuario`: representa usuarios da plataforma.
- `Jogo`: representa jogos cadastrados por usuarios.
- `Console`: representa consoles associados aos jogos.
- `Anuncio`: representa anuncios criados para jogos.
- `Troca`: representa uma solicitacao de troca entre dois usuarios.
- `ChatMessage`: representa mensagens trocadas entre usuarios.
- `Cidade`: representa a cidade do usuario.
- `Ranking`: representa uma classificacao associada ao usuario.
- `Categoria`: representa categorias usadas no sistema.

## Endpoints Principais

### Usuarios

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| POST | `/api/usuario/save` | Cria usuario |
| PUT | `/api/usuario/update/{id}` | Atualiza usuario |
| GET | `/api/usuario/findAll` | Lista usuarios |
| GET | `/api/usuario/findById/{id}` | Busca usuario por ID |
| DELETE | `/api/usuario/delete/{id}` | Remove usuario |
| GET | `/api/usuario/findByNomeStartingWithIgnoreCase` | Busca usuarios por nome |

### Jogos

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| POST | `/api/jogo/save` | Cria jogo |
| PUT | `/api/jogo/update/{id}` | Atualiza jogo |
| DELETE | `/api/jogo/delete/{id}` | Remove jogo |
| GET | `/api/jogo/findAll/{pagina}` | Lista jogos paginados |
| GET | `/api/jogo/findAllAll` | Lista todos os jogos |
| GET | `/api/jogo/findById/{id}` | Busca jogo por ID |
| GET | `/api/jogo/findByNomeStartingWithIgnoreCase` | Busca jogos por nome |
| GET | `/api/jogo/findByConsole` | Busca jogos por console |

### Trocas

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| POST | `/api/troca/solicitar/{usuarioAId}` | Solicita uma troca |
| PUT | `/api/troca/{trocaId}/confirmar/{usuarioId}` | Confirma uma troca |
| PUT | `/api/troca/{trocaId}/cancelar/{usuarioId}` | Cancela uma troca |
| GET | `/api/troca/findAll` | Lista trocas |
| GET | `/api/troca/findById/{id}` | Busca troca por ID |

### Anuncios

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| POST | `/api/anuncio/save` | Cria anuncio |
| PUT | `/api/anuncio/update/{id}` | Atualiza anuncio |
| DELETE | `/api/anuncio/delete/{id}` | Remove anuncio |
| GET | `/api/anuncio/findAll` | Lista anuncios |
| GET | `/api/anuncio/findById/{id}` | Busca anuncio por ID |
| GET | `/api/anuncio/findByUsuario/{usuarioId}` | Lista anuncios por usuario |

### Chat

| Metodo | Endpoint | Descricao |
| --- | --- | --- |
| POST | `/api/chat/enviar` | Envia mensagem |
| GET | `/api/chat/historico` | Consulta historico de mensagens |
| PUT | `/api/chat/marcarComoLida/{id}` | Marca mensagem como lida |

## Como Rodar Localmente

### Pre-requisitos

- Java 17
- Maven
- MySQL

### Banco de Dados

Crie um banco MySQL chamado `integrador`:

```sql
CREATE DATABASE integrador;
```

Configuracao atual em `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/integrador
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### Executando a API

Acesse a pasta do backend:

```bash
cd Integrador2025.1/Integrador2025.1
```

Execute com Maven:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API sera iniciada em:

```text
http://localhost:8080
```

## Exemplo de Requisicao

Solicitar uma troca:

```http
POST /api/troca/solicitar/1
Content-Type: application/json

{
  "usuarioBId": 2,
  "jogoXId": 10,
  "jogoYId": 15
}
```

Confirmar uma troca:

```http
PUT /api/troca/3/confirmar/1
```

Cancelar uma troca:

```http
PUT /api/troca/3/cancelar/1
```

## Pontos Tecnicos Ja Aplicados

- API REST com Spring Boot.
- Separacao em camadas.
- Persistencia com JPA/Hibernate.
- Relacionamentos entre entidades.
- DTOs para fluxos especificos.
- Validacoes com Bean Validation.
- Tratamento global de excecoes.
- Regras de negocio em camada de service.
- Transacoes em fluxos de troca.
- WebSocket para funcionalidades de chat.

## Melhorias Planejadas

Este projeto ainda esta em evolucao. As proximas melhorias planejadas sao:

- Criar Docker Compose com MySQL.
- Remover credenciais fixas do `application.properties`.
- Trocar `spring.jpa.hibernate.ddl-auto=update` por migracoes com Flyway.
- Adicionar Swagger/OpenAPI.
- Criar testes unitarios para `TrocaService`.
- Criar testes de integracao para os principais endpoints.
- Configurar GitHub Actions para rodar `mvn test`.
- Revisar `SecurityConfig`, pois atualmente as requisicoes estao liberadas.
- Implementar autenticacao/autorizacao real com JWT ou Keycloak.
- Padronizar respostas de erro.
- Limpar duplicidades e dependencias desnecessarias no `pom.xml`.

## Status do Projeto

Projeto academico funcional, usado como base de estudo e evolucao para portfolio backend Java. O objetivo atual e transformar o KDS em um projeto mais profissional, com documentacao, testes, Docker, CI/CD e seguranca melhor definida.
