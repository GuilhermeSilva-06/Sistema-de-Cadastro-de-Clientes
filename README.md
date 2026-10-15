# 👥 Sistema de Cadastro de Clientes

API REST desenvolvida em **Java com Spring Boot** para o gerenciamento de clientes, com operações CRUD, validação de dados e persistência em banco de dados PostgreSQL.

O projeto tem como objetivo aplicar conceitos de desenvolvimento Backend, arquitetura em camadas, regras de negócio e boas práticas na construção de APIs RESTful.

## 🚀 Tecnologias utilizadas

- **Java** — linguagem de programação.
- **Spring Boot** — desenvolvimento da aplicação Backend.
- **Spring Data JPA** — persistência e acesso aos dados.
- **Hibernate** — mapeamento objeto-relacional (ORM).
- **PostgreSQL** — banco de dados relacional.
- **Bean Validation** — validação dos dados de entrada.
- **Maven** — gerenciamento de dependências.
- **Git e GitHub** — versionamento e controle do código.
- **Postman** — testes das requisições HTTP.

## ✨ Funcionalidades

### 👤 Gerenciamento de clientes

- Cadastro de clientes.
- Listagem de clientes cadastrados.
- Busca de cliente por ID.
- Atualização dos dados de um cliente.
- Exclusão de clientes.

### 🛡️ Validação e regras de negócio

- Validação de campos obrigatórios.
- Verificação do formato de e-mail.
- Validação do formato de CPF.
- Validação de telefone.
- Tratamento de CPF e e-mail duplicados.
- Retorno de códigos HTTP apropriados para erros de requisição.

### 🔎 Busca e organização dos dados

- Filtros por CPF e e-mail.
- Paginação dos resultados, conforme a implementação das funcionalidades.

## 🏗️ Arquitetura do projeto

A aplicação utiliza uma arquitetura organizada por responsabilidades, separando as camadas principais do Backend.

| Camada | Responsabilidade |
|---|---|
| Controller | Receber requisições HTTP e retornar respostas da API. |
| Service | Implementar regras de negócio e coordenar operações. |
| Repository | Realizar operações de acesso ao banco de dados. |
| Entity | Representar as entidades persistidas no banco. |
| DTO | Transportar e controlar os dados recebidos e retornados pela API. |

Essa separação facilita a manutenção, a organização e a evolução da aplicação.

## 🔗 Endpoints da API

Base URL local: `http://localhost:8080`

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| `GET` | `/clientes` | Listar clientes |
| `GET` | `/clientes/{id}` | Buscar cliente por ID |
| `POST` | `/clientes` | Cadastrar cliente |
| `PUT` | `/clientes/{id}` | Atualizar cliente |
| `DELETE` | `/clientes/{id}` | Excluir cliente |

Os endpoints de filtros e paginação devem ser documentados conforme os parâmetros efetivamente implementados no projeto.

## 🧪 Testes

O projeto contempla testes relacionados à validação dos dados, incluindo cenários como:

- E-mail vazio ou inválido.
- CPF vazio ou em formato inválido.
- Telefone vazio ou inválido.
- Aceitação de dados válidos.
- Tratamento de dados duplicados.

As requisições HTTP também podem ser verificadas utilizando o Postman.

## ⚙️ Como executar o projeto

### Pré-requisitos

- JDK compatível com a versão Java configurada no projeto.
- Maven.
- PostgreSQL.
- Git.

### 1. Clone o repositório

```bash
git clone https://github.com/GuilhermeSilva-06/Sistema-de-Cadastro-de-Clientes.git
```

### 2. Acesse a pasta do projeto

```bash
cd Sistema-de-Cadastro-de-Clientes
```

### 3. Configure o banco de dados

Crie um banco de dados PostgreSQL chamado `cadastro_clientes` e confira as configurações de conexão no arquivo `src/main/resources/application.properties`.

Exemplo de configuração:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cadastro_clientes
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**Importante:** substitua `SUA_SENHA` pela senha do seu banco de dados. Não publique credenciais reais no GitHub.

### 4. Execute a aplicação

Pelo terminal, na pasta do projeto:

```bash
mvn spring-boot:run
```

Após a inicialização, a API estará disponível em `http://localhost:8080`, caso a porta padrão esteja configurada.

## 📚 Aprendizados

O desenvolvimento deste projeto permite praticar:

- Construção de APIs REST com Spring Boot.
- Operações CRUD utilizando Spring Data JPA.
- Integração com PostgreSQL.
- Validação de dados e tratamento de exceções.
- Implementação de regras de negócio.
- Utilização de DTOs para entrada e saída de dados.
- Consultas, filtros e paginação.
- Testes e depuração de aplicações Backend.

## 👨‍💻 Autor

**Guilherme Fernandes Silva**

Estudante de Análise e Desenvolvimento de Sistemas, com foco em desenvolvimento Backend Java e interesse em aprimorar conhecimentos em Spring Boot, bancos de dados relacionais, testes e construção de APIs REST.

- GitHub: [GuilhermeSilva-06](https://github.com/GuilhermeSilva-06)
- Repositório: [Sistema de Cadastro de Clientes](https://github.com/GuilhermeSilva-06/Sistema-de-Cadastro-de-Clientes)

---

⭐ Se este projeto foi útil para seus estudos, considere deixar uma estrela no repositório.
