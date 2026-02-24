# Carteira de Investimentos API

Esta é uma API REST para gerenciamento de carteiras de ativos financeiros, desenvolvida para a disciplina de **Sistemas Web II**. A aplicação permite o controle de usuários e seus respectivos investimentos.

---

## 🚀 Como Rodar Localmente

### Pré-requisitos
* **Java 17** ou superior instalado.
* **PostgreSQL** rodando localmente.
* **Maven Wrapper** (incluído na raiz do projeto como `./mvnw`).

### Configuração do Banco de Dados
A aplicação espera um banco de dados PostgreSQL. Certifique-se de que as credenciais no seu `src/main/resources/application.properties` coincidam com o seu ambiente:
* **Database:** `investment-wallet` (ou o nome que você definiu)
* **Username/Password:** Conforme sua configuração local.

```bash
# Entre na pasta onde está o arquivo docker-compose.yml
cd docker

# Sobe o container do PostgreSQL em background
docker-compose up -d

# Retorne para a raiz do projeto
cd ..

# Rode a aplicação
./mvnw spring-boot:run
```

### 🧩 Camadas do Sistema
* **Controller:** Responsável apenas pela exposição dos endpoints e recepção das requisições HTTP.
* **Service:** Atua como orquestrador, chamando os repositórios e os casos de uso.
* **Domain (Use Cases):** Camada onde residem as regras de negócio puras, desacopladas de frameworks de persistência.
* **Repository:** Interface de comunicação com o banco de dados PostgreSQL.

---

## 📑 Especificação dos Endpoints

### 👤 Usuários (`/users`)

| Método   | Endpoint           | Payload (Corpo)          | Descrição                                      |
| :------- | :----------------- | :----------------------- | :--------------------------------------------- |
| **GET** | `/users`           | N/A                      | Retorna a lista de todos os usuários cadastrados. |
| **GET** | `/users/{id}`      | N/A                      | Busca detalhes de um usuário específico via UUID. |
| **POST** | `/users`           | `CreateUserDTO`          | Cria um novo usuário com validações de domínio. |
| **PUT** | `/users`           | `UpdateUserDTO`          | Atualiza informações (nome, email, senha).     |
| **DELETE**| `/users`          | `DeleteUserDTO`          | Remove um usuário do sistema.                  |

### 💰 Investimentos (`/investments`)

| Método   | Endpoint                        | Payload (Corpo)          | Descrição                                                                 |
| :------- | :------------------------------ | :----------------------- | :------------------------------------------------------------------------ |
| **POST** | `/investments/user/{userId}`    | `CreateInvestmentDTO`    | Registra um novo aporte para o usuário (valida preço, qtd e data).        |
| **GET** | `/investments/user/{userId}`    | N/A                      | Recupera todo o histórico de investimentos de um usuário.                 |
| **GET** | `/investments/user/{userId}/type/{typeId}` | N/A           | Filtra os ativos por tipo (Ex: 1 para Ações, 2 para FIIs).                |
| **PUT** | `/investments`                  | `UpdateInvestmentDTO`    | Altera dados de um investimento existente (ID obrigatório).               |
| **DELETE**| `/investments`                 | `DeleteInvestmentDTO`    | Remove um registro de investimento da carteira.                           |
| **GET** | `/investments/summary/{userId}` | N/A                      | Retorna o balanço consolidado (Patrimônio total e indicadores do usuário).|

---
